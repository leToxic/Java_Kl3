package DiceWare_Aktien;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Aktienuebersicht {
    public static Set<Aktien> readFromFile(String filename) {
        Set<Aktien> aktien = new TreeSet<>();
        try (Scanner sc = new Scanner(new File(filename))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                try {
                    if (line.contains("\"")) {
                        String[] lineArray = line.split("\"");
                        String name = lineArray[1];
                        lineArray = lineArray[2].split(",");
                        aktien.add(new Aktien(name, lineArray[1], lineArray[2]));

                    } else {
                        String[] lineArray = line.split(",");
                        aktien.add(new Aktien(lineArray[0], lineArray[1], lineArray[2]));
                    }
                } catch (IllegalArgumentException e) {
                    //  System.out.println(e);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return aktien;
    }

    public static Map<String, Integer> aktienPortfolio(Set<Aktien> aktien, int budget) {
        Map<String, Integer> portfolio = new LinkedHashMap<>();
        double budgetForeach = budget * 0.2;
        for (Aktien items : aktien) {
            if (budget < budgetForeach) {
                budgetForeach = budget;
            }
            int stockcount = (int) Math.floor(budgetForeach / items.getPrice());
            int cost = stockcount * items.getPrice();
            if (cost <= budgetForeach && stockcount != 0) {
                portfolio.put(items.getName(), stockcount);
                budget = budget - cost;
            }

        }

        portfolio.put("Leftover Money: ", budget);

        portfolio.forEach((k, v) -> {
            if (k.contains("Leftover Money: ")) {
                System.out.println(k + v);
            } else {
                System.out.println(k + ": " + v + " Shares");
            }
        });


        return portfolio;

    }


    public static void main(String[] args) {
        System.out.println(aktienPortfolio(readFromFile("resources/diceware_aktien/stocks.csv"), 100000));

    }


}
