import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class CopyFiles extends SimpleFileVisitor<Path> {

    private Path sourceRoot;
    private Path targetRoot;

    public CopyFiles(Path sourceRoot, Path targetRoot) {
        this.sourceRoot = sourceRoot;
        this.targetRoot = targetRoot;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.out.println("Error accessing file: "+file.toAbsolutePath()+" "+exc.getMessage());
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        //for example think that we are copying
        //FileTree/Dir2/Dir3/file2.txt TO FileTress/Dir4/CopyOfDir2/Dir3/file2.txt
        Path relativisedPath = sourceRoot.relativize(dir);//this is Dir3/file2.txt
        System.out.println("Relativised path is : "+relativisedPath);
        Path copyDir = targetRoot.resolve(relativisedPath);//this is FileTress/Dir4/CopyOfDir2/Dir3/file2.txt
        System.out.println("Resolved path for copy : "+copyDir);

        try{
            Files.copy(dir,copyDir);
        }catch (IOException e){
            System.out.println(e.getMessage());
            return FileVisitResult.SKIP_SUBTREE;
        }
        return FileVisitResult.CONTINUE;

    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        Path relativisedPath = sourceRoot.relativize(file);
        System.out.println("Relativised path is : "+relativisedPath);
        Path copyDir = targetRoot.resolve(relativisedPath);
        System.out.println("Resolved path for copy : "+copyDir);

        try{
            Files.copy(file,copyDir);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return FileVisitResult.CONTINUE;
    }
}
