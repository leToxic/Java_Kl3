package DiceWare_Aktien;

public class Aktien implements Comparable<Aktien> {

    private String name;
    private int rating;
    private int price;

    public Aktien(String name, String rating, String price) {
        int rating2 = Integer.parseInt(rating);
        int price2 = Integer.parseInt(price);
        if (rating2 >= 0 && price2 >= 0) {
            this.rating = rating2;
            this.price = price2;
            this.name = name;
        } else {
            throw new IllegalArgumentException("Falsche Eingabe");
        }


    }


    @Override
    public int compareTo(Aktien o) {
        double verhaeltnis = (double) price / rating;
        double verhaeltnis2 = (double) o.price / o.rating;

        int compare = Double.compare(verhaeltnis, verhaeltnis2);

        if (compare == 0) {
            return Integer.compare(price, o.price);
        }

        return compare;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
