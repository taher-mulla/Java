import java.awt.*;

public class Main {

    public static void main(String[] args) {

        countdown countdown = new countdown();
        CountdownThread t1 = new CountdownThread(countdown);
        t1.setName("Thread 1");
        CountdownThread t2 = new CountdownThread(countdown);
        t2.setName("Thread 2");

        t1.start();
        t2.start();

    }
}


class countdown {

     //HERE 'synchronized' WILL ENSURE THAT ONLY ONE THREAD AT A TIME CAN ACCESS THIS, THERE WILL NOT BE ANY OVERLAP
     //SO ONE THREAD WILL BE ACTIVE AT A TIME
//    public synchronized void doCountdown(){
    public void doCountdown(){
        String str="";

            //if we initialize 'i' here then both the threads will be using the same variable, hence the o/p will
            //show each thread skipping one number and printing(thread 1 -- 10,8,6,4,2 & thread 2 -- 9,7,5,3,1)
            //this happens if we declare the var here, it goes to the program heap WHICH IS ACCESSIBLE AND SAME FOR ALL THREADS
            //if its locally declared then it GOES TO THE THREAD STACK AND IS ONLY FOR THAT VAR
            //the other way to avoid this would be passing 2 different 'countdown' objects instead of just 1
//        int i;

        switch (Thread.currentThread().getName()){
            case "Thread 1":
                str = " 1111111 ";
                break;
            case "Thread 2":
                str = " 2222222 ";
                break;
            default:
                break;
        }

        //YOU CAN ALSO PUT THIS 'for' LOOP IN A 'synchronized(this){.....}' BLOCK

        for(int i=10; i>0; i--){
            System.out.println(str + "  " + Thread.currentThread().getName() + ": i = " + i);
        }

    }
}

class CountdownThread extends Thread{
    private countdown threadCountdown;

    public CountdownThread(countdown countdown){
        threadCountdown = countdown;
    }

    public void run(){
        threadCountdown.doCountdown();
    }
}