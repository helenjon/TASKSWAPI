package web.pages;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;

import static driver.DriverInit.getDriver;
import static driver.DriverInit.waiter;

public class RiskWarningPage implements AbstractPage {

    private static final By RISK_WARNING_HEADER_MENU = By.className("info-header");

    @Override
    public RiskWarningPage waitForPageLoading() {
        waiter().waitForElementPresent(RISK_WARNING_HEADER_MENU);
        return new RiskWarningPage();
    }

    public String getRiskWarningTitle() {
        return Selenide.title();
    }

    public String getRiskWarningUrl() {
        return getDriver().getCurrentUrl();
    }
}
