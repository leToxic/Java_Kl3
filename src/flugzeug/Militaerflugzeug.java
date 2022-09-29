package flugzeug;

/**
 * Created: 08.09.2022 at 11:53
 *
 * @author Plasek Sebastian
 */
public class Militaerflugzeug extends Flugzeug {
    private boolean stealthTechnik;

    public Militaerflugzeug(String bezeichnung, double preis, int triebwerksAnzahl, boolean stealthTechnik) {
        super(bezeichnung, preis, triebwerksAnzahl);
        setStealthTechnik(stealthTechnik);
    }

    public Militaerflugzeug() {
        super();
        setStealthTechnik(false);
    }

    public void setStealthTechnik(boolean stealthTechnik) {
        this.stealthTechnik = stealthTechnik;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", Steahltechnik: ");
        if (stealthTechnik) {
            sb.append("Ja");
        } else {
            sb.append("Nein");
        }
        return sb.toString();
    }
}
