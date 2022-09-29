package generics.pair;

/**
 * Created: 29.09.2022 at 12:00
 *
 * @author Plasek Sebastian
 */
public class Pair<T, U> {
    private T content1;
    private U content2;

    public Pair(T content1, U content2) {
        this.content1 = content1;
        this.content2 = content2;
    }

    public T getContent1() {
        return content1;
    }

    public void setContent1(T content1) {
        this.content1 = content1;
    }

    public void setContent2(U content2) {
        this.content2 = content2;
    }

    public U getContent2() {
        return content2;
    }

    public Pair<U, T> flip() {
        return new Pair<U, T>(this.content2, this.content1);
    }

}
