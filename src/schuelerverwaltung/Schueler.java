package schuelerverwaltung;

import java.time.*;
import java.time.format.*;

public class Schueler implements Comparable<Schueler> {
    private String klasse;
    private String name;
    private String vorname;
    private char geschlecht;
    private LocalDate geburtstag;
    private String religion;


    static final public DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.uuuu").withResolverStyle(ResolverStyle.STRICT);


    public static Schueler makeSchueler(String input) {
        String[] arr = input.split(";");
        return new Schueler(arr[0], arr[1], arr[2], arr[3].charAt(0), LocalDate.parse(arr[4], DATE_TIME_FORMATTER), arr[5]);
    }


    public Schueler(String klasse, String name, String vorname, char geschlecht, LocalDate geburtstag, String religion) {
        setKlasse(klasse);
        setName(name);
        setVorname(vorname);
        setGeschlecht(geschlecht);
        setGeburtstag(geburtstag);
        setReligion(religion);
    }

    public void setGeschlecht(char geschlecht) {
        if (geschlecht == 'm' || geschlecht == 'w') {
            this.geschlecht = geschlecht;
        } else {
            throw new IllegalArgumentException("Falsches Geschlecht");
        }
    }

    public void setGeburtstag(LocalDate geburtstag) {
        if (!geburtstag.isAfter(LocalDate.now())) {
            this.geburtstag = geburtstag;
        } else {
            throw new IllegalArgumentException("Schüler noch nicht geboren");
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKlasse(String klasse) {
        this.klasse = klasse;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Schueler schueler = (Schueler) o;

        return this.hashCode() == schueler.hashCode() && this.compareTo(schueler) == 0;
    }

    @Override
    public int hashCode() {
        int result = klasse.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + vorname.hashCode();
        result = 31 * result + geburtstag.hashCode();
        return result;
    }

    @Override
    public int compareTo(Schueler o) {
        if (this == o) {
            return 0;
        }
        if (this.klasse.compareTo(o.klasse) != 0) return this.klasse.compareTo(o.klasse);
        if (this.name.compareTo(o.name) != 0) return this.name.compareTo(o.name);
        if (this.vorname.compareTo(o.vorname) != 0) return this.vorname.compareTo(o.vorname);

        return this.geburtstag.compareTo(o.geburtstag);

    }

    public int getAge(LocalDate ld) {
        if (geburtstag.isAfter(ld)) throw new IllegalArgumentException(ld.toString());
        else return Period.between(geburtstag, ld).getYears();
    }


    public String getReligion() {
        return religion;
    }

    public String getKlasse() {
        return klasse;
    }

    public String getName() {
        return name;
    }

    public String getVorname() {
        return vorname;
    }

    public char getGeschlecht() {
        return geschlecht;
    }

    public LocalDate getGeburtstag() {
        return geburtstag;
    }

}