package datentraeger;

/**
 * Created: 18.09.2022 at 10:52
 *
 * @author Plasek Sebastian
 */
public class CD extends Datentreager {
    private boolean readOnly;

    public CD(String titel, double preis, int megaByteGroesse, boolean readOnly) {
        super(titel, preis, megaByteGroesse);
        setReadOnly(readOnly);
    }

    public CD() {
        super();
        setReadOnly(false);
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public boolean getReadOnly() {
        return this.readOnly;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", readOnly: ");
        if(this.readOnly) {
            sb.append("Ja");
        } else {
            sb.append("Nein");
        }
        return sb.toString();
    }
}
