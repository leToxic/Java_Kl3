package collectionsAufgabe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created: 20.10.2022 at 11:23
 *
 * @author Plasek Sebastian
 */
public class PhonebookEntry implements Comparable<PhonebookEntry>{
    private String name;
    private String number;

    public PhonebookEntry(String name, String number) {
        setName(name);
        setNumber(number);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhonebookEntry that = (PhonebookEntry) o;
        return this.number.equals(that.number);
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        if (number.matches("^0[1-9][0-9]*$") || number.matches("^00[1-9][0-9]*$") || number.matches("^\\+[1-9][0-9]*$")) {
            this.number = number;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public int compareTo(PhonebookEntry o) {
        int compare = this.name.compareTo(o.name);
        if(compare == 0) {
            compare = this.number.compareTo(o.number);
        }
        return compare;
    }

    @Override
    public String toString() {
        return this.name + ": " + this.number;
    }


    public static void main(String[] args) {
        List<PhonebookEntry> arr = new ArrayList<>();

        arr.add(new PhonebookEntry("B", "+1234"));
        arr.add(new PhonebookEntry("A", "00123"));
        arr.add(new PhonebookEntry("Z", "0123"));

        Collections.sort(arr);
        System.out.println(arr);

        Collections.reverse(arr);
        System.out.println(arr);

    }
}


