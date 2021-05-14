public class AnotherThread extends Thread{

    @Override
    public void run() {
        System.out.println("Hello from another thread, fro outside main in different class ..... in "+currentThread().getName());

        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){
            System.out.println("Another thread work me up");
            return;
        }

        System.out.println("Three seconds have passed and I am awake");
    }
}
