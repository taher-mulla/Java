import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static final String EOF = "EOF";

    public static void main(String[] args) {

        ArrayBlockingQueue<String> buffer = new ArrayBlockingQueue<String>(6);

        //'ExecutorService' ALLOWS THE COMPILER TO OPTIMIZE THE THREADS AND THE SYSTEM
        ExecutorService executiveService = Executors.newFixedThreadPool(3);

        MyProducer producer = new MyProducer(buffer, ThreadColors.ANSI_YELLOW);
        MyConsumer consumer1 = new MyConsumer(buffer, ThreadColors.ANSI_PURPLE);
        MyConsumer consumer2 = new MyConsumer(buffer, ThreadColors.ANSI_CYAN);


        executiveService.execute(producer);
        executiveService.execute(consumer1);
        executiveService.execute(consumer2);


        Future<String> future = executiveService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(ThreadColors.ANSI_WHITE + "I am in the callable class");
                return "This is the callable runnable";
            }
        });
        try {
            System.out.println(future.get());
        } catch (ExecutionException e) {
            System.out.println("Something went wrong");
        } catch (InterruptedException e) {
            System.out.println("thread running the task was interrupted");
        }


        executiveService.shutdown();
    }
}


class MyProducer implements Runnable {
    private ArrayBlockingQueue<String> buffer;
    private String color;

    public MyProducer(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {

        Random random = new Random();
        String[] nums = {"1", "2", "3", "4", "5"};

        for (String num : nums) {
            try {
                System.out.println(color + "Adding... " + num);
                buffer.put(num);

                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                System.out.println("Producer was interrupted");
            }
        }

        System.out.println(color + "Adding EOF and exiting ");
        try {
            buffer.put("EOF");
        } catch (InterruptedException e) {

        }
    }
}


class MyConsumer implements Runnable {
    private ArrayBlockingQueue<String> buffer;
    private String color;

    public MyConsumer(ArrayBlockingQueue<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {

        while (true) {
            synchronized (buffer) {
                try {
                    if (buffer.isEmpty()) {
                        continue;
                    }
                    if (buffer.peek().equals(Main.EOF)) {
                        System.out.println(color + "Exiting");
                        break;
                    } else {
                        System.out.println(color + "Removed " + buffer.take());
                    }
                } catch (InterruptedException e) {

                }

            }
        }
    }
}