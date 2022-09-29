package datentraeger;

import java.util.Scanner;

/**
 * Created: 18.09.2022 at 11:03
 *
 * @author Plasek Sebastian
 */
public class DatentraegerMain {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);


        Datentreager cd = new DVD("Erinnerungsfotos", 19.99, 65, '+');
        Datentreager dvd = new CD("Jerry Lee Lewis - Great Balls of Fire", 25.99, 20, true);
        System.out.println(cd);
        System.out.println(dvd);
        System.out.println(Datentreager.getGesamtAnzahl());

        dvd.setTitel("Abschiedsfotos");

        try{
            cd.setPreis(-13);
        } catch (WertNegativException wne) {
            System.out.println("Das geht nicht. Negativer Wert");
        }

        System.out.print("\nTitel: ");
        String titel = scan.nextLine();
        System.out.print("\nPreis: ");
        double preis = scan.nextDouble();
        System.out.print("\nGroesse: ");
        int megaByteGroesse = scan.nextInt();
        Datentreager erstellt = new Datentreager(titel, preis, megaByteGroesse);
        System.out.println(erstellt);




    }
}
