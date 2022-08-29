package hooks;

import driver.DriverInit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class BaseHookWeb {

    protected WebDriver driver;

    @BeforeEach
    void driverSetup() {
        driver = DriverInit.getDriver();
    }

    @AfterEach
    void driverQuit() {
        DriverInit.close();
    }

}
