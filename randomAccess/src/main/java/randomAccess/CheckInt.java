package randomAccess;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * Created: 26.01.2023 at 11:27
 *
 * @author Plasek Sebastian
 */
public class CheckInt {
    public static void createFile(String filename, double... values) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(filename, "rw")) {
            file.setLength(0);
            file.writeInt(values.length);
            for (double value : values) {
                file.writeDouble(value);
            }
        }
    }

    public static boolean isValidFile(String filename) throws IOException {
        try (RandomAccessFile file = new RandomAccessFile(filename, "r")) {
            return file.readInt() * 8L == file.length() - 4;
        }
    }

    public static String getFileInfo(String filename) throws IOException {
        StringBuilder ret = new StringBuilder();

        try (RandomAccessFile file = new RandomAccessFile(filename, "r")) {
            if (isValidFile(filename)) {
                ret.append("Saved values: ").append(file.readInt()).append("\n");
                while (true) {
                    try {
                        ret.append(String.format("%.2f", file.readDouble())).append(" ");
                    } catch (EOFException eof) {
                        break;
                    }
                }
                ret.replace(ret.length() - 1, ret.length(), "");
            } else {
                ret.append("invalid");
            }
        }
        return ret.toString();
    }

    public static void append(String filename, double toAppend) throws IOException {
        if (!isValidFile(filename)) {
            throw new IllegalArgumentException(filename);
        }
        try (RandomAccessFile file = new RandomAccessFile(filename, "rw")) {
            int app = file.readInt() + 1;
            file.seek(0);

            file.writeInt(app);
            file.seek(file.length());
            file.writeDouble(toAppend);
        }
    }
}
