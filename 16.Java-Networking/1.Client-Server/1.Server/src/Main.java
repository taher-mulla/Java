//every client will have a different socket and server will have a different socket
//all clients will talk to the same ip and post number but will have different sockets

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {

        //creating a server socket
        try(ServerSocket serverSocket = new ServerSocket(5000)){

            //creating a listener for the clients(server will listen for clients through this), this will return an socket instance and not a new ServerSocket
            Socket socket = serverSocket.accept();//accept method will block till a client connects tot eh server
            System.out.println("Client connected");

            //when client connects the server will use ip and op stream to send or receive data fro client
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //true will self flush the op every time we write/ send anything on op Stream
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            //to keep the server running till the client disconnects
            while(true){
                String echoString = input.readLine();
                if(echoString.equals("exit")){
                    break;
                }
                output.println("Echo from server : "+echoString);
            }

        }catch (IOException e){
            System.out.println("server error : "+e.getMessage());
        }



    }
}
