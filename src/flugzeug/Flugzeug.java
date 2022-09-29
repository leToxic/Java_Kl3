package flugzeug;

/**
 * Created: 08.09.2022 at 11:43
 *
 * @author Plasek Sebastian
 */
public class Flugzeug {
    private String bezeichnung;
    private double preis;
    private int triebwerksAnzahl;
    private static int flugzeugAnzahl;

    public Flugzeug(String bezeichnung, double preis, int triebwerksAnzahl) {
        setBezeichnung(bezeichnung);
        setPreis(preis);
        setTriebwerksAnzahl(triebwerksAnzahl);
        flugzeugAnzahl++;
    }

    public Flugzeug() {
        this("Null", 0, 1);
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public void setPreis(double preis) {
        if (preis < 0) {
            throw new WertNegativException();
        }
        this.preis = preis;
    }


    private void setTriebwerksAnzahl(int triebwerksAnzahl) {
        if (triebwerksAnzahl <= 0) {
            throw new WertNegativException();
        }
        this.triebwerksAnzahl = triebwerksAnzahl;
    }

    private void anzMinusEins() {
        flugzeugAnzahl--;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.bezeichnung).append(", Preis: ").append(this.preis).append(", Triebwerksanzahl: ").append(this.triebwerksAnzahl);
        return sb.toString();
    }

    public static int getFlugzeugAnzahl() {
        return flugzeugAnzahl;
    }
}
