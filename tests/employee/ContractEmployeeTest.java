package employee;

import employee.fixtures.LocalizedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContractEmployeeTest extends LocalizedTest {

    @Test
    void constructor_dailyRateNegative_exception() {
        assertThrows(IllegalArgumentException.class, () ->
                new ContractEmployee("name", -1));
    }

    @Test
    void constructor_dailyRate0_objectCreated() {
        assertDoesNotThrow(() ->
                new ContractEmployee("name", 0));
    }

    @Test
    void calculateHourlyRate_returnsRatePerHour() {
        Employee e = new ContractEmployee("name", 937);

        assertEquals(117.125, e.calculateHourlyRate());
    }

    @Test
    void toString_containsCurrency() {
        Employee e = new ContractEmployee("name", 937);

        assertEquals("name, €\u00A09,37", e.toString());
    }
}