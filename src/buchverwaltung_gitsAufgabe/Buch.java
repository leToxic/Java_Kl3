package buchverwaltung_gitsAufgabe;

import java.time.LocalDate;

/**
 * Created: 22.12.2022 at 11:21
 *
 * @author Plasek Sebastian
 */
public class Buch implements Comparable<Buch> {

    private static Integer lastId = 0;
    private Integer id;
    private double preis;
    private String titel;
    private String autor;
    private int seiten;
    private int jahr;

    public static Buch fromCSV(String info, char trenner) {
        if (info.contains("\"")) {
            String name = info.substring(info.indexOf("\""), info.indexOf("\"", 2));
            String[] splitted = info.substring(info.indexOf("\"", 2)).split(String.valueOf(trenner));
            if (splitted.length != 4) {
                throw new IllegalArgumentException("Falsche Parameter");
            }
            return new Buch(name, splitted[0], splitted[1], splitted[2], splitted[3]);
        } else {
            String[] splitted = info.split(String.valueOf(trenner));

            if (splitted.length != 5) {
                throw new IllegalArgumentException("Falsche Parameter");
            }

            return new Buch(splitted[0], splitted[1], splitted[2], splitted[3], splitted[4]);
        }
    }

    private Buch(String titel, String autor, String preis, String seiten, String jahr) {
        setAutor(autor);
        setId();
        setJahr(jahr);
        setSeiten(seiten);
        setTitel(titel);
        setPreis(preis);
    }

    private void setTitel(String titel) {
        this.titel = titel;
    }

    private void setPreis(String preis) {
        double preisParsed = Double.parseDouble(preis);

        if (!(preisParsed > 0)) {
            throw new IllegalArgumentException("Preis negativ");
        }
        this.preis = preisParsed;
    }

    private void setPreis(double preis) {
        if (!(preis > 0)) {
            throw new IllegalArgumentException("Preis negativ");
        }
        this.preis = preis;
    }

    private void setAutor(String autor) {
        this.autor = autor;
    }

    private void setId() {
        this.id = lastId + 1;
        lastId += 1;
    }

    private void setJahr(String jahr) {
        int jahrParsed = Integer.parseInt(jahr);

        if (LocalDate.now().getYear() < jahrParsed) {
            throw new IllegalArgumentException("Jahr noch nicht erreicht!");
        }
        this.jahr = jahrParsed;
    }

    private void setSeiten(String seiten) {
        int seitenParsed = Integer.parseInt(seiten);

        if (!(seitenParsed > 0)) {
            throw new IllegalArgumentException("Seiten negativ");
        }
        this.seiten = seitenParsed;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Buch buch = (Buch) o;

        if (!(id.equals(buch.id))) return false;
        if (!titel.equals(buch.titel)) return false;
        return autor.equals(buch.autor);
    }

    @Override
    public int hashCode() {
        Integer result = id;
        result = 31 * result + titel.hashCode();
        result = 31 * result + autor.hashCode();
        return result;
    }

    @Override
    public int compareTo(Buch b) {
        if (this.autor.compareTo(b.autor) != 0) return this.autor.compareTo(b.autor);
        if (this.titel.compareTo(b.titel) != 0) return this.titel.compareTo(b.titel);
        return this.id.compareTo(b.id);
    }

    public void rabatt(Double percent) {
        this.setPreis(this.getPreis() * (percent / 100.0));
    }

    public String getTitel() {
        return titel;
    }

    public double getPreis() {
        return preis;
    }

    public int getId() {
        return id;
    }

    public int getJahr() {
        return jahr;
    }

    public int getSeiten() {
        return seiten;
    }

    public String getAutor() {
        return autor;
    }

    @Override
    public String toString() {
        return "Buch{" +
                "id=" + id +
                ", preis=" + preis +
                ", titel='" + titel + '\'' +
                ", autor='" + autor + '\'' +
                '}';
    }
}
