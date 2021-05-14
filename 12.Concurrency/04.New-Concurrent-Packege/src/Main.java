import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;


public class Main {

    public static final String EOF = "EOF";

    public static void main(String[] args) {

        List<String> buffer = new ArrayList<String >();
        ReentrantLock bufferLock = new ReentrantLock();
        MyProducer producer = new MyProducer(buffer, ThreadColors.ANSI_YELLOW, bufferLock);
        MyConsumer consumer1 = new MyConsumer(buffer, ThreadColors.ANSI_PURPLE, bufferLock);
        MyConsumer consumer2 = new MyConsumer(buffer, ThreadColors.ANSI_CYAN, bufferLock);

        new Thread(producer).start();
        new Thread(consumer1).start();
        new Thread(consumer2).start();

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
                    //the lock and unlock are done to lock the buffer for reading or writing,
                    // basically only this thread can use this
                bufferLock.lock();
                    //this is put in a try block as good practice - if ever there is an exception of a error the buffer will be unlocked
                try{
                    buffer.add(num);
                }finally {
                    bufferLock.unlock();
                }

                //YOU DON'T NEED THIS IF YOU USE THE BUFFERLOCK'S
//                synchronized (buffer){
//                    buffer.add(num);
//                }

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


        //YOU DON'T NEED THIS IF YOU USE THE BUFFERLOCK'S
//        synchronized (buffer){
//            buffer.add("EOF");
//        }

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

            bufferLock.lock();
            try{
                if (buffer.isEmpty()) {
//                    bufferLock.unlock();
                    continue;
                }
                if (buffer.get(0).equals(Main.EOF)) {
                    System.out.println(color + "Exiting");
//                    bufferLock.unlock();
                    break;
                } else {
                    System.out.println(color + "Removed " + buffer.remove(0));
                }
            }finally{
                bufferLock.unlock();
            }



            //EXAMPLE OF 'tryLock' THIS WILL CHECK IF THERE IS A LOCK BY OTHER THREAD, AND IF FALSE IT WILL LOCK
            //YOU CAN ALSO PASS A TIMER SO THAT THE THREAD IS INTERRUPTED AFTER A TIE SO THAT IT DOESN'T WAIT TOOMUCH
            //YOU CAN ALSO CHECK TO SEE WHICH THREAD IS HOLDING THE LOCK
            //YOU CAN ALSO MAKE IT A FAIR LOCK, THIS WILL ASSIGN THE LOCK TO THE THREAD THAT IS WAITING THE MOST FOR THE LOCK
            if(bufferLock.tryLock()){
                try{
                    if (buffer.isEmpty()) {
//                    bufferLock.unlock();
                        continue;
                    }
                    System.out.println(color+ "The counter is == "+counter);
                    counter = 0;
                    if (buffer.get(0).equals(Main.EOF)) {
                        System.out.println(color + "Exiting");
//                    bufferLock.unlock();
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



            //YOU DON'T NEED THIS IF YOU USE THE BUFFERLOCK'S
//            synchronized (buffer){
//                if(buffer.isEmpty()){
//                    continue;
//                }
//                if(buffer.get(0).equals(Main.EOF)){
//                    System.out.println(color+"Exiting");
//                    break;
//                }else {
//                    System.out.println(color+"Removed "+buffer.remove(0));
//                }
//            }

        }
    }
}
