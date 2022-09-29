package datentraeger;

/**
 * Created: 18.09.2022 at 10:44
 *
 * @author Plasek Sebastian
 */
public class Datentreager {
    private String titel;
    private double preis;
    private int megaByteGroesse;
    private static int gesamtAnzahl;

    public Datentreager(String titel, double preis, int megaByteGroesse) {
        setTitel(titel);
        setPreis(preis);
        this.megaByteGroesse = megaByteGroesse;
        gesamtAnzahl++;
    }

    public Datentreager() {
        this("N/A", 0, 1);
    }


    public void setPreis(double preis) {
        if(preis < 0) {
            throw new WertNegativException();
        }
        this.preis = preis;
    }

    private void setMegaByteGroesse(int megaByteGroesse) {
        if(megaByteGroesse < 0) {
            throw new WertNegativException();
        }
        this.megaByteGroesse = megaByteGroesse;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public double getPreis() {
        return preis;
    }

    public static int getGesamtAnzahl() {
        return gesamtAnzahl;
    }

    public int getMegaByteGroesse() {
        return megaByteGroesse;
    }

    public String getTitel() {
        return titel;
    }
    @Override
    public String toString() {
        return titel + ", Preis: " + preis + ", Groesse: " + megaByteGroesse;
    }
}
