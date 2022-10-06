package generics.bottle;

/**
 * Created: 29.09.2022 at 11:31
 *
 * @author Plasek Sebastian
 */
public class Beer extends Drink{
    private String brewery;

    public Beer(String brewery) {
        this.brewery = brewery;
    }

    public Beer() {
        this.brewery = "";
    }

    public String getBrewery() {
        return brewery;
    }

    @Override
    public String toString() {
        return "Beer: " + this.brewery;
    }
}
