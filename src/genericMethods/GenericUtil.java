package genericMethods;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * Created: 13.10.2022 at 11:26
 *
 * @author Plasek Sebastian
 */
public class GenericUtil {

    public static <T> void printArray(T[] arr) {
        for (T ausgabe : arr) {
            System.out.println(ausgabe);
        }
    }

    public static <T extends Number> double sum(T[] arr) {
        double ret = 0.0;

        for (T num : arr) {
            ret += num.doubleValue();
        }

        return ret;

    }

    public static <T extends Comparable<T>> T maxElement(List<T> arr) {
        T compareThis = arr.get(0);

        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).compareTo(compareThis) > 0) {
                compareThis = arr.get(i);
            }
        }
        return compareThis;
    }

    public static <T extends Number> int countOdds(Collection<T> coll) {
        int counter = 0;
        for (T t : coll) {
            if (t.doubleValue() % 2 != 0) {
                counter++;
            }
        }
        return counter;
    }

    public static <T extends Comparable<T>> void swapElements(List<T> arr, int index1, int index2) {
        if (arr.get(index1).compareTo(arr.get(index2)) > 0) {
            T holding = arr.get(index1);
            arr.set(index2, arr.get(index1));
            arr.set(index1, holding);
        }
    }


    public static <T extends Number> List<T> makeList(Class<T> classType, int anzahl) {
        List<T> retList = new ArrayList<>();
        Random rand = new Random();
        if (classType == Integer.class || classType == Byte.class || classType == Long.class) {
            for (int i = 0; i < anzahl; i++) {
                retList.add((T) Integer.valueOf(rand.nextInt(5, 25)));
            }
        } else if (classType == Double.class || classType == Float.class) {
            for (int i = 0; i < anzahl; i++) {
                retList.add((T) Double.valueOf(rand.nextDouble(50, 100)));
            }

        }
        return retList;
    }

    public static <T extends Comparable<T>> List<T> getGrenzwert(List<T> list, T grenzwert) {
        List<T> retList = new ArrayList<>();

        for (T num : list) {
            if (num.compareTo(grenzwert) > 0) {
                retList.add(num);
            }
        }
        return retList;
    }


    public static void main(String[] args) {
        System.out.println(makeList(Double.class, 10));

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        System.out.println(getGrenzwert(list, 7));

        System.out.println(countOdds(list));
    }

}
