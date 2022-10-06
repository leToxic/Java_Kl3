package token;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created: 06.10.2022 at 11:33
 *
 * @author Plasek Sebastian
 */
public class Token {

    public static Collection<String> getToken(String filename) throws FileNotFoundException {
        try(Scanner scanner = new Scanner(new File(filename))) {
            scanner.useDelimiter("[\\s,\\.:;?!]+");
            Set<String> set = new HashSet<>();

            while (scanner.hasNext()) {
                set.add(scanner.next());
            }
            return set;
        }
    }

    public static List<String> getSortedTokens(Collection<String> token1, Collection<String> token2) {
        token1.retainAll(token2);

        List<String> tokens = new LinkedList<>(token1);

        Collections.sort(tokens);
        return tokens;
    }

    public static void main(String[] args) {
        try {
            Collection<String> token1 = getToken("resources/tokens1.txt");
            Collection<String> token2 = getToken("resources/tokens2.txt");

            System.out.println(getSortedTokens(token1, token2));
        } catch (FileNotFoundException f) {
            System.out.println("File was not found. Error: (" + f + ")");
        }
    }

}
