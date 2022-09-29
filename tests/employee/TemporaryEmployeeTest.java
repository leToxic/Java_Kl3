package employee;

import employee.fixtures.LocalizedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemporaryEmployeeTest extends LocalizedTest {

    @Test
    void constructor_dailyRateNegative_exception() {
        assertThrows(IllegalArgumentException.class, () ->
                new TemporaryEmployee("name", -1));
    }

    @Test
    void constructor_dailyRate0_objectCreated() {
        assertDoesNotThrow(() ->
                new TemporaryEmployee("name", 0));
    }

    @Test
    void calculateHourlyRate_returnsRatePerHour() {
        Employee e = new TemporaryEmployee("name", 937);

        assertEquals(937, e.calculateHourlyRate());
    }

    @Test
    void toString_containsCurrency() {
        Employee e = new TemporaryEmployee("name", 937);

        assertEquals("name, €\u00A09,37", e.toString());
    }
}