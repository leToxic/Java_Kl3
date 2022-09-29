package employee;

/**
 * Created: 22.09.2022 at 11:39
 *
 * @author Plasek Sebastian
 */
public class PermanentEmployee extends Employee {

    private long salary;

    public PermanentEmployee(String name, long salary) {
        super(name);
        setSalary(salary);
    }

    public void setSalary(long salary) {
        if (salary < 0) {
            throw new IllegalArgumentException();
        }
        this.salary = salary;
    }

    @Override
    public double calculateHourlyRate() {
        return (this.salary / (double) Payable.DEFAULT_WORK_DAYS_PER_YEAR) / (double) Payable.DEFAULT_WORK_HOURS_PER_DAY;
    }

    @Override
    public String toString() {
        return this.getName() + ", " + Payable.getFormattedPayment(salary / 100.0);
    }
}
