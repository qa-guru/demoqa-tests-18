package properties;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static java.lang.String.format;

public class SystemPropertiesTests {
    @Test
    void simplePropertyTest() {
        String browserName = System.getProperty("browser");
        System.out.println(browserName); // null
    }

    @Test
    void simpleProperty1Test() {
        System.setProperty("browser", "opera");
        String browserName = System.getProperty("browser");
        System.out.println(browserName); // opera
    }

    @Test
    void simpleProperty2Test() {
        String browserName = System.getProperty("browser", "mozilla");
        System.out.println(browserName); // mozilla
    }

    @Test
    void simpleProperty3Test() {
        System.setProperty("browser", "opera");
        String browserName = System.getProperty("browser", "mozilla");
        System.out.println(browserName); // opera
    }

    @Test
    @Tag("one_property")
    void simpleProperty4Test() {
        String browserName = System.getProperty("browser", "mozilla");
        System.out.println(browserName);

        // gradle clean one_property_test
        // mozilla

        // gradle clean one_property_test -Dbrowser=safari
        // safari

    }
    @Test
    @Tag("many_properties")
    void simpleProperty5Test() {
        String browserName = System.getProperty("browser", "mozilla");
        String browserVersion = System.getProperty("browser_version", "99.0");
        System.out.println(browserName);
        System.out.println(browserVersion);

        // gradle clean many_properties_test
        // mozilla
        // 99.0

        // gradle clean many_properties_test -Dbrowser=safari -Dbrowser_version="100.0"
        // safari
        // 100.0
    }
    @Test
    @Tag("hello")
    void simpleProperty6Test() {
        System.out.println(format("Hello, %s!", System.getProperty("user_name", "unknown student")));

        // gradle clean hello_test
        // Hello, unknown student!

        // gradle clean hello_test -Duser_name=Alex Egorov
        // ERROR Task 'Egorov' not found in root project 'demoqa-tests-18'

        // gradle clean hello_test -Duser_name="Alex Egorov"
        // gradle clean hello_test "-Duser_name=Alex Egorov"
        // Hello, Alex Egorov!
    }
}
