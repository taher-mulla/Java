import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try{
            //WE DONT USE THIS CAUSE WE WILL NEED TO MAKE A BUFFER AND FOR DIFFERENT LINE LENGTHS
            //DIFFERENT BUFFERS ARE NOT POSSIBLE
//            FileInputStream file = new FileInputStream("data.txt");
//            FileChannel channel = file.getChannel();

            //HERE WE ARE READING THE ENTIRE FILE INTO THE LIST OF STRINGS "lines"
            Path dataPath = FileSystems.getDefault().getPath("data.txt");

            //HERE WE ARE WRITING DATA TO THE FILE, DATA IS WRITTED IN BYTE FORM HENCE THE ".getBytes"
            //"File.write" NEEDS BYTE DATA AND CANT HANDEL STRING
            //WE PUT THIS "StandardOpenOption.APPEND" BECAUSE THE DEFAULT WOULD DELETE AND MAKE A NEW FILE
            Files.write(dataPath,"\nLine 3".getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);

            System.out.println("The path is "+dataPath.toString());
            
            //"Files.read" ASSUMES AND CONVERTS DATA TO BYTE BY DEFAULT
            List<String> lines = Files.readAllLines(dataPath);
            for(String line : lines){
                System.out.println(line);
            }

        }catch(IOException e){
            System.out.println("In catch \n");
            e.printStackTrace();
        }
    }
}
