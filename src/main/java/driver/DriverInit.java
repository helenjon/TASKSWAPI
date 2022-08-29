package driver;

import backend.utils.ReadPropertyFile;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

import static com.codeborne.selenide.Browsers.CHROME;
import static com.codeborne.selenide.Browsers.FIREFOX;

public abstract class DriverInit {
    protected static String platformName;
    private static String implicitlyWait;
    private static final ThreadLocal<WebDriver> webdriver = new ThreadLocal();
    private static final ThreadLocal<DriverWaiter> driverWaiter = new ThreadLocal();
    private static final String propertiesWebdriverFilePath = "src/test/resources/webdriver.config.properties";
    private static WebDriver driver;

    public DriverInit() {
    }

    public static boolean isSelenideDriverExist() {
        return WebDriverRunner.hasWebDriverStarted();
    }

    public static boolean isDriverExist() {
        return getDriver() != null;
    }

    public static WebDriver getDriver() {
        if (!isSelenideDriverExist()) {
            setBrowserProperties();
            init();
        }
        return WebDriverRunner.getWebDriver();
    }

    public static void setBrowserProperties() {
        platformName = ReadPropertyFile.getProperties(propertiesWebdriverFilePath).getProperty("browser.name");
        implicitlyWait = ReadPropertyFile.getProperties(propertiesWebdriverFilePath).getProperty("implicitlyWait");
    }


    private static void init() {
        initBrowser(platformName);
        getDriver().manage().deleteAllCookies();
//        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofMillis(Integer.parseInt(implicitlyWait)));
    }

    public static void close() {
        if (isSelenideDriverExist()) {
      //      Log.info("The driver is closing...");

            try {
                Selenide.clearBrowserCookies();
                Selenide.clearBrowserLocalStorage();
                WebDriverRunner.closeWebDriver();
                webdriver.remove();
     //           Log.info("The driver has been closed.");
            } catch (Exception var4) {
      //        Log.warn(var4.getMessage());
            }

        }
    }


    private static void initBrowser(String driverName) {
        if (webdriver.get() == null) {
            switch (driverName) {
                case "chrome":
                    WebDriverManager.getInstance(CHROME).setup();
                    driver = new ChromeDriver();
                    WebDriverRunner.setWebDriver(driver);
                    break;
                case "firefox":
                    WebDriverManager.getInstance(FIREFOX).setup();
                    driver = new FirefoxDriver();
                    WebDriverRunner.setWebDriver(driver);
                    break;
                default:
                    throw new IllegalArgumentException(String.format("Driver '%s' is not found", driverName));

            }
            webdriver.set(driver);
            webdriver.get();
        }
    }


    public static DriverWaiter waiter() {
        if (driverWaiter.get() == null) {
            driverWaiter.set(new DriverWaiter());
        }
        return driverWaiter.get();
    }

    public static void iFraimeSwitch() {
        driver.switchTo().frame("economicCalendar");
    }

}
