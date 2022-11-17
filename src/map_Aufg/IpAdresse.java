package map_Aufg;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created: 10.11.2022 at 11:34
 *
 * @author Plasek Sebastian
 */
public class IpAdresse implements Comparable<IpAdresse> {
    private String adresse;

    public IpAdresse(String adresse) {
        if (adresse == null || adresse.isBlank()) {
            throw new IllegalArgumentException("IP Adresse ist ungültig!");
        }
        setAdresse(adresse);
    }

    private void setAdresse(String adresse) {
        if (adresse.contains(":")) {
            adresse = adresse.substring(0, adresse.indexOf(":"));
        }

        String[] splittedString = adresse.split("\\.");
        for (String s : splittedString) {
            if (splittedString.length != 4 || Integer.parseInt(s) < 0 || Integer.parseInt(s) > 255) {
                throw new IllegalArgumentException("IP Adresse ist nicht gültig!");
            }
        }

        this.adresse = adresse;
    }

    public String getAdresse() {
        return adresse;
    }

    @Override
    public int compareTo(IpAdresse o) {
        return this.hashCode() - o.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        IpAdresse adr = (IpAdresse) o;
        return Objects.equals(this.adresse, adr.adresse);
    }

    @Override
    public int hashCode() {
        return this.adresse != null ? this.adresse.hashCode() : 0;
    }

    public String toString() {
        return this.getAdresse();
    }

    public static Map<IpAdresse, Set<IpAdresse>> getAdressesFromFile(String filePath) throws FileNotFoundException {
        Map<IpAdresse, Set<IpAdresse>> map = new HashMap<>();
        try (Scanner scan = new Scanner(new File(filePath))) {
            scan.nextLine();
            String[] s;
            while (scan.hasNext()) {
                s = scan.nextLine().split(" ");
                if (s.length != 2 || s[0].isBlank() || s[1].isBlank()) {
                    throw new IllegalArgumentException("Ip Adressen nicht in gewünschter Form angegeben");
                }
                s[1] = s[1].split(":")[0];
                IpAdresse value = new IpAdresse(s[0]);
                IpAdresse key = new IpAdresse(s[1]);
                if (map.containsKey(key)) {
                    map.get(key).add(value);
                } else {
                    Set<IpAdresse> set = new TreeSet<>();
                    set.add(value);
                    map.put(key, set);
                }
            }
        }
        return map;
    }


    public static void printIpAdresses(String filePath) throws FileNotFoundException {
        Map<IpAdresse, Set<IpAdresse>> map = getAdressesFromFile(filePath);
        Set<IpAdresse> keys = map.keySet();

        for (IpAdresse key : keys) {
            System.out.println(key);
            for (IpAdresse ipAdresse : map.get(key)) {
                System.out.println("\t" + ipAdresse);
            }
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        try {
            printIpAdresses("resources/firewall.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}

