package employee;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static employee.Payable.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PayableTest {

    @Test
    void calculateAverageHourlyRate_emptyList_0() {
        double actual = calculateAverageHourlyRate(new ArrayList<Payable>());

        assertEquals(0, actual);
    }

    @Test
    void calculateAverageHourlyRate_nonEmptyList_averageOfHourlyRates() {
        List<Payable> payables = List.of(new PermanentEmployee("A", 1000),
                new TemporaryEmployee("B", 1000),
                new ContractEmployee("C", 1000));
        double expected = (1000.0 / DEFAULT_WORK_HOURS_PER_DAY
                + 1000.0 / DEFAULT_WORK_HOURS_PER_DAY / DEFAULT_WORK_DAYS_PER_YEAR
                + 1000) / 3;

        double actual = calculateAverageHourlyRate(payables);

        assertEquals(expected, actual);
    }
}