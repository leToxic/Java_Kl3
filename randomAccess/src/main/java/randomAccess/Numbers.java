package randomAccess;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

/**
 * Created: 26.01.2023 at 12:06
 *
 * @author Plasek Sebastian
 */
public class Numbers {

    public static List<Number> getContents(String filename) throws IOException {
        List<Number> ret = new ArrayList<>();
        try (RandomAccessFile file = new RandomAccessFile(filename, "r")) {
            while (true) {
                try {
                    if (file.readBoolean()) {
                        ret.add(file.readDouble());
                    } else {
                        ret.add(file.readInt());
                    }
                } catch (EOFException eof) {
                    if (file.length() != file.getFilePointer()) {
                        throw new IllegalArgumentException(filename);
                    } else {
                        break;
                    }
                }
            }
        }
        return ret;
    }

    public static Map<String, Set<Number>> groupByType(List<? extends Number> numbers) {
        Map<String, Set<Number>> ret = new TreeMap<>();
        ret.put("Double", new TreeSet<>());
        ret.put("Integer", new TreeSet<>());
        for (Number number : numbers) {
            if (number instanceof Double) {
                ret.get("Double").add(number);
            } else {
                ret.get("Integer").add(number);
            }
        }

        return ret;
    }

    public static void createFile(String filename, List<? extends Number> numbers) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(filename, "rw")) {
            for (Number number : numbers) {
                if (number instanceof Double) {
                    file.writeDouble((Double) number);
                } else {
                    file.writeInt((Integer) number);
                }
            }
        }
    }
}

