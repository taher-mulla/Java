import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {


        try{

            DatagramSocket socket = new DatagramSocket(5000);

            while (true){
                byte[] buffer = new byte[50];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                //[ buffer, 0, packet.getLength() ] -> this will print only the characters and not the full buffer
                System.out.println("Text received is :"+new String(buffer, 0, packet.getLength()) );

                String returnString = "Echo : "+new String(buffer,0, packet.getLength());
                byte[] buffer2 = returnString.getBytes();
                //the received packet has all the sender data
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buffer2, buffer2.length, address, port);
                socket.send(packet);

            }

        }catch (SocketException e){
            System.out.println("server SocketException : "+e.getMessage());
        }catch (IOException e){
            System.out.println("server IOException : "+e.getMessage());
        }

    }
}
