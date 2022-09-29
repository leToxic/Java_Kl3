package generics.Bottle;

/**
 * Created: 29.09.2022 at 11:30
 *
 * @author Plasek Sebastian
 */
public class Bottle<T extends Drink> {
    private T content;

    public Bottle(T content) {
        this.content = content;
    }

    public Bottle() {
    }

    public void fill(T content) {
        this.content = content;
    }

    public boolean isEmpty() {
        return content == null;
    }

    public T empty() {
        T ret = this.content;
        this.content = null;
        return ret;
    }

    @Override
    public String toString() {
        return "A bottle with: " + this.content;
    }
}
