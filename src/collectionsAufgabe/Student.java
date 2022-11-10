package collectionsAufgabe;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created: 20.10.2022 at 17:26
 *
 * @author Plasek Sebastian
 */
public class Student implements Comparable<Student> {
    private String ln;
    private String fn;
    private Integer mtrklNr;
    public static Set<Integer> arr = new TreeSet<>();

    public Student(String ln, String fn, int mtrklNr) {
        if (arr.add(mtrklNr)) {
            setLn(ln);
            setFn(fn);
            setMtrklNr(mtrklNr);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public void setLn(String ln) {
        this.ln = ln;
    }

    public void setMtrklNr(Integer mtrklNr) {
        this.mtrklNr = mtrklNr;
    }

    public Integer getMtrklNr() {
        return mtrklNr;
    }

    public String getFn() {
        return fn;
    }

    public String getLn() {
        return ln;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(mtrklNr, student.mtrklNr);
    }

    @Override
    public int hashCode() {
        return this.mtrklNr.hashCode();
    }

    @Override
    public int compareTo(Student o) {
        int ret = this.fn.compareTo(o.fn);
        if (ret == 0) {
            ret = this.ln.compareTo(o.ln);
        }
        return ret;
    }


    @Override
    public String toString() {
        return this.fn + ", " + this.ln + "(" + this.mtrklNr + ")";
    }

    public static void main(String[] args) {
        try {
            Student s1 = new Student("Muster", "Thomas", 123456);
            Student s2 = new Student("Herbert", "Franz", 111111);
            Student s3 = new Student("Malt", "David R.", 323232);
            new Student("", "", 111111);


            System.out.println(arr);
        } catch (IllegalArgumentException i) {
            System.out.println("Matrikelnummer schon vorhanden");
        }
    }


}
