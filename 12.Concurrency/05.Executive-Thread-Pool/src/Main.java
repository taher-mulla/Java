import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static final String EOF = "EOF";

    public static void main(String[] args) {

        List<String> buffer = new ArrayList<String >();
        ReentrantLock bufferLock = new ReentrantLock();

        //'ExecutorService' ALLOWS THE COMPILER TO OPTIMIZE THE THREADS AND THE SYSTEM
        ExecutorService executiveService = Executors.newFixedThreadPool(3);

        MyProducer producer = new MyProducer(buffer, ThreadColors.ANSI_YELLOW, bufferLock);
        MyConsumer consumer1 = new MyConsumer(buffer, ThreadColors.ANSI_PURPLE, bufferLock);
        MyConsumer consumer2 = new MyConsumer(buffer, ThreadColors.ANSI_CYAN, bufferLock);


        executiveService.execute(producer);
        executiveService.execute(consumer1);
        executiveService.execute(consumer2);


        Future<String> future =executiveService.submit(new Callable<String >() {
            @Override
            public String call() throws Exception {
                System.out.println(ThreadColors.ANSI_WHITE+ "I am in the callable class");
                return "This is the callable runnable";
            }
        });
        try{
            System.out.println(future.get());
        }catch (ExecutionException e){
            System.out.println("Something went wrong");
        }catch (InterruptedException e){
            System.out.println("thread running the task was interrupted");
        }


        executiveService.shutdown();

//        new Thread(producer).start();
//        new Thread(consumer1).start();
//        new Thread(consumer2).start();

    }
}

class MyProducer implements Runnable{
    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public MyProducer(List<String> buffer, String color, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;
    }

    @Override
    public void run() {

        Random random =new Random();
        String[] nums = {"1","2","3","4","5"};

        for(String num:nums){
            try{
                System.out.println(color+"Adding... "+num);
                bufferLock.lock();
                try{
                    buffer.add(num);
                }finally {
                    bufferLock.unlock();
                }

                Thread.sleep(random.nextInt(1000));
            }catch (InterruptedException e){
                System.out.println("Producer was interrupted");
            }
        }

        System.out.println(color+"Adding EOF and exiting ");
        bufferLock.lock();
        try {
            buffer.add("EOF");
        }finally {
            bufferLock.unlock();
        }
    }
}






class MyConsumer implements Runnable{
    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public MyConsumer(List<String> buffer, String color, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;
    }

    @Override
    public void run() {

        int counter = 0;

        while(true){
            if(bufferLock.tryLock()){
                try{
                    if (buffer.isEmpty()) {
                        continue;
                    }
                    System.out.println(color+ "The counter is == "+counter);
                    counter = 0;
                    if (buffer.get(0).equals(Main.EOF)) {
                        System.out.println(color + "Exiting");
                        break;
                    } else {
                        System.out.println(color + "Removed " + buffer.remove(0));
                    }
                }finally{
                    bufferLock.unlock();
                }
            }else {
                counter++;
            }
        }
    }
}