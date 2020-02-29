import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class FindWord extends RecursiveAction {

    String[] text;
    String wordToFind;
    int count;
    final int CAP = 100;

    public FindWord(String[] text, String word) {
        this.text = text;
        this.wordToFind = word.toLowerCase();
        this.count = 0;
    }

    @Override
    protected void compute() {
        if(text.length < CAP) {
            for(int i = 0; i < text.length; i++) {
                if(text[i].matches(wordToFind)) {
                    count++;
                }
            }
        } else {
            String[] leftText = Arrays.copyOfRange(text, 0, text.length/2);
            String[] rightText = Arrays.copyOfRange(text, text.length/2, text.length);

            FindWord left = new FindWord(leftText, wordToFind);
            FindWord right = new FindWord(rightText, wordToFind);

            left.fork();
            right.fork();
            left.join();
            right.join();

            count = count + left.count + right.count;
        }
    }

    @Override
    public String toString() {
        return "" + count;
    }
}
