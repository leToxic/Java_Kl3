package generics.pair;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PairMapTest {

    @Test
    public void emptyMapHasSize0() {
        PairMap<Integer, String> map = new PairMap<>();

        int size = map.size();

        assertEquals(0, size);
    }

    @Test
    public void sizeIsEqualToNumberOfStoredPairs() {
        PairMap<Integer, String> map = new PairMap<>();
        map.put(128, "A");
        map.put(129, "B");
        map.put(129, "Should not change size");

        int size = map.size();

        assertEquals(2, size);
    }

    @Test
    public void cannotPutNull() {
        PairMap<Integer, String> map = new PairMap<>();

        assertThrows(IllegalArgumentException.class, () -> map.put(null, "A"));
    }

    @Test
    public void puttingNewValueReturnsNull() {
        PairMap<Integer, String> map = new PairMap<>();

        String result = map.put(128, "A");

        assertNull(result);
    }

    @Test
    public void puttingExistingValueReturnsOldValue() {
        PairMap<Integer, String> map = new PairMap<>();
        map.put(128, "A");

        String result = map.put(128, "B");

        assertEquals("A", result);
    }

    @Test
    public void gettingMissingKeyReturnsNull() {
        PairMap<Integer, String> map = new PairMap<>();

        String result = map.get(42);

        assertNull(result);
    }

    @Test
    public void gettingPresentKeyReturnsMostRecentlyStoredValue() {
        PairMap<Integer, String> map = new PairMap<>();
        map.put(128, "A");
        map.put(128, "B");

        String result = map.get(128);

        assertEquals("B", result);
    }
}
