package web.pages;

import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static driver.DriverInit.getDriver;
import static driver.DriverInit.waiter;

public class EconomicCalendarPage implements AbstractPage {

    private static final By ECONOMIC_CALENDAR = byText("Economic Calendar");
    private static final By YESTERDAY_CALENDAR = byId("timeFrame_yesterday");
    private static final By TODAY_CALENDAR = byId("timeFrame_today");
    private static final By TOMORROW_CALENDAR = byId("timeFrame_tomorrow");
    private static final By THIS_WEEK_CALENDAR = byId("timeFrame_thisWeek");
    private static final By TIME_SELECTOR_THIS_WEEK_CALENDAR = byXpath("//ul[@id='timeselector']//a[contains(text(),'This Week')]");
    private static final String TIME_SELECTOR_CALENDAR = "//ul[@id='timeselector']//a[contains(text(),'%s')]";
    private static final By TIME_SELECTOR_OPENED = byXpath("//*[@class='float_lang_base_2 dateBtns opened']");
    private static final By DATE_RANGE = byId("widgetFieldDateRange");
    private static final By CLOSE_UP_MESSAGE = byXpath("//div[@class='sticky-bar active']//i[@class='fa fa-times']");
    private static final By HERE_BOTTOM_LINK = byXpath("//a[@href='/research/risk_warning']");
    private static final By DATA_PICKER_WIDGET = byXpath("//div[@id='flatDatePickerCanvasHol']/div[@id='widget']");
    private static final By Business_NZ_PMI = byText("Business NZ PMI");
    private static final String IFRAME_ECONOMIC_CALENDAR = "//iframe[@title='economicCalendar']";

    @Override
    public EconomicCalendarPage waitForPageLoading() {
        waiter().waitForElementPresent(ECONOMIC_CALENDAR);
        return this;
    }

    public EconomicCalendarPage clickYesterdayButton() {
        driverSwitchToFrameEconomicCalendar();
        $(YESTERDAY_CALENDAR).click();
        return this;
    }

    public EconomicCalendarPage clickTimeSelectorThisWeek(){
        driverSwitchToFrameEconomicCalendar();
        $(By.xpath(String.format(TIME_SELECTOR_CALENDAR, getMenuTimeSelectorName()))).click();
        $(TIME_SELECTOR_OPENED).shouldBe(exist);
        return this;
    }

    private  String  getMenuTimeSelectorName(){
        driverSwitchToFrameEconomicCalendar();
        return $(By.xpath("//li[@class='saveSpace']//a")).getText();
    }


    public EconomicCalendarPage clickCloseUpmessage() {
        driverSwitchToFrameEconomicCalendar();
        $(CLOSE_UP_MESSAGE).click();
        return this;
    }

    public EconomicCalendarPage clickTodayButton() {
        driverSwitchToFrameEconomicCalendar();
        $(TODAY_CALENDAR).click();
        return this;
    }

    public EconomicCalendarPage clickTomorrowButton() {
        driverSwitchToFrameEconomicCalendar();
        $(TOMORROW_CALENDAR).click();
        return this;
    }

    @SneakyThrows
    public EconomicCalendarPage clickThisWeekButton() {
        driverSwitchToFrameEconomicCalendar();
        $(THIS_WEEK_CALENDAR).click();
        // probably waiting for spinner disappeared should be here, however don't have time to check, how to catch spinner event;
     //   $(Business_NZ_PMI).shouldBe(visible);
        return this;
    }

    public String getDateRangeValue() {
        driverSwitchToFrameEconomicCalendar();
        $(DATA_PICKER_WIDGET).shouldBe(visible);
        return $(DATE_RANGE).getText();
    }

    private void driverSwitchToFrameEconomicCalendar() {
        getDriver().switchTo().parentFrame();
        WebElement fr = getDriver().findElement(By.xpath(IFRAME_ECONOMIC_CALENDAR));
        getDriver().switchTo().frame(fr);
    }

    public EconomicCalendarPage clickHereLink() {
        $(HERE_BOTTOM_LINK).scrollIntoView(true);
        if ($(CLOSE_UP_MESSAGE).isDisplayed()) $(CLOSE_UP_MESSAGE).click();
        $(HERE_BOTTOM_LINK).click();
        return this;
    }
}
