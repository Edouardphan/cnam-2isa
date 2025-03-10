import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.FileVisitResult;
import java.nio.file.attribute.BasicFileAttributes;

import java.io.IOException;
import java.io.PrintWriter;

class FilesExamples{
    
    public static void main(String[] args){
        try{
            String input;
            
            // touch toto1.txt
            Path p1 = Paths.get("toto1.txt").toAbsolutePath();
            Files.createFile(p1); // que si n'existe pas (sinon FileAlreadyExistsException)
            
            // cat "du contenu" > toto1.txt
            PrintWriter out = new PrintWriter(p1.toFile()); // TWR préférable ici (pas besoin de close())
            out.println("du contenu");
            out.close();
            
            // cp toto1.txt toto2.txt
            Path p2 = p1.resolveSibling("toto2.txt");
            Files.copy(p1, p2);
            
            System.out.print("toto1.txt et toto2.txt ont été créés - appuyez sur 'Enter' pour les effacer");
            input = System.console().readLine();

            // rm toto1.txt toto2.txt
            Files.delete(p1);
            Files.delete(p2);
            
            // mkdir -p test/{dir1,dir2,dir3/dir4}
            Files.createDirectories(p1.resolveSibling("test/dir1"));
            Files.createDirectories(p1.resolveSibling("test/dir2"));
            Files.createDirectories(p1.resolveSibling("test/dir3/dir4"));
            
            Path test = p1.resolveSibling("test");
            
            // for i in $(tree -fi test | grep test); do touch $i/test.txt; done;
            Files.walkFileTree(test, new SimpleFileVisitor<Path>() {
                 @Override public FileVisitResult postVisitDirectory(Path dir, IOException e) throws IOException {
                     if (e == null) {
                         Files.createFile(dir.resolve("test.txt"));
                         return FileVisitResult.CONTINUE;
                     } else {
                         throw e; // directory iteration failed
                     }
                 }
             });
            
            System.out.print("test/ a été créé - appuyez sur 'Enter' pour l'effacer");
            input = System.console().readLine();
            
            // rm -fr test/
            Files.walkFileTree(test, new SimpleFileVisitor<Path>() {
                 @Override public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                     Files.delete(file);
                     return FileVisitResult.CONTINUE;
                 }
                 @Override public FileVisitResult postVisitDirectory(Path dir, IOException e) throws IOException {
                     if (e == null) {
                         Files.delete(dir);
                         return FileVisitResult.CONTINUE;
                     } else {
                         throw e; // directory iteration failed
                     }
                 }
             });
        }
        catch(IOException e){
            e.printStackTrace();
        }
                
    }
}
