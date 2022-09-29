package generics.Bottle;

/**
 * Created: 29.09.2022 at 11:31
 *
 * @author Plasek Sebastian
 */
public abstract class Wine extends Drink{

    private String region;

    public Wine(String region) {
        this.region = region;
    }

    public Wine() {
        this.region = "";
    }

    public String getRegion() {
        return region;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()+ ": " + this.region;
    }
}
