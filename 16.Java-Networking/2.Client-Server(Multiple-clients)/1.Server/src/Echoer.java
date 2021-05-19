import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Echoer extends Thread{
    private Socket socket;

    public Echoer(Socket socket){
        this.socket=socket;
    }

    @Override
    public void run() {
        try{

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            while (true){
                String echoString = input.readLine();
                if(echoString.equals("exit")){
                    break;
                }
                System.out.println("received message from client : "+echoString);


                try{
                    Thread.sleep(10000);
                }catch (Exception e){

                }


                output.println("Echo from server : "+echoString);
            }

        }catch (IOException e){
            System.out.println("Error in client : "+e.getMessage());
        }finally {
            try{
                socket.close();
            }catch (IOException e){
                System.out.println("Error trying to close client thread socket : "+e.getMessage());
            }
        }
    }
}
