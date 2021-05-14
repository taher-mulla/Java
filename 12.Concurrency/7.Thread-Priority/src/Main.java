//PRIORITY IS JUST A SUGGESTION AND NOT GUARANTEED
//PRIORITY 2 MAY FINISH B4 PRIORITY 10 ALSO THIS HAPPENS DUE TO THE 'synchronized'
//WE CAN ALSO USE A FAIR LOCK THAT WILL BE FIRST-COME-FIRST-SERVE

import java.util.concurrent.locks.ReentrantLock;

public class Main {

    //without the fair lock
//    private static Object lock = new Object();

    //with the fair lock-----the true value is for the fair lock
    private static ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {


	//even with priority, most of the threads will never run according to it.
	//it is possible you will see the thread with priority 10 end last also
        Thread t1 = new Thread(new Worker(ThreadColors.ANSI_RED),"Priority 10");
        Thread t2 = new Thread(new Worker(ThreadColors.ANSI_BLUE),"Priority 8");
        Thread t3 = new Thread(new Worker(ThreadColors.ANSI_GREEN),"Priority 6");
        Thread t4 = new Thread(new Worker(ThreadColors.ANSI_CYAN),"Priority 4");
        Thread t5 = new Thread(new Worker(ThreadColors.ANSI_PURPLE),"Priority 2");

        t1.setPriority(10);
        t2.setPriority(8);
        t3.setPriority(6);
        t4.setPriority(4);
        t5.setPriority(2);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }


    private static class Worker implements Runnable{
        private int count =1;
        private String threadColor;

        public Worker(String threadColor) {
            this.threadColor = threadColor;
        }

        @Override
        public void run() {

            //with the fair lock
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try{
                        System.out.format(threadColor + "%s: runColor == %d\n", Thread.currentThread().getName(), count++);
                }finally {
                    lock.unlock();
                }
            }



            //without the fair lock
//            for(int i=0; i<10; i++){
//                synchronized (lock){
//                    System.out.format(threadColor+ "%s: runColor == %d\n",Thread.currentThread().getName(), count++);
//                }
//            }

        }
    }
}
