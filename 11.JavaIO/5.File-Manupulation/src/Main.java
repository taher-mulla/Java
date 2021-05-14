import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

public class Main {

    public static void main(String[] args) {

        try{
            //COPYING A FILE
            Path sourcePath = FileSystems.getDefault().getPath("Examples","file1.txt");
            Path copyPath = FileSystems.getDefault().getPath("Examples","fileCopied.txt");
//            Files.copy(sourcePath,copyPath);  // "StandardCopyOption.REPLACE_EXISTING" is put to replace an existing file
            Files.copy(sourcePath,copyPath, StandardCopyOption.REPLACE_EXISTING);

            //COPYING A DIRECTORY (ONLY THE FOLDER...NOT ANY FILES INSIDE IT)
            sourcePath = FileSystems.getDefault().getPath("Examples","Dir1");
            copyPath = FileSystems.getDefault().getPath("Examples","Dir4");
            Files.copy(sourcePath,copyPath, StandardCopyOption.REPLACE_EXISTING);

            //MOVING A FILE
            Path fileToMove = FileSystems.getDefault().getPath("Examples","fileCopied.txt");
            Path destinationToMoveTo = FileSystems.getDefault().getPath("Examples","Dir1",
                    "fileCopiedThenMoved.txt");
            Files.move(fileToMove,destinationToMoveTo);

            //DELETING A FILE
            Path fileToDelete = FileSystems.getDefault().getPath("Examples", "Dir1",
                    "fileCopiedThenMoved.txt");
            Files.delete(fileToDelete);
                //deleted if file exists
//            Files.deleteIfExists(fileToDelete);

            //CREATING A FILE
            Path fileToCreate = FileSystems.getDefault().getPath("Examples","NewCreatedfile.txt");
            Files.createFile(fileToCreate);

            //CREATING A DIRECTORY
            Path dirToCreate = FileSystems.getDefault().getPath("Examples","NewDirMade");
            Files.createDirectory(dirToCreate);

            //CREATING MULTIPLE DIRECTORIES
            Path multipleDirToCreate = FileSystems.getDefault().getPath("Examples",
                    "Dir2/Dir3/Dir4/dir5/Dir6/NewDirMade");
            Files.createDirectories(multipleDirToCreate);

            //GETTING THE FILE PROPERTIES/MATADATA/ATTRIBUTES
            Path properties = FileSystems.getDefault().getPath("Examples","Dir1/file1.txt");
            System.out.println("Size is = "+Files.size(properties));
            System.out.println("Last modified time ="+Files.getLastModifiedTime(properties));

            BasicFileAttributes fileAttr = Files.readAttributes(properties,BasicFileAttributes.class);
            System.out.println("Size = "+fileAttr.size());
            System.out.println("Is Directory ="+fileAttr.isDirectory());
            System.out.println("Created = "+fileAttr.creationTime());
            System.out.println("Last accessed at = "+fileAttr.lastAccessTime());
            System.out.println("Is regular field = "+fileAttr.isRegularFile());



        }catch(IOException e){
            System.out.println("ERROR!!!!!!!!!!!!!  "+e.getMessage());
            e.printStackTrace();
        }

    }
}
