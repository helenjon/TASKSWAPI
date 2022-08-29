package web.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;
import static driver.DriverInit.getDriver;
import static driver.DriverInit.waiter;


public class HomePage implements AbstractPage {


    private static final String ACCEPT_ALL_SELECTOR = "button[class *= 'acceptDefaultCookieFirstVisit']";
    private static final By OPEN_AN_ACCOUNT =byXpath( "//div[contains(text(),'OPEN AN ACCOUNT')]//parent::a");
    private static final By RISK_DISCLOSURE_BOTTOM_MESSAGE = byXpath("//div[@id='risk-block']//a[contains(text(),'Risk Disclosure')]");
    private static final By RESEARCH_EDICATION = byText("Research & Education");
    private static final By ECONOMIC_CALENDAR = byText("Economic Calendar");

    public HomePage openHomePage() {
        open("https://www.xm.com/");
        waitForPageLoading();
        if ($(ACCEPT_ALL_SELECTOR).isDisplayed()) acceptCookies();
        return this;
    }

    public HomePage acceptCookies(){
        waiter().waitForElementPresent(By.cssSelector(ACCEPT_ALL_SELECTOR));
        $(ACCEPT_ALL_SELECTOR).click();
        return this;
    }

    public HomePage waitForPageLoading() {
        waiter().waitForElementPresent(OPEN_AN_ACCOUNT);
        return this;
    }

    public HomePage clickResearchEducation() {
        $(RESEARCH_EDICATION).click();
        return this;
    }

    public EconomicCalendarPage clickEconomicCalendar() {
        $(ECONOMIC_CALENDAR).scrollIntoView(true).click();
   //   $(ECONOMIC_CALENDAR).click();
        return new EconomicCalendarPage();
    }

    public void clickRiskDsclosureBottomLink() {
        getDriver().switchTo().parentFrame();
        $(RISK_DISCLOSURE_BOTTOM_MESSAGE).click();
        switchTo().window(1);
        waiter().waitForElementPresent(By.xpath("//title"));
    }

}
