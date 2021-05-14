import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {
    
    //try with resources 
    		//FileOutputStream is finding/creating a file 'data.dat', think of it as the file object
    		//the FileChannel is used to read/ write to it
        try (FileOutputStream binFile = new FileOutputStream("data.dat");
             FileChannel binChannel = binFile.getChannel()) {

	     
	     //Writing the string in byte for to outputBytes
            byte[] outputBytes = "Hello Word!".getBytes();
            //wrapping the bytes in a ByteBuffer
            ByteBuffer buffer = ByteBuffer.wrap(outputBytes);

            int numBytes = binChannel.write(buffer);
            System.out.println("Number of bytes written is = " + numBytes);

            ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES);
            intBuffer.putInt(245);
            intBuffer.flip();
            numBytes = binChannel.write(intBuffer);
            System.out.println("Number of INT bytes written is = " + numBytes);

            intBuffer.flip();
            intBuffer.putInt(-2498);
            intBuffer.flip();
            numBytes = binChannel.write(intBuffer);
            System.out.println("Number of INT bytes written is = " + numBytes);


            //  READING THE FILE **WITH RANDOM ACCESS FILE CLASS**
            RandomAccessFile ra = new RandomAccessFile("data.dat", "rwd");
            byte[] b = new byte[outputBytes.length];
            ra.read(b);
            System.out.println();
            System.out.println(new String(b));

            long int1 = ra.readInt();
            System.out.println(int1);
            long int2 = ra.readInt();
            System.out.println(int2);


		//*********************** Java NIO ***********************

            //  READING THE FILE **WITH JAVA.NIO ACCESS FILE CLASS**
            RandomAccessFile raNIO = new RandomAccessFile("data.dat", "rwd");
            FileChannel channel = raNIO.getChannel();
            buffer.flip();
            long numBytesRead = channel.read(buffer);
            System.out.println("\noutput bytes = " + new String(outputBytes) + " Size is " + numBytesRead);


            //THESE ARE RELATIVE READ'S AS WE ARE JUST FLIPPING THE BUFFER EVERY TIME
            intBuffer.flip();       //FOR INT 1
            numBytesRead = channel.read(intBuffer);
            intBuffer.flip();
            System.out.println(intBuffer.getInt());

            //THIS IS ABSOLUTE READ, HERE WE SEND THE INDEX LOCATION OF THE INT TO THE READER
            intBuffer.flip();       //FOR INT 2
            numBytesRead = channel.read(intBuffer);
            System.out.println(intBuffer.getInt(0));


            //DIFFERENT WAY TO PRINT THE STRING IS
            buffer.flip();
            channel.read(buffer);
            if (buffer.hasArray()) {
                System.out.println("byte buffer = " + new String(buffer.array()));
            }

            channel.close();
            raNIO.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

        //RANDOM ACCESS NIO THOUGH CHANNEL
        try (FileOutputStream binFile = new FileOutputStream("Random_data.dat");
             FileChannel binChannel = binFile.getChannel()) {

            ByteBuffer buffer = ByteBuffer.allocate(100);

            byte[] outputBytes = "Hello Word!".getBytes();
            buffer.put(outputBytes);

            long position1 = outputBytes.length; //start of integer 1
            buffer.putInt(245);

            long position2 = position1 + Integer.BYTES; //start of integer 2
            buffer.putInt(-91221);

            byte[] outputBytes2 = "Nice to eet you".getBytes();
            buffer.put(outputBytes2);

            long position3 = position2 + Integer.BYTES + outputBytes2.length;  //start of integer 3
            buffer.putInt(1000);

            long lastPosition = position3 +Integer.BYTES;
            buffer.flip();
            //YOU CAN ALSO CHAIN THE PUT METHORD
//            buffer.put(outputBytes).putInt(245).putInt(-91221).put(outputBytes2).putInt(1000);
            binChannel.write(buffer);

            //READING IN LINEAR FOR
            System.out.println("\n\nREADING IN LINEAR FOR");
            RandomAccessFile ra = new RandomAccessFile("Random_data.dat","rwd");
            FileChannel channel = ra.getChannel();

            ByteBuffer readBuffer = ByteBuffer.allocate(100);
            channel.read(readBuffer);
            readBuffer.flip();
            byte[] inputString = new byte[outputBytes.length];
            readBuffer.get(inputString);
            System.out.println("Input String = "+new String(inputString));
            System.out.println("int 1 = "+readBuffer.getInt());
            System.out.println("int 2 = "+readBuffer.getInt());
            byte[] inputString2 = new byte[outputBytes2.length];
            readBuffer.get(inputString2);
            System.out.println("Input String = "+new String(inputString2));
            System.out.println("int 3 = "+readBuffer.getInt());

            channel.position(0);
            System.out.println("\nRandomly reading with position");
            ByteBuffer readRandomBuffer = ByteBuffer.allocate(Integer.BYTES);
            channel.position(position3);
            channel.read(readRandomBuffer);
            readRandomBuffer.flip();
            System.out.println("Int 3 = "+readRandomBuffer.getInt());

            channel.position(position2);
            readRandomBuffer.flip();
            channel.read(readRandomBuffer);
            readRandomBuffer.flip();
            System.out.println("Int 2 = "+readRandomBuffer.getInt());

            channel.position(position1);
            readRandomBuffer.flip();
            channel.read(readRandomBuffer);
            readRandomBuffer.flip();
            System.out.println("Int 1 = "+readRandomBuffer.getInt());

            //WRITING RANDOMLY USING THE LOCATIONS
            byte[] newOutputBytes = "***********New Hello Word********".getBytes();
            long strPos1 = lastPosition;                          //LOCATION FOR STRING 1
            long newPosition1 = strPos1 + newOutputBytes.length;  //LOCATION FOR INT 1
            long newPosition2 = newPosition1 + Integer.BYTES;     //LOCATION FOR INT 2
            long strPos2 = newPosition2 + Integer.BYTES;          //LOCATION FOR STRING 2
            byte[] newOutputBytes2 = "***************NEW Nice to meet you***********".getBytes();
            long newPosition3 = strPos2 + newOutputBytes2.length;//LOCATION FOR STRING 2

            ByteBuffer intBuffer = ByteBuffer.allocate(Integer.BYTES);
            intBuffer.putInt(78867);
            intBuffer.flip();
            binChannel.position(newPosition1);
            binChannel.write(intBuffer);    //WRITING THE FIRST INT

            intBuffer.flip();
            intBuffer.putInt(-99999);
            intBuffer.flip();
            binChannel.position(newPosition2);
            binChannel.write(intBuffer);    //WRITING THE SECOND INT -9999

            intBuffer.flip();
            intBuffer.putInt(88888);
            intBuffer.flip();
            binChannel.position(newPosition3);
            binChannel.write(intBuffer);    //WRITING THE THIRD INT 8888

            binChannel.position(strPos1);
            binChannel.write(ByteBuffer.wrap(newOutputBytes));
            binChannel.position(strPos2);
            binChannel.write(ByteBuffer.wrap(newOutputBytes2));

                    //DATA HAS NOT BE OUTPUTED BUT CAN BE SEEN IN THE .dat FILE

            //COPYING A FILE
            RandomAccessFile copyFile = new RandomAccessFile("data_copy.dat", "rw");
            FileChannel copyChannel = copyFile.getChannel();
            channel.position(0);
            //here you can use transferFrom(channel,0,channel.size()) or transferTo(0, channel.size(), copyChannel())
            long numTransfered = copyChannel.transferFrom(channel,0,channel.size());
            System.out.println("Num of bytes tranfered is = "+numTransfered);

            channel.close();
            ra.close();
            copyChannel.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
