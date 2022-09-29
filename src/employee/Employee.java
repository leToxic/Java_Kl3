package employee;

import java.util.Objects;

public abstract class Employee implements Payable {
    private String name;

    public Employee(String name) {
        if (name == null) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    /**
     * Creates an employee from a given csv-String.
     *
     * @param csv the csv Values: type, name, payment
     * @return the newly created employee
     */
    public static Employee of(String csv) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Employee employee = (Employee) o;
        return name.equals(employee.name);
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
