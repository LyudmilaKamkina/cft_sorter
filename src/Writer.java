import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Writer {
public void toWrite(List<String> sortedStrings, Path out){
    try {
        Files.write(out, sortedStrings);
    } catch (IOException e) {
        System.out.println("error of writing");
    } catch (NullPointerException n) {
        System.out.println("no elements to write");
    }
}
}
