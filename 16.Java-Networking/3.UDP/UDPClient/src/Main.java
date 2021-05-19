import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try{

            //this will get the localhost here
            //there is also a InetAddress.getByName(),  this will let you pass a host name to it
            InetAddress address = InetAddress.getLocalHost();
            //socket that the client is going to use, socket has no port number
            DatagramSocket datagramSocket = new DatagramSocket();

            Scanner scanner = new Scanner(System.in);
            String echoString;

            do{

                System.out.println("Enter String to echo : ");
                echoString = scanner.nextLine();

                byte[] buffer = echoString.getBytes();

                //creating the packet
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 5000);
                datagramSocket.send(packet);

                //the echo received from the server
                byte[] buffer2 = new byte[50];
                packet= new DatagramPacket(buffer2, buffer2.length);
                datagramSocket.receive(packet);
                System.out.println("Received : "+new String(buffer2, 0, packet.getLength()));

            }while(!echoString.equals("exit"));

        }catch (SocketTimeoutException e){
            System.out.println("Client Socker timed out : "+e.getMessage());
        }catch(IOException e){
            System.out.println("IOException in client : "+e.getMessage());
        }

    }
}
