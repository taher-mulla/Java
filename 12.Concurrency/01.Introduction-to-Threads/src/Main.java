public class Main {

    public static void main(String[] args) {

        //YOU CANNOT KNOW WHICH THREAD WILL PRINT, OR GIVE IT OUTPUT WHEN
        // IT IS ENTIRELY DEPENDANT ON THE RUNNING TIME OF THE THREAD

        //THIS EXAMPLE ALSO SHOWS HOW THREADS CAN BE PUT TO SLEEP FOR SOMETIME
        Thread anotherThread = new AnotherThread();
        anotherThread.setName(" == AnotherThread == ");
        anotherThread.start();




        System.out.println("Hello from the main thread");

        new Thread() {
            public void run(){
                System.out.println("Hello from the anonymous class thread ");
            }
        }.start();

        System.out.println("Hello again from the main thread");

        //
        Thread myRunnableThread = new Thread(new RunnableThread());
        myRunnableThread.start();

        //
        Thread myRunnableThread1 = new Thread(new RunnableThread(){
            @Override
            public void run() {
                super.run();
                System.out.println("Hello from the anonymous class's implementation of run()");
                try{

//                    anotherThread.join();     //this will not have a time out and can infinitely wait for the previous thread
                    anotherThread.join(2000);
                    System.out.println("anotherThread is awake (or the time has run out) and has finished its functions, now I can continue");

                }catch (InterruptedException e){
                    System.out.println("I couldent want and was intrupted ... I was in join");
                }
            }
        });
        myRunnableThread1.start();

        //IF YOU CANN run() THE THREAD WILL RUN ON THE SAME THREAD AND NOT A NEW ONE
        //THIS EXAMPLE ALSO SHOWS HOW THREADS CAN BE PUT TO SLEEP FOR SOMETIME
        Thread anotherThread1 = new AnotherThread();
        anotherThread1.setName(" == AnotherThread 1 == ");
        anotherThread.run();        //this is running in main hence will take time as it sleeps

        //INTERRUPTING THE THREAD
        Thread anotherThreadinterupt = new AnotherThread();
        anotherThreadinterupt.setName(" == AnotherThread 1 == ");
        anotherThreadinterupt.start();
        anotherThreadinterupt.interrupt();


    }
}
