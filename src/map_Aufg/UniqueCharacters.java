package map_Aufg;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created: 03.11.2022 at 12:27
 *
 * @author Plasek Sebastian
 */
public class UniqueCharacters {
    private static final HashMap<String, Integer> map = new HashMap<>();

    public static int getUniqueChars(String toCheck) {

        if (!map.containsKey(toCheck)) {

            Set<Character> uniqueSet = new HashSet<>();

            for (char c : toCheck.toCharArray()) {
                uniqueSet.add(c);
            }

            map.put(toCheck, uniqueSet.size());
            return uniqueSet.size();
        }
        System.out.println("Wert aus Map: ");
        return map.get(toCheck);
    }


    public static void main(String[] args) {
        System.out.println(getUniqueChars("abc"));
        System.out.println(getUniqueChars("abc"));
    }

}

