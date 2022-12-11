package schuelerverwaltung;

import java.time.*;
import java.util.*;
import java.time.format.*;

public class Schueler implements Comparable<Schueler> {
    private String klasse;
    private String name;
    private String vorname;
    private char geschlecht;
    private LocalDate geboren;
    private String religion;

    private static final String PATTERN = "dd.MM.uuuu";

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    private static final List<Character> AVAILIBLE_GENDERS = new ArrayList<>(Arrays.asList('w', 'm'));

    public Schueler(String klasse, String name, String vorname, Character geschlecht, String geboren, String religion) {
        setGeboren(geboren);
        setName(name);
        setVorname(vorname);
        setKlasse(klasse);
        setGeschlecht(geschlecht);
        setReligion(religion);
    }

    public static Schueler makeSchueler(String var) {
        String[] arr = var.split(";");
        return new Schueler(arr[0], arr[1], arr[2], arr[3].charAt(0), arr[4], arr[5]);
    }

    private void setGeboren(String geboren) {
        this.geboren = LocalDate.parse(geboren, FORMATTER);

    }

    private void setGeschlecht(Character geschlecht) {
        if (AVAILIBLE_GENDERS.contains(geschlecht)) {
            this.geschlecht = geschlecht;
        } else {
            throw new IllegalArgumentException("Geschlecht falsch");
        }
    }

    private void setKlasse(String klasse) {
        String[] arr = klasse.split("");
        try {
            int stufe = Integer.parseInt(arr[0]);
            if (stufe < 8 && stufe > 0) {
                this.klasse = klasse;
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Klasse falsch");
        }
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setReligion(String religion) {
        this.religion = religion;
    }

    private void setVorname(String vorname) {
        this.vorname = vorname;
    }

    @Override
    public int compareTo(Schueler o) {
        if (this == o) {
            return 0;
        }
        if (this.klasse.compareTo(o.klasse) != 0) return this.klasse.compareTo(o.klasse);
        if (this.name.compareTo(o.name) != 0) return this.name.compareTo(o.name);
        if (this.vorname.compareTo(o.vorname) != 0) return this.vorname.compareTo(o.vorname);

        return this.geboren.compareTo(o.geboren);


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Schueler schueler = (Schueler) o;

        if (!klasse.equals(schueler.klasse)) return false;
        if (!name.equals(schueler.name)) return false;
        if (!vorname.equals(schueler.vorname)) return false;
        return geboren.equals(schueler.geboren);
    }

    @Override
    public int hashCode() {
        int result = klasse.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + vorname.hashCode();
        result = 31 * result + geboren.hashCode();
        return result;
    }

    public Character getGeschlecht() {
        return geschlecht;
    }

    public LocalDate getGeboren() {
        return geboren;
    }

    public int getAge(LocalDate date) {
        if(this.geboren.isAfter(date)) {throw new IllegalArgumentException(this.geboren.toString());}

        int age = date.getYear() - this.getGeboren().getYear();
        if(date.getMonth().getValue() >= this.getGeboren().getMonth().getValue()) {
            if(date.getDayOfMonth() >= this.getGeboren().getDayOfMonth()) {
                age += 1;
            }
        }
    return age;
    }

    public String getKlasse() {
        return klasse;
    }

    public String getName() {
        return name;
    }

    public String getReligion() {
        return religion;
    }

    public String getVorname() {
        return vorname;
    }
}