package employee;

/**
 * Created: 22.09.2022 at 11:39
 *
 * @author Plasek Sebastian
 */
public class TemporaryEmployee extends Employee {

    private long hourlyRate;

    public TemporaryEmployee(String name, long hourlyRate) {
        super(name);
        setHourlyRate(hourlyRate);
    }

    public void setHourlyRate(long hourlyRate) {
        if (hourlyRate < 0) {
            throw new IllegalArgumentException();
        }
        this.hourlyRate = hourlyRate;
    }

    public long getHourlyRate() {
        return hourlyRate;
    }

    @Override
    public double calculateHourlyRate() {
        return this.hourlyRate;
    }

    @Override
    public String toString() {
        return this.getName() + ", " + Payable.getFormattedPayment(this.hourlyRate / 100.0);
    }
}
