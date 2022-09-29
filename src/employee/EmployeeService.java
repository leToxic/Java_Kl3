package employee;

import employee.Employee;

import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {
    private List<Employee> employees = new ArrayList<>();

    /**
     * Reads all employees from a given csv-File. Log is written to given logfile.
     *
     * @param srcFilename the file containing the
     * @param logFilename the file the log is written to
     * @throws IOException
     */
    public EmployeeService(String srcFilename, String logFilename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(srcFilename));
             PrintWriter writer = new PrintWriter(logFilename)) {
            writer.println(String.format("%s: %s", Instant.now(), srcFilename));
            reader.readLine();  // read the header
            String line;
            while ((line = reader.readLine()) != null) {
                String[] array = line.split(",");
                Employee person = switch (array[0]) {
                    case ("C") -> new ContractEmployee(array[1], Long.parseLong(array[2]));
                    case ("T") -> new TemporaryEmployee(array[1], Long.parseLong(array[2]));
                    case ("P") -> new PermanentEmployee(array[1], Long.parseLong(array[2]));
                    default -> throw new IllegalArgumentException();
                };
                employees.add(person);
            }
        }
    }

    /**
     * Creates an EmployeeService without any employees.
     */
    public EmployeeService() {
    }

    /**
     * Writes all saved employeedata to the given file.
     *
     * @param filename the file to write
     */
    public void writeToFile(String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
            for (int i = 0; i < employees.size(); i++) {
            }
        }
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    /**
     * Adds an employee to the Service.
     *
     * @param e the employee to be added
     */
    public void addEmployee(Employee e) {
        employees.add(e);
    }

    /**
     * Counts all managed employees.
     *
     * @return the number of employees
     */
    public int countAll() {
        return employees.size();
    }

    /**
     * Counts all permanent employees.
     *
     * @return the number of permanent employees
     */
    public long countPermanentEmployees() {
        throw new UnsupportedOperationException("TODO");
    }
}
