import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Main {


    public static void main(String[] args) {
        DiffWays obj = new DiffWays();

        //HERE WE IMPLEMENT Runnable IN A DIFFERENT CLASS AND CALL THAT
        obj.runnableImplementedInOtherClass();

        //ANONYMOUS THREADS
        obj.anonymousThreads();

        //HAVING A RUNNABLE INTERFACE
        obj.runnableInterface();            //this tryLock, lock and SYNCHRONIZATION can be
                                            //done in the bankAccount class also
        //USING 'ReentrantLock' LOCK FOR SYNCHRONIZATION
        ReentrantLock lock = new ReentrantLock();
        obj.reetrantLock(lock);

    }

}

class DiffWays {

    //HERE WE IMPLEMENT Runnable IN A DIFFERENT CLASS AND CALL THAT
    public void runnableImplementedInOtherClass() {
        String color = ThreadColors.ANSI_BLUE;
        BankAccount account = new BankAccount(1000, "TM12345");

        Runnable r1 = new Operations(account, color);
        Runnable r2 = new Operations2(account, color);
        new Thread(r1).start();
        new Thread(r2).start();
    }

    //ANONYMOUS THREADS
    public void anonymousThreads() {

        String colors = ThreadColors.ANSI_GREEN;
        final BankAccount account = new BankAccount(1000, "TM12345");

        Thread r1 = new Thread() {
            public void run() {
                account.deposit(300);
                System.out.println(colors + "AT -- r1 post deposit : " + account.getBallance());
                account.withdraw(50);
                System.out.println(colors + "AT -- r1 post withdraw :" + account.getBallance());
            }
        };

        Thread r2 = new Thread() {
            public void run() {
                account.deposit(203.75);
                System.out.println(colors + "AT -- r2 post deposit : " + account.getBallance());
                account.withdraw(100);
                System.out.println(colors + "AT -- r2 post withdraw :" + account.getBallance());
            }
        };

        r1.start();
        r2.start();
    }

    //HAVING A RUNNABLE INTERFACE
    public void runnableInterface() {
        String colors = ThreadColors.ANSI_RED;
        BankAccount account = new BankAccount(1000, "TM12345");

        Thread r1 = new Thread(new Runnable() {
            @Override
            public void run() {
                account.deposit(300);
                System.out.println(colors + "Runnable -- r1 post deposit : " + account.getBallance());
                account.withdraw(50);
                System.out.println(colors + "Runnable -- r1 post withdraw :" + account.getBallance());
            }
        });

        Thread r2 = new Thread(new Runnable() {
            @Override
            public void run() {
                account.deposit(203.75);
                System.out.println(colors + "Runnable -- r2 post deposit : " + account.getBallance());
                account.withdraw(100);
                System.out.println(colors + "Runnable -- r2 post withdraw :" + account.getBallance());
            }
        });

        r1.start();
        r2.start();
    }

    //USING 'ReentrantLock' LOCK FOR SYNCHRONIZATION
    public void reetrantLock(ReentrantLock lock) {
        String colors = ThreadColors.ANSI_WHITE;
        BankAccount account = new BankAccount(1000, "TM12345");

        Thread r1 = new Thread(new Runnable() {
            @Override
            public void run() {
                //normal lock
                try {
                    lock.lock();
                    account.deposit(300);
                    System.out.println(colors + "ReentrantLock -- r1 post deposit : " + account.getBallance());
                    account.withdraw(50);
                    System.out.println(colors + "ReentrantLock -- r1 post withdraw :" + account.getBallance());
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread r2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //tryLock
                try {
                    if(lock.tryLock(200, TimeUnit.MILLISECONDS)){
                        try {
                            lock.lock();
                            account.deposit(203.75);
                            System.out.println(colors + "ReentrantLock -- r2 post deposit : " + account.getBallance());
                            account.withdraw(100);
                            System.out.println(colors + "ReentrantLock -- r2 post withdraw :" + account.getBallance());
                        } finally {
                            lock.unlock();
                        }
                    }else{
                        System.out.println("Time Out in the tryLock()");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        r1.start();
        r2.start();

    }

}

