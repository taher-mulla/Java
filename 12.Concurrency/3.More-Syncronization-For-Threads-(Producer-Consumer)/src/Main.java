import java.awt.image.renderable.RenderableImageProducer;
import java.beans.IntrospectionException;
import java.io.Reader;
import java.sql.Wrapper;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Message message = new Message();
        (new Thread((new Writer(message)))).start();
        (new Thread((new Reder(message)))).start();
    }

}

class Message{
    private String message;
    private boolean empty = true;

    //empty is true when there is no message

    public synchronized String read(){
        while (empty){
            try{
                wait();
            }catch (InterruptedException e){

            }
        }
        empty = true;
        return message;
    }

    public synchronized void write(String message){
        while (!empty){
            try{
                wait();
            }catch (InterruptedException e){

            }
        }
        empty = false;
        this.message = message;
        notifyAll();
    }

}

class Writer implements Runnable{
    private Message message;

    public Writer(Message message){
        this.message=message;
    }

    public void run(){
        String messages[] = {
                "Humpty Dumpty Sat on a wall",
                "Humpty Dumpty Had a great fall",
                "All the kings horses and ll the kings en couldn't put humpty together again :("
        };

        Random random = new Random();

        for(int i=0; i< messages.length; i++){
            message.write(messages[i]);
            try{
                Thread.sleep(random.nextInt(2000));
            }catch (InterruptedException e){

            }
        }
        message.write("Finished");
    }
}

class Reder implements Runnable{
    private Message message;

    public Reder(Message message) {
        this.message = message;
    }

    public void run(){
        Random random = new Random();
        for(String latestMessage = message.read(); !latestMessage.equals("Finished");
            latestMessage = message.read()){

            System.out.println(latestMessage);
            try{
                Thread.sleep(random.nextInt(2000));
            }catch (InterruptedException e){

            }
        }
    }
}
