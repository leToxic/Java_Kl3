package employee;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmployeeTest {

    @Nested
    class Of {
        @ParameterizedTest
        @ValueSource(strings = {
                "", ",,", "P,,1", "P,name,payment", "X,name,1", "P,name,-1", "P,name,1,extra"})
        void invalidString_exception(String csv) {
            assertThrows(IllegalArgumentException.class, () -> Employee.of(csv));
        }

        @Test
        void permanentEmployee_objectCreated() {
            Employee expected = new PermanentEmployee("习近平", 123456);

            assertEquals(expected, Employee.of("P,习近平,1234.56"));
        }

        @Test
        void temporaryEmployee_objectCreated() {
            Employee expected = new TemporaryEmployee("name", 123456);

            assertEquals(expected, Employee.of("T,name,1234.56"));
        }

        @Test
        void contractEmployee_objectCreated() {
            Employee expected = new ContractEmployee("name", 123456);

            assertEquals(expected, Employee.of("C,name,1234.56"));
        }
    }

    @Nested
    static class Constructor {

        @Test
        void nameNull_exception() {
            assertThrows(IllegalArgumentException.class, () ->
                    new TestEmployee(null));
        }

        @ParameterizedTest
        @ValueSource(strings = {"", " ", "\t", "\n", "\r"})
        void nameBlank_exception(String name) {
            assertThrows(IllegalArgumentException.class, () ->
                    new TestEmployee(name));
        }

        private class TestEmployee extends Employee {

            public TestEmployee(String name) {
                super(name);
            }

            @Override
            public double calculateHourlyRate() {
                return 0;
            }
        }
    }
}