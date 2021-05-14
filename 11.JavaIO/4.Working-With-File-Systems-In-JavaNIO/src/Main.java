import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        Path path = FileSystems.getDefault().getPath("WorkingDriecttory.txt");
        printFile(path);

        System.out.println();
        System.out.println();
        Path filePath = FileSystems.getDefault().getPath("files","subdirectory.txt");
        printFile(filePath);

        System.out.println();
        Path outsideFile = Paths.get("/home/user/Java-Learning/Udemy-Java/14.JavaIO/outsideDirectory.txt");
        printFile(outsideFile);

        //PRINTING THE DIRECTORY PATH
        Path temp = Paths.get("."); //getting the main directory path
        System.out.println();
        System.out.println(temp.toAbsolutePath());

        String tempString =temp.toAbsolutePath().toString();    //path to string conversion
        System.out.println(tempString);

        temp = FileSystems.getDefault().getPath("." , "files" , ".." , "files" ,
                "subdirectory.txt");        //manipulating the path, ".." will take to parent-folder
        System.out.println(temp.normalize().toAbsolutePath());

        //TO PRINT THE SUB-DIRECOTRY WE CAN ALSO USE THIS
        System.out.println();
        filePath=Paths.get("." , "files" , "subdirectory.txt");
        printFile(filePath);

        //CHECKING IF A FILE EXISTS
        System.out.println();
        filePath = FileSystems.getDefault().getPath("files");
        System.out.println("This file Exists ? = "+Files.exists(filePath));
        filePath = FileSystems.getDefault().getPath("ABASODbu");
        System.out.println("This file Exists ? = "+Files.exists(filePath));
        filePath = FileSystems.getDefault().getPath("files");
            //Is file readable
        System.out.println("Is this file readable ? = "+Files.isReadable(filePath));
            //Is file writable
        System.out.println("Is this file writable ? = "+Files.isWritable(filePath));
            //Is file executable
        System.out.println("Is this file executable ? = "+Files.isExecutable(filePath));

    }

    private static void printFile(Path path){
        try(BufferedReader fileReader = Files.newBufferedReader(path)){
            String line;
            while((line = fileReader.readLine()) != null){
                System.out.println(line);
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
