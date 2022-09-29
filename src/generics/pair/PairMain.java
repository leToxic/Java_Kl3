package generics.pair;

import java.util.Objects;

/**
 * Created: 29.09.2022 at 12:04
 *
 * @author Plasek Sebastian
 */
public class PairMain {
    public static void main(String[] args) {


        Brot brot = new Brot();
        Wurst wurst = new Wurst();

        Pair<Brot, Wurst> wurstbrot = new Pair<>(brot, wurst);
        Brot brotRet = wurstbrot.getContent1();
        Wurst wurstRet = wurstbrot.getContent2();

        Pair<Integer, String> pair = new Pair<>(3, "if");
        Pair<String, Integer> flipped = pair.flip();

        System.out.println(Objects.equals(flipped.getContent2(), pair.getContent1()));

    }
}
