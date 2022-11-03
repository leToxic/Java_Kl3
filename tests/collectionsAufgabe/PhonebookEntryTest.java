package collectionsAufgabe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created: 20.10.2022 at 13:03
 *
 * @author Plasek Sebastian
 */
class PhonebookEntryTest {

    PhonebookEntry publ = new PhonebookEntry("Testing", "+1234");

    @Test
    void setName() {
        assertEquals("TestingSetName", new PhonebookEntry("TestingSetName", "001234").getName());
    }

    @Test
    void setNumber() {
        assertEquals("+1234", new PhonebookEntry("TestingSetNumber", "+1234").getNumber());
        assertThrows(IllegalArgumentException.class,() -> new PhonebookEntry("TestingSetNumber", "0001234"));
    }
}