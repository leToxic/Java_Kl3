package DiceWare_Aktien;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DiceWare {

    private Map<Integer, String> diceWarePaare;


    public DiceWare(String filename) {
        this.diceWarePaare = getDiceWarePaare(filename);
    }


    public Map<Integer, String> getDiceWarePaare(String filename) {
        this.diceWarePaare = new HashMap<>();

        try (Scanner scan = new Scanner(new File(filename))) {
            while (scan.hasNext()) {
                String[] scanned = scan.nextLine().split(" ");
                this.diceWarePaare.put(Integer.parseInt(scanned[0]), scanned[1]);
            }

        } catch (FileNotFoundException | NumberFormatException e) {
            System.out.println("Fehler: " + e.getMessage() + " | " + e.getClass().getSimpleName());
        }


        return this.diceWarePaare;
    }

    public Integer wuerfeln() {
        StringBuilder ret = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            ret.append(rand.nextInt((6 - 1) + 1) + 1);
        }
        try {
            return Integer.parseInt(ret.toString());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }


    public String getRandomPairs(int range) {
        Set<String> ret = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        while (ret.size() < range) {
            Integer wurf = wuerfeln();
            String word = this.diceWarePaare.get(wurf);
            ret.add(word);
        }

        for (String s : ret) {
            sb.append(s).append(" ");
        }

        return sb.toString();

    }


    public static void main(String[] args) {
        DiceWare dc = new DiceWare("resources/diceware_aktien/diceware_german.txt");
        System.out.println(dc.getRandomPairs(5));
    }


}