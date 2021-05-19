import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //localhost will basically take your PC's ip address
        //you can also put your own ip address there or put 127.0.0.1
        //port number needs to be same as server
        try(Socket socket = new Socket("localHost", 5000)){

            BufferedReader echoes = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //true will self flush the op every time we write/ send anything on op Stream
            PrintWriter stringToEcho =  new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String echoString;
            String response;

            do{
                System.out.println("Enter string to be echoed");
                echoString = scanner.nextLine();

                stringToEcho.println(echoString);

                //if not exit then server will sent echo
                if(!echoString.equals("exit")){
                    response = echoes.readLine();
                    System.out.println(response);
                }
            }while (!echoString.equals("exit"));

        }catch (IOException e){
            System.out.println("Client error : "+e.getMessage());
            e.getStackTrace();
        }

    }
}
