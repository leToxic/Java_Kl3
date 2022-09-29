package employee.fixtures;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.util.Locale;

public class LocalizedTest {

    private static Locale oldLocale;

    @BeforeAll
    private static void setupLocale() {
        oldLocale = Locale.getDefault();
        Locale.setDefault(new Locale("de", "AT"));
    }

    @AfterAll
    private static void restoreLocale() {
        Locale.setDefault(oldLocale);
    }
}
