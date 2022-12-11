package schuelerverwaltung;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class SchuelerVerwaltung {

    private Collection<Schueler> verwaltung;

    public SchuelerVerwaltung(String filename) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new File(filename))) {
            this.verwaltung = new TreeSet<>();
            sc.next();

            while (sc.hasNext()) {
                this.verwaltung.add(Schueler.makeSchueler(sc.nextLine()));
            }

        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public Set<Schueler> getSchuelerFromKlase(String klasse) {
        Set<Schueler> ret = new TreeSet<>();

        for (Schueler schueler : this.verwaltung) {
            if (schueler.getKlasse().equals(klasse)) {
                ret.add(schueler);
            }
        }
        return ret;
    }

    public Set<Schueler> containsName(String name, boolean komplett) {
        Set<Schueler> ret = new TreeSet<>();

        for (Schueler schueler : this.verwaltung) {
            if (komplett) {

                String[] arr = schueler.getName().trim().split("-|\s");
                for (String s : arr) {
                    if (name.equals(s)) {
                        ret.add(schueler);
                    }
                }
            } else {
                if (schueler.getName().equals(name)) {
                    ret.add(schueler);
                }
            }
        }
        return ret;
    }

    public Set<Schueler> getAllWith(char geschlecht) {
        Set<Schueler> ret = new TreeSet<>();

        for (Schueler schueler : this.verwaltung) {
            if (schueler.getGeschlecht().equals(geschlecht)) {
                ret.add(schueler);
            }
        }
        return ret;
    }

    public Set<Schueler> getGeborenBis(LocalDate datum, boolean vorNach) {
        Set<Schueler> ret = new TreeSet<>();

        for (Schueler schueler : this.verwaltung) {
            if (vorNach) {
                if (schueler.getGeboren().isBefore(datum) || schueler.getGeboren().isEqual(datum)) {
                    ret.add(schueler);
                }
            } else {
                if (schueler.getGeboren().isAfter(datum) || schueler.getGeboren().isEqual(datum)) {
                    ret.add(schueler);
                }
            }
        }
        return ret;
    }

    public Map<String, Integer> getKlassenAnzahl() {
        Map<String, Integer> ret = new TreeMap<>();

        for (Schueler schueler : this.verwaltung) {
            if (ret.containsKey(schueler.getKlasse())) {
                ret.put(schueler.getKlasse(), ret.get(schueler.getKlasse()) + 1);
            } else {
                ret.put(schueler.getKlasse(), 1);
            }
        }
        return ret;
    }


    public Map<String, Map<String, List<String>>> getReligionsZugehoerigkeit() {
        Map<String, Map<String, List<String>>> ret = new TreeMap<>();

        for (Schueler schueler : this.verwaltung) {
            if (ret.containsKey(schueler.getReligion())) {
                Map<String, List<String>> innerMap = ret.get(schueler.getReligion());
                if (innerMap.containsKey(schueler.getKlasse())) {
                    innerMap.get(schueler.getKlasse()).add(schueler.getName());
                } else {
                    List<String> app = new ArrayList<>();
                    app.add(schueler.getName());
                    innerMap.put(schueler.getKlasse(), app);
                }
            } else {
                Map<String, List<String>> app = new TreeMap<>();
                List<String> appList = new ArrayList<>();
                appList.add(schueler.getName());
                app.put(schueler.getKlasse(), appList);
                ret.put(schueler.getReligion(), app);
            }
        }
        return ret;
    }

    public Map<Integer, Set<String>> getGeburtstagsListe(int jahr) {
        Map<Integer, Set<String>> ret = new TreeMap<>();

        for (Schueler schueler : this.verwaltung) {
            if(schueler.getGeboren().getYear() == jahr) {
                if(ret.containsKey(schueler.getGeboren().getYear())) {
                    String sb = schueler.getName() + " " + schueler.getVorname() + " " + schueler.getKlasse() + " " + schueler.getAge(LocalDate.from(LocalDateTime.now()));
                    ret.get(schueler.getGeboren().getYear()).add(sb);
                } else {
                    Set<String> app = new TreeSet<>();
                    app.add(schueler.getName() + " " + schueler.getVorname() + " " + schueler.getKlasse() + " " + schueler.getAge(LocalDate.from(LocalDateTime.now())));
                    ret.put(schueler.getGeboren().getYear(), app);
                }
            }
        }
        return ret;
    }

    public Map<Integer, Set<String>> getGeburtstagsListe() {
        return this.getGeburtstagsListe(LocalDate.from(LocalDate.now()).getYear());
    }


    public Collection<Schueler> getVerwaltung() {
        return verwaltung;
    }
}