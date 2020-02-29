import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        final ForkJoinPool fjPool = new ForkJoinPool();

        String text = "";
        String word = "the";

        try {
            text = new String(Files.readAllBytes(Paths.get("alice.txt")));
        } catch (IOException e) {
            System.out.println("Unable to open file.......");
            System.out.println("Exiting.....");
            System.exit(-1);
        }
        text = text.replaceAll("[^a-zA-Z ]+"," ");
        text = text.replaceAll("[\r\n]+","");
        String[] textArr = text.split(" ");

        FindWord aa = new FindWord(textArr, word);
        fjPool.invoke(aa);

        System.out.println("There are " + aa + " instances of " + "\"" + word + "\"");
    }
}
