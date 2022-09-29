package flugzeug;

/**
 * Created: 08.09.2022 at 12:07
 *
 * @author Plasek Sebastian
 */
public class Vekehrsflugzeug extends Flugzeug {
    private int passagierAnzahl;

    public Vekehrsflugzeug(String bezeichnung, double preis, int triebwerksAnzahl, int passagierAnzahl) {
        super(bezeichnung, preis, triebwerksAnzahl);
        setPassagierAnzahl(passagierAnzahl);
    }

    public Vekehrsflugzeug() {
        super();
        setPassagierAnzahl(1);
    }

    public void setPassagierAnzahl(int passagierAnzahl) {
        if (passagierAnzahl < 1) {
            throw new WertNegativException();
        }
        this.passagierAnzahl = passagierAnzahl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", Passagierplatzanzahl: ").append(passagierAnzahl);
        return sb.toString();
    }
}
