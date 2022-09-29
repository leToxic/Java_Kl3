package flugzeug;

import java.util.Random;

/**
 * Created: 08.09.2022 at 12:11
 *
 * @author Plasek Sebastian
 */
public class FlugzeugMain {
    public static void main(String[] args) {
        Flugzeug military = new Militaerflugzeug("C-130", 300000.0, 4, false);
        Flugzeug verkehr = new Vekehrsflugzeug("Boeing 1323", 140000.0, 2, 150);
        System.out.println(military);
        System.out.println(verkehr);
        System.out.println(Flugzeug.getFlugzeugAnzahl());

        verkehr.setBezeichnung("Boing 1444");
        try {
            military.setPreis(-13);
        } catch (WertNegativException wertnegativ) {
            System.out.println("Das wird nicht funktionieren!");
        }

        ((Vekehrsflugzeug) verkehr).setPassagierAnzahl(15);
        Flugzeug[] field = new Flugzeug[30];
        Random rand = new Random();
        for (int i = 0; i < field.length; i++) {
            if (rand.nextInt(2) == 1) {
                field[i] = new Vekehrsflugzeug();
            } else {
                field[i] = new Militaerflugzeug();
            }

        }
        int anzahlVerkehr = 0;
        for (Flugzeug flug : field) {
            if (flug.getClass() == Vekehrsflugzeug.class) {
                anzahlVerkehr++;
            }
        }
        System.out.println("Anzahl Verkehrsflugzeuge: " + anzahlVerkehr);
        System.out.println(Flugzeug.getFlugzeugAnzahl());

        System.out.println(military);
        System.out.println(verkehr);
    }
}
