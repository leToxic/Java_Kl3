package generics.bottle;

/**
 * Created: 29.09.2022 at 11:47
 *
 * @author Plasek Sebastian
 */
public class BottleMain {
    public static void main(String[] args) {
        Bottle<Beer> beer = new Bottle<>();
        beer.fill(new Beer("Zwettler"));
        System.out.println(beer);
        Beer ret = beer.empty();
        System.out.println(beer);

        Bottle<Wine> wine = new Bottle<>();
        wine.fill(new WhiteWine("Kamptal"));
        System.out.println(wine);
        wine.empty();
        System.out.println(wine);
        wine.fill(new RedWine("Carnuntum"));
        System.out.println(wine);
    }
}
