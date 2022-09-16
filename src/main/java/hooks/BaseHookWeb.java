package hooks;

import backend.utils.Log;
import driver.DriverInit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class BaseHookWeb {

    protected WebDriver driver;

    @BeforeEach
    void driverSetup() {
        String initHeader = ("============================ START TESTING AND INIT DRIVER ============================");
        driver = DriverInit.getDriver();
        Log.info(initHeader);
    }

    @AfterEach
    void driverQuit() {
        DriverInit.close();
    }

}
