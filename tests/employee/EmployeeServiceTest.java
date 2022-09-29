package employee;

import employee.ContractEmployee;
import employee.Employee;
import employee.PermanentEmployee;
import employee.TemporaryEmployee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmployeeServiceTest {

    public static final String TEST_LOG = "testLog.txt";
    public static final String TEST_CSV = "test.csv";

    @AfterEach
    public void deleteTestFiles() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_CSV));
        Files.deleteIfExists(Paths.get(TEST_LOG));
    }

    @Test
    void constructorFiles_onlyValidRecords_objectCreated() throws IOException {
        List<Employee> employees = List.of(
                Employee.of("C,Huber Klaus,96.0"),
                Employee.of("P,Werner Eva,20000.79"),
                Employee.of("T,Schuster Hermann,11.0"),
                Employee.of("T,Winter Christine,12.57"),
                Employee.of("P,ගෝඨාභය රාජපක්ෂ,100000"),
                Employee.of("C,Huber Fritz,210.8"),
                Employee.of("T,Schmidt Elisabeth,17.8"),
                Employee.of("P,Fischer Gerhard,25000.0"),
                Employee.of("P,徳仁,100000000"),
                Employee.of("C,Eberhart Eva,312.0"),
                Employee.of("T,Müller Klaus,24.5"));
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(TEST_CSV)))) {
            pw.println("type,name,payment\n" +
                    "C,Huber Klaus,96.0\n" +
                    "P,Werner Eva,20000.79\n" +
                    "T,Schuster Hermann,11.0\n" +
                    "T,Winter Christine,12.57\n" +
                    "P,ගෝඨාභය රාජපක්ෂ,100000\n" +
                    "C,Huber Fritz,210.8\n" +
                    "T,Schmidt Elisabeth,17.8\n" +
                    "P,Fischer Gerhard,25000.0\n" +
                    "P,徳仁,100000000\n" +
                    "C,Eberhart Eva,312.0\n" +
                    "T,Müller Klaus,24.5");
        }

        EmployeeService service = new EmployeeService(TEST_CSV, TEST_LOG);

        assertEquals(employees, service.getEmployees());
    }

    @Test
    void constructorFiles_fileContainsInvalidRecords_objectCreatedFromValidLinesOnly() throws IOException {
        List<Employee> employees = List.of(
                Employee.of("C,Huber Fritz,210.80"),
                Employee.of("P,Gruber Felix,42359.99"),
                Employee.of("T,Bauer Richard Franz,19.5"),
                Employee.of("C,null null,0"));
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(TEST_CSV)))) {
            pw.println("type,name,payment\n" +
                    "C,Huber Fritz,210.80\n" +
                    "T,Schmidt Elisabeth,-17.80\n" +
                    "P,Fischer Gerhard,xx\n" +
                    "X,Eberhart Eva,312\n" +
                    "T,Klaus 24.50\n" +
                    "P,,3\n" +
                    "P,Gruber Felix,42359.99\n" +
                    "T,Bauer Richard Franz,19.5\n" +
                    "C,null null,0");
        }

        EmployeeService service = new EmployeeService(TEST_CSV, TEST_LOG);

        assertEquals(employees, service.getEmployees());
    }

    @Test
    void constructorFiles_file_timestampAndFileNameLogged() throws IOException {
        String timestamp = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
        Files.createFile(Paths.get(TEST_CSV));

        new EmployeeService(TEST_CSV, TEST_LOG);
        String logEntry = Files.readAllLines(Paths.get(TEST_LOG)).get(0);

        assertTrue(logEntry.contains(timestamp));
        assertTrue(logEntry.contains(TEST_CSV));
    }

    @Test
    void constructorFiles_file_oneLogEntryPerLine() throws IOException {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(TEST_CSV)))) {
            pw.println("type,name,payment\n" +
                    "C,Huber Fritz,210.80\n" +
                    "T,Schmidt Elisabeth,-17.80\n" +
                    "P,Fischer Gerhard,xx\n" +
                    "X,Eberhart Eva,312\n" +
                    "T,Klaus 24.50\n" +
                    "P,,3\n" +
                    "P,Gruber Felix,42359.99\n" +
                    "T,Bauer Richard Franz,19.5\n" +
                    "C,null null,0");
        }

        new EmployeeService(TEST_CSV, TEST_LOG);
        long lines = Files.lines(Paths.get(TEST_CSV)).count();

        assertEquals(10, lines);
    }

    @Test
    void countPermanentEmployees_emptyList_0() {
        EmployeeService service = new EmployeeService();

        long actual = service.countPermanentEmployees();

        assertEquals(0, actual);
    }

    @Test
    void countPermanentEmployees_notEmptyList_countOfPermanentEmployees() throws IOException {
        EmployeeService service = new EmployeeService();
        service.addEmployee(new TemporaryEmployee("A", 1));
        service.addEmployee(new PermanentEmployee("B", 1));
        service.addEmployee(new TemporaryEmployee("C", 1));
        service.addEmployee(new PermanentEmployee("D", 1));
        service.addEmployee(new PermanentEmployee("E", 1));
        service.addEmployee(new ContractEmployee("F", 1));

        long actual = service.countPermanentEmployees();

        assertEquals(3, actual);
    }
}