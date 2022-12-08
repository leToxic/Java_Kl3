package DiceWare_Aktien;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Aktien {

    private Map<String, List<Integer>> stocks;

    public Aktien(String filename) {
        this.stocks = getMapofRatings(filename);
    }


    public static Map<String, List<Integer>> getMapofRatings(String filename) {
        StringBuilder name = new StringBuilder();
        Integer rating = 0;
        Integer price = 0;
        Map<String, List<Integer>> ret = new HashMap<>();

        try (Scanner sc = new Scanner(new File(filename))) {
            sc.nextLine();
            while (sc.hasNext()) {
                String[] arr = sc.nextLine().split(",");

                try {
                    if (arr.length < 3) {
                        throw new IllegalArgumentException("Werte falsch");
                    }

                    for (String s : arr) {
                        boolean ratSet = false;
                        try {
                            int test = Integer.parseInt(s);

                            if (rating == null) {
                                price = test;
                            } else {
                                rating = test;
                            }

                        } catch (NumberFormatException n) {
                            name.append(s);
                        } catch (IndexOutOfBoundsException i) {
                            System.out.println(i.getMessage());
                        }
                    }
                    List<Integer> app = new LinkedList<>();
                    app.add(price);
                    app.add(rating);
                    ret.put(name.toString(), app);


                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }

    public double getBestRatio() {
        Map<String, List<Integer>> map = this.stocks;
        double ret = 0.0;

        for (List<Integer> value : map.values()) {
            ret = value.get(0) * 1.0 / value.get(1);
        }

        return ret;
    }

    public static double getRatio(List<Integer> values) {
        return values.get(0) * 1.0 / values.get(1);
    }

    public Map<String, Double> getShares(double assets, Double maxInvest) {
        Map<String, Double> ret = new LinkedHashMap<>();
        double highestRatio = this.getBestRatio();

        for (String s : this.stocks.keySet()) {
            if (getRatio(this.stocks.get(s)) == highestRatio) {
                double app = this.stocks.get(s).get(0) / (assets * 1.0 * maxInvest);
                ret.put(s, app);
            }
        }

        return ret;

    }


    public static void main(String[] args) {
        Aktien akt = new Aktien("resources/diceware_aktien/stocks.csv");
        Map<String, Double> shares = akt.getShares(100000, 0.2);

        for (String s : shares.keySet()) {
            System.out.println(s + ": " + shares.get(s));
        }

    }
}