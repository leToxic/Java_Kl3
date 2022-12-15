package schuelerverwaltung;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.*;

public class SchuelerVerwaltung {

    private final Set<Schueler> verwaltung;


    public SchuelerVerwaltung(String filename) throws FileNotFoundException {
        verwaltung = new TreeSet<>();
        try (Scanner sc = new Scanner(new File(filename))) {
            sc.nextLine();
            while (sc.hasNextLine()) {
                try {
                    verwaltung.add(Schueler.makeSchueler(sc.nextLine()));
                } catch (IllegalArgumentException e) {
                    System.out.println("Fehler: " + e.getMessage());
                }
            }

        }
    }

    public Set<Schueler> containsName(String name, boolean komplett) {
        Set<Schueler> ret = new TreeSet<>();

        if (komplett) {
            for (Schueler s : verwaltung) {
                if (s.getName().equals(name)) {
                    ret.add(s);
                }
            }
        } else {
            for (Schueler s : verwaltung) {
                if (s.getName().contains(name)) {
                    ret.add(s);
                }
            }
        }
        return ret;
    }


    public Set<Schueler> getSchuelerFromKlasse(String klasse) {
        Set<Schueler> ret = new TreeSet<>();

        for (Schueler s : verwaltung) {
            if (s.getKlasse().equals(klasse)) {
                ret.add(s);
            }
        }
        return ret;
    }


    public Set<Schueler> getAllWith(char geschlecht) {
        Set<Schueler> ret = new TreeSet<>();

        for (Schueler s : verwaltung) {
            if (s.getGeschlecht() == geschlecht) ret.add(s);
        }
        return ret;
    }

    public Set<Schueler> getGeborenBis(LocalDate datum, boolean vorNach) {
        Set<Schueler> ret = new TreeSet<>();

        if (vorNach) {
            for (Schueler s : verwaltung) {
                if (s.getGeburtstag().compareTo(datum) <= 0) {
                    ret.add(s);
                }
            }
        } else {
            for (Schueler s : verwaltung) {
                if (s.getGeburtstag().compareTo(datum) > 0) {
                    ret.add(s);
                }
            }
        }
        return ret;
    }

    public Map<String, Integer> getKlassenAnzahl() {
        Map<String, Integer> ret = new TreeMap<>();
        for (Schueler s : verwaltung) {
            Integer anzahl = ret.getOrDefault(s.getKlasse(), 0);
            ret.put(s.getKlasse(), anzahl + 1);


        }
        return ret;
    }

    public Map<String, Map<String, List<String>>> getReligionsZugehoerigkeit() {
        Map<String, Map<String, List<String>>> ret = new TreeMap<>();

        for (Schueler s : verwaltung) {
            if (ret.containsKey(s.getReligion())) {
                Map<String, List<String>> mapOfKlasse = ret.get(s.getReligion());
                if (mapOfKlasse.containsKey(s.getKlasse())) {
                    mapOfKlasse.get(s.getKlasse()).add(s.getName());
                } else {
                    List<String> listOfNames = new ArrayList<>();
                    listOfNames.add(s.getName());
                    mapOfKlasse.put(s.getKlasse(), listOfNames);
                }
            } else {
                Map<String, List<String>> klasseUndSchueler = new HashMap<>();
                List<String> listOfNames = new ArrayList<>();
                listOfNames.add(s.getName());
                klasseUndSchueler.put(s.getKlasse(), listOfNames);
                ret.put(s.getReligion(), klasseUndSchueler);
            }

        }
        return ret;
    }

    public Map<LocalDate, Set<String>> getGeburtstagsListe(int jahr) {

        Map<LocalDate, Set<String>> ret = new TreeMap<>();

        if (jahr <= 1901) return ret;

        for (Schueler s : verwaltung) {
            try {
                LocalDate sGeburtstag = s.getGeburtstag();
                LocalDate gebDiesesJahr = LocalDate.of(jahr, sGeburtstag.getMonth(), sGeburtstag.getDayOfMonth());

                if (ret.containsKey(gebDiesesJahr)) {
                    ret.get(gebDiesesJahr).add(s.getName() + " " + s.getVorname() + " " + s.getAge(gebDiesesJahr));
                } else {
                    Set<String> setOfGeburtstagsSchueler = new TreeSet<>();
                    setOfGeburtstagsSchueler.add(s.getName() + " " + s.getVorname() + " " + s.getKlasse() + " " + s.getAge(gebDiesesJahr));
                    ret.put(gebDiesesJahr, setOfGeburtstagsSchueler);
                }

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return ret;

    }

    public Map<LocalDate, Set<String>> getGeburtstagsListe() {
        return getGeburtstagsListe(LocalDate.now().getYear());
    }
}