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
    private static final String PATTERN = "dd.MM.yyyy HH:mm";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);


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
                    System.out.println("Fehler " + e.getMessage() + " | " + e.getClass().getSimpleName());
                }
            }
        }
        return map;
    }

    public NavigableMap<LocalDateTime, Integer> retFromToMap(LocalDateTime from, LocalDateTime to) {
        return ((NavigableMap<LocalDateTime, Integer>) this.levels).subMap(from, true, to, true);
    }

    public Map<LocalDateTime, Integer> highest(LocalDateTime from, LocalDateTime to) {
        Map<LocalDateTime, Integer> temp = retFromToMap(from, to);
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
        NavigableMap<LocalDateTime, Integer> temp = retFromToMap(from, to);
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

    public static String parseString(LocalDateTime l) {
        return l.toLocalDate() + ", " + l.toLocalTime();
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

        for (LocalDateTime l : ws.getLevels().keySet()) {
            System.out.println(parseString(l) + " | " + ws.getLevels().get(l));
        }

        System.out.println("Test");


    }


}
