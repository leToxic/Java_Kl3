package wassersteande;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * Created: 17.11.2022 at 11:17
 *
 * @author Plasek Sebastian
 */
public class WasserStandAnalyse {
    private final Map<LocalDateTime, Integer> levels;
    private static final String pattern = "dd.MM.yyyy HH:mm";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(pattern);


    public WasserStandAnalyse(String filePath) throws IOException {
        this.levels = readFromFile(filePath);
    }


    public Map<LocalDateTime, Integer> readFromFile(String filePath) throws IOException {
        Map<LocalDateTime, Integer> map = new TreeMap<>();

        try (Scanner scan = new Scanner(new File(filePath))) {
            while (scan.hasNext()) {
                try {
                    String[] arr = scan.nextLine().split("\t");
                    LocalDateTime dateTime = LocalDateTime.parse(arr[0], FORMATTER);
                    map.put(dateTime, Integer.valueOf(arr[2]));

                } catch (NumberFormatException | DateTimeParseException | IndexOutOfBoundsException e) {
                    System.out.println("Fehler");
                }
            }
        }
        return map;
    }

    public Map<LocalDateTime, Integer> highest(LocalDateTime from, LocalDateTime to) {
        Map<LocalDateTime, Integer> temp = ((NavigableMap<LocalDateTime, Integer>) this.levels).subMap(from, true, to, true);
        Map<LocalDateTime, Integer> ret = new TreeMap<>();
        int high = 0;

        for (Integer i : temp.values()) {
            if (high < i) {
                high = i;
            }
        }

        for (LocalDateTime l : temp.keySet()) {
            if (temp.get(l) == high) {
                ret.put(l, high);
            }
        }
        return ret;
    }

    public double average(LocalDateTime from, LocalDateTime to) {
        Map<LocalDateTime, Integer> temp = ((NavigableMap<LocalDateTime, Integer>) this.levels).subMap(from, true, to, true);
        Double sum = 0.0;

        if (temp.size() == 0) {
            return 0.0;
        }

        for (Integer i : temp.values()) {
            sum += i;
        }

        return sum / temp.values().size();
    }

    public static LocalDateTime parseDate(String toParse) {
        return LocalDateTime.parse(toParse, FORMATTER);
    }

    public Map<LocalDateTime, Integer> getLevels() {
        return levels;
    }

    public static void main(String[] args) throws IOException {
        WasserStandAnalyse ws = new WasserStandAnalyse("resources/Datei_Wasserstand_data_25268_W_MONTH.txt");
        LocalDateTime first = (LocalDateTime) ws.getLevels().keySet().toArray()[0];
        LocalDateTime last = (LocalDateTime) ws.getLevels().keySet().toArray()[ws.getLevels().keySet().size() - 1];

        System.out.println(ws.average(first, last));
        System.out.println(ws.highest(first, last).keySet().size());


    }


}
