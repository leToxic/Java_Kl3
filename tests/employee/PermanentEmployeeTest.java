package employee;

import employee.fixtures.LocalizedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PermanentEmployeeTest extends LocalizedTest {
    @Test
    void constructor_dailyRateNegative_exception() {
        assertThrows(IllegalArgumentException.class, () ->
                new PermanentEmployee("name", -1));
    }

    @Test
    void constructor_dailyRate0_objectCreated() {
        assertDoesNotThrow(() ->
                new PermanentEmployee("name", 0));
    }

    @Test
    void calculateHourlyRate_returnsRatePerHour() {
        Employee e = new PermanentEmployee("name", 937);

        assertEquals(0.4685, e.calculateHourlyRate());
    }

    @Test
    void toString_containsCurrency() {
        Employee e = new PermanentEmployee("name", 937);

        assertEquals("name, €\u00A09,37", e.toString());
    }
}