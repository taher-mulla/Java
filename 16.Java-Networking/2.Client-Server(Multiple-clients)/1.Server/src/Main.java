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
        try (ServerSocket serverSocket = new ServerSocket(5000)) {

            while (true) {

                //calling a client thread everytime a client tries to connect
//                Socket socket = serverSocket.accept();
//                Echoer echoer = new Echoer(socket);
//                echoer.start();
                //same as the below code
                new Echoer(serverSocket.accept()).start();
            }

        } catch (IOException e) {
            System.out.println("server error : " + e.getMessage());
        }


    }
}
