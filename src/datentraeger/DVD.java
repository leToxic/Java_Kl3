package datentraeger;

/**
 * Created: 18.09.2022 at 10:55
 *
 * @author Plasek Sebastian
 */
public class DVD extends Datentreager {
    private char plusOderMinus;

    public DVD(String titel, double preis, int megaByteGroesse, char plusOderMinus) {
        super(titel, preis, megaByteGroesse);
        setPlusOderMinus(plusOderMinus);
    }

    public void setPlusOderMinus(char plusOderMinus) {
        this.plusOderMinus = plusOderMinus;
    }

    public char getPlusOderMinus() {
        return plusOderMinus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", Plus oder Minus: ").append(plusOderMinus);
        return sb.toString();
    }
}
