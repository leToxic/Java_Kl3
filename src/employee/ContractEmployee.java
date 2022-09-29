package employee;

/**
 * Created: 22.09.2022 at 11:39
 *
 * @author Plasek Sebastian
 */
public class ContractEmployee extends Employee {

    private long dailyRate;

    public ContractEmployee(String name, long dailyRate) {
        super(name);
        setDailyRate(dailyRate);
    }

    public void setDailyRate(long dailyRate) {
        if (dailyRate < 0) {
            throw new IllegalArgumentException();
        }
        this.dailyRate = dailyRate;
    }

    @Override
    public double calculateHourlyRate() {
        return this.dailyRate / (double) Payable.DEFAULT_WORK_HOURS_PER_DAY;
    }

    @Override
    public String toString() {
        return this.getName() + ", " + Payable.getFormattedPayment(this.dailyRate / 100.0);
    }
}
