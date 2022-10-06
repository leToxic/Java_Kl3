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
        Scanner sc = new Scanner(new File(filename));
        sc.useDelimiter("[\\s,\\.:;?!]+");
        HashSet<String> set = new HashSet<>();

        while (sc.hasNext()) {
            set.add(sc.next());
        }
        return set;
    }

    public static void main(String[] args) {
        try {
            Collection<String> coll1 = getToken("resources/tokens1.txt");
            Collection<String> coll2 = getToken("resources/tokens2.txt");
            coll1.retainAll(coll2);

            List<String> list = new LinkedList<>(coll1);
            Collections.sort(list);

            System.out.println(list);
        } catch (FileNotFoundException f) {
            System.out.println("File was not found. Error: (" + f + ")");
        }
    }

}
