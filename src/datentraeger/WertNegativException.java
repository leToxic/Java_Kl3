package datentraeger;

/**
 * Created: 08.09.2022 at 11:47
 *
 * @author Plasek Sebastian
 */
public class WertNegativException extends RuntimeException {
    public WertNegativException() {
    }
    public WertNegativException(String errormessage) {
        super(errormessage);
    }
}
