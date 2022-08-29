package driver;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import java.time.Duration;

public class DriverWaiter extends DriverInit {

    public DriverWaiter() {
    }

    public void waitForElementPresent(By element, long... msToWait) {
        long msToWaitLoc = msToWait.length > 0 ? msToWait[0] : 20000L;
        Selenide.$(element).should(Condition.exist, Duration.ofMillis(msToWaitLoc));
    }
}
