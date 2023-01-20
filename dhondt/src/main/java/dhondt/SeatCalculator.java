package dhondt;


import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class SeatCalculator {

    private final TreeSet<Party> setOfParties;

    public SeatCalculator(Set<String> parties) {
        if (parties == null || parties.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.setOfParties = new TreeSet<>();
        for (String party : parties) {
            this.setOfParties.add(new Party(party, 0L, 0));
        }
    }

    public void changeSeatsOfParties(Map<String, Long> votesPerParty, Map<String, Integer> returnMap, int seats) {
        int seatsUsed = 0;
        Party highestScored = this.setOfParties.first();

        while (!(seatsUsed == seats)) {
            this.setOfParties.forEach(p -> {
                if (!(votesPerParty.containsKey(p.getName()))) {
                    throw new IllegalArgumentException();
                }
                if (p.getSeats() >= 1) {
                    p.changingVotes = p.getVotes() / (p.getSeats() + 1.0);
                }
            });

            for (Party party : this.setOfParties) {
                if (party.changingVotes >= highestScored.changingVotes) {
                    highestScored = party;
                }
            }

            highestScored.setSeats(highestScored.getSeats() + 1);
            returnMap.put(highestScored.getName(), highestScored.getSeats());
            seatsUsed++;
        }
    }

    /**
     * Berechnet die Sitz-/Mandatsverteilung nach D'Hondt
     *
     * @param votesPerParty für jede Partei die Anzahl an abgegeben Stimmen für diese Partei, darf nicht leer sein
     * @param seats         Anzahl zu vergebender Sitze/Mandate, muss positiv sein
     * @return für jede Partei die Anzahl ihr zugeteilter Sitze/Mandate
     */
    public Map<String, Integer> calculate(Map<String, Long> votesPerParty, int seats) {
        if (seats <= 0 || votesPerParty.isEmpty()) {
            throw new IllegalArgumentException();
        }
        for (String partyName : votesPerParty.keySet()) {
            this.setOfParties.stream().filter(p -> p.getName().equals(partyName)).forEach(p -> p.setVotes(votesPerParty.get(partyName)));
        }

        Map<String, Integer> ret = new TreeMap<>();
        this.setOfParties.forEach(p -> ret.put(p.getName(), p.getSeats()));

        // Alle Key:Value Pairs wo Value <= 0 sollen noch aus der Map removed werden
        //ret.keySet().stream().filter(p -> ret.get(p) > 0).forEach(ret::remove);

        for (String partyName : new TreeMap<>(ret).keySet()) {
            if (ret.get(partyName) <= 0) {
                ret.remove(partyName);
            }
        }
        this.changeSeatsOfParties(votesPerParty, ret, seats);
        return ret;
    }
}
