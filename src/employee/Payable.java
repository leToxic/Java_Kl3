package employee;

import java.text.NumberFormat;
import java.util.List;

public interface Payable {
    int DEFAULT_WORK_HOURS_PER_DAY = 8;
    int DEFAULT_WORK_DAYS_PER_YEAR = 250;

    /**
     * Formats an amount with the currency of the default Locale.
     *
     * @param payment the amount in need of a currency symbol
     * @return the String with the currency symbol pre-/appended
     */
    static String getFormattedPayment(double payment) {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        return nf.format(payment);
    }

    /**
     * Computes the average hourly rate of given payables.
     *
     * @param payables the payables
     * @return the average hourly rate of all given payables
     */
    static double calculateAverageHourlyRate(List<Payable> payables) {
        if(payables.size() == 0) {
            return 0;
        }
        double rates = 0;

        for (Payable employee : payables) {
            rates += employee.calculateHourlyRate();
        }
        return rates / (double) payables.size();
    }

    double calculateHourlyRate();
}
