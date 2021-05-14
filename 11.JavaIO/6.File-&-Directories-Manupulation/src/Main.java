import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {

        Path directory = FileSystems.getDefault().getPath("FileTree/Dir2");

                        //for a filter(glob) to directory you can use allot of things like "*.txt" , "???.txt",
                        //"b?*.txt"........ all these will set a filter for the files output
        try(DirectoryStream<Path> contents = Files.newDirectoryStream(directory,"*.{dat,txt}")){
            for(Path file:contents){
                System.out.println(file.getFileName());
            }
        }catch (IOException | DirectoryIteratorException e){
            System.out.println("Error !!!!!!!!!! "+e.getMessage());
            e.printStackTrace();
        }


        //CREATING OUR OWN FILTER
        System.out.println();
        DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path path) throws IOException {
                return Files.isRegularFile(path);
            }
        };
        try(DirectoryStream<Path> contents = Files.newDirectoryStream(directory,filter)){
            for(Path file:contents){
                System.out.println(file.getFileName());
            }
        }catch (IOException | DirectoryIteratorException e){
            System.out.println("Error !!!!!!!!!! "+e.getMessage());
            e.printStackTrace();
        }

        //LAMMBDA EXPRESSION
        System.out.println();
        DirectoryStream.Filter<Path> lamdaFilter = p -> Files.isRegularFile(p);
        Path lamdaDirectory = FileSystems.getDefault().getPath("FileTree/Dir2");
        try(DirectoryStream<Path> contents = Files.newDirectoryStream(lamdaDirectory,lamdaFilter)){
            for(Path file:contents){
                System.out.println(file.getFileName());
            }
        }catch (IOException | DirectoryIteratorException e){
            System.out.println("Error !!!!!!!!!! "+e.getMessage());
            e.printStackTrace();
        }


        //USING THE SEPARATOR
        System.out.println();
        String separator = File.separator;
        System.out.println(separator);
        separator= FileSystems.getDefault().getSeparator();
        System.out.println(separator);
        //in a real world application you will use this seperator and not hard code it
        Path realWorldDirectory = FileSystems.getDefault().getPath("FileTree"+File.separator+"Dir2");


        //CREATING A TEMPORARY FILE
        System.out.println();
        try {
            Path tempFile = Files.createTempFile("myTempFile",".appext");
            System.out.println(tempFile.toAbsolutePath());
        }catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        //PRINTING THE MAIN DIRECTORIES OF A PC
        System.out.println();
        Iterable<FileStore> stores = FileSystems.getDefault().getFileStores();
        for(FileStore store : stores){
            System.out.println("Volume name/Drive letter = "+store);
            System.out.println("              File store = "+store.name());
        }

        System.out.println();
        Iterable<Path> paths = FileSystems.getDefault().getRootDirectories();
            //this will show the root drive
        for(Path path : paths){
            System.out.println(path);
        }

    }
}
