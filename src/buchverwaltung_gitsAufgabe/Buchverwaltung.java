package buchverwaltung_gitsAufgabe;

import java.util.*;

/**
 * Created: 22.12.2022 at 12:10
 *
 * @author Plasek Sebastian
 */
public class Buchverwaltung {
    private List<Buch> buecher;

    public boolean verkaufen(int id) {
        for (Buch buch : this.buecher) {
            if (buch.getId() == id) {
                this.buecher.remove(buch);
                return true;
            }
        }
        return false;
    }

    public Map<String, List<Buch>> getAllBooksOfGuy(String guy) {
        List<Buch> filteredList = this.buecher.stream()
                .filter(b -> b.getAutor().equals(guy)).toList();
        Map<String, List<Buch>> ret = new TreeMap<>();
        ret.put(guy, filteredList);
        return ret;
    }

    public Map<String, List<Buch>> getAllBooksOfTitel(String titel) {
        List<Buch> filteredList = this.buecher.stream()
                .filter(b -> b.getTitel().equals(titel)).toList();
        Map<String, List<Buch>> ret = new TreeMap<>();
        ret.put(titel, filteredList);
        return ret;
    }

    public Map<Integer, List<Buch>> getAllBooksOfYear(Integer jahr) {
        List<Buch> filteredList = this.buecher.stream()
                .filter(b -> Integer.valueOf(b.getJahr()).equals(jahr)).toList();
        Map<Integer, List<Buch>> ret = new TreeMap<>();
        ret.put(jahr, filteredList);
        return ret;
    }

    public Integer bestellt(String csvInput, char trenner) {
        this.buecher.add(Buch.fromCSV(csvInput, trenner));
        return this.buecher.get(this.buecher.size() - 1).getId();
    }

    public void ouputAllBooksFromYear(Integer jahr) {
        Map<Integer, List<Buch>> booksOfYears = this.getAllBooksOfYear(jahr);

        for (Integer year : booksOfYears.keySet()) {
            for (Buch buch : booksOfYears.get(year)) {
                System.out.println(buch);
            }
        }
    }

    public void ouputAllBooksFromGuy(String autor) {
        Map<String, List<Buch>> booksOfGuy = this.getAllBooksOfGuy(autor);

        for (String guy : booksOfGuy.keySet()) {
            for (Buch buch : booksOfGuy.get(guy)) {
                System.out.println(buch);
            }
        }
    }

    public void ouputAllBooksThatTitelLike(String titel, String like) {
        Map<String, List<Buch>> booksThatTitelIsLike = this.getAllBooksOfTitel(titel);

        for (String guy : booksThatTitelIsLike.keySet()) {
            booksThatTitelIsLike.get(guy).stream().filter(x -> x.getTitel().contains(like)).forEach(System.out::println);
        }
    }

    public void outputAllBooksThatPriceIsBetween(Integer overThisPrice, Integer overThisSeitenAnz) {
        for (Buch buch : this.buecher) {
            if (buch.getSeiten() > overThisSeitenAnz && buch.getPreis() > overThisPrice) {
                System.out.println(buch);
            }
        }
    }

    public void getDiscountOnBooksOverPages(Integer pages, double rabatt) {
        this.buecher.stream().filter(b -> b.getSeiten() < pages).forEach(b -> b.rabatt(rabatt));
    }

    public void getDiscountOnBooksUnderPages(Integer pages, double rabatt) {
        this.buecher.stream().filter(b -> b.getSeiten() > pages).forEach(b -> b.rabatt(rabatt));
    }

    public void getDiscountOnBooksOfGuy(String guy, double rabatt) {
        this.buecher.stream().filter(b -> b.getAutor().equals(guy)).forEach(b -> b.rabatt(rabatt));

    }


}
