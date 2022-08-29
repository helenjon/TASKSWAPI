package web;

import backend.enums.DateRangeSelection;
import backend.enums.ScreenResolutions;
import com.codeborne.selenide.Selenide;
import hooks.BaseHookWeb;
import lombok.SneakyThrows;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import web.pages.EconomicCalendarPage;
import web.pages.HomePage;
import web.pages.RiskWarningPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static backend.enums.DateRangeSelection.THIS_WEEK;
import static backend.enums.DateRangeSelection.TODAY;
import static backend.enums.DateRangeSelection.TOMORROW;
import static backend.enums.DateRangeSelection.YESTERDAY;
import static backend.utils.DriverManagement.setTestWindowResolution;
import static driver.DriverInit.getDriver;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class XmcomTest extends BaseHookWeb {

    HomePage homePage = new HomePage();
    EconomicCalendarPage economicCalendarPage = new EconomicCalendarPage();
    RiskWarningPage riskWarningPage = new RiskWarningPage();

    private static final String RISK_DISCLOSERS_PDF_TAB_TITLE = "XM-Risk-Disclosures-for-Financial-Instruments.pdf";

    @ParameterizedTest  //,,
    @EnumSource(value = ScreenResolutions.class, names = {"MAXIMUM", "RESOLUTION1024X768", "RESOLUTION800X600"})
    void testTask1_test1(ScreenResolutions screenResolutions) {
        setTestWindowResolution(screenResolutions);
        homePage.openHomePage()
                .clickResearchEducation()
                .clickEconomicCalendar();
        economicCalendarPage.clickHereLink();
        riskWarningPage.waitForPageLoading();
//https://www.xm.com/research/risk_warning:
// - go directly - page tab title - Research
// - go via "here" link - page tab title - Economic Calendar
        String expectedTabTitle = "Economic Calendar";
        String expectedUrl = "https://www.xm.com/research/risk_warning";
        assertAll(
                () -> assertEquals(expectedTabTitle, riskWarningPage.getRiskWarningTitle(), "Incorrect number of open tabs, should be 2"),
                () -> assertEquals(expectedUrl, riskWarningPage.getRiskWarningUrl(), "Wrong url was opened")
        );
    }

    @ParameterizedTest
    @EnumSource(value = ScreenResolutions.class, names = {"RESOLUTION800X600"})
    void testTask1_test2(ScreenResolutions screenResolutions) {
        setTestWindowResolution(screenResolutions);
        homePage.openHomePage()
                .waitForPageLoading()
                .clickResearchEducation()
                .clickEconomicCalendar();
        economicCalendarPage.waitForPageLoading();
//        economicCalendarPage.clickThisWeekButton();
//       // checkDates(economicCalendarPage.getDateRangeValue(), THIS_WEEK);
//        economicCalendarPage.clickYesterdayButton();
//       // checkDates(economicCalendarPage.getDateRangeValue(), YESTERDAY);
//        economicCalendarPage.clickTodayButton();
//      //  checkDates(economicCalendarPage.getDateRangeValue(), TODAY);
//        economicCalendarPage.clickTomorrowButton();
//      //  checkDates(economicCalendarPage.getDateRangeValue(), TOMORROW);

        economicCalendarPage.clickTimeSelectorThisWeek();
        economicCalendarPage.clickThisWeekButton();
        economicCalendarPage.clickTimeSelectorThisWeek();
        economicCalendarPage.clickYesterdayButton();
        economicCalendarPage.clickTimeSelectorThisWeek();
        economicCalendarPage.clickTodayButton();
        economicCalendarPage.clickTimeSelectorThisWeek().clickTomorrowButton();

    }

    @Test
    void testTask1_test3() {
        homePage.openHomePage()
                .waitForPageLoading()
                .clickRiskDsclosureBottomLink();
        assertRiskDisclosureBottomLinkOpenedNewTab();
    }

    public void assertRiskDisclosureBottomLinkOpenedNewTab() {
        int actualNumberOfOpenTabs = new ArrayList<>(getDriver().getWindowHandles()).size();
        String actualRiskDisclosuresTabTitle = Selenide.title();
        assertAll(
                () -> assertEquals(2, actualNumberOfOpenTabs, "Incorrect number of open tabs, should be 2"),
                () -> assertEquals(RISK_DISCLOSERS_PDF_TAB_TITLE,
                        actualRiskDisclosuresTabTitle, "Incorrect number of open tabs, should be 2"));
    }

    @SneakyThrows
    private void checkDates(String actualEconomicCalendarDates, DateRangeSelection dateRangeSelection) {
        LocalDate today = new LocalDate();
        List<String> actualDates = new ArrayList<>(Arrays.asList(actualEconomicCalendarDates.replace(" - ", ",").split(",")));
        String expectedStartDate;
        String expectedEndDate = null;
        switch (dateRangeSelection) {
            case YESTERDAY:
                expectedStartDate = DateTime.now().minusDays(1).toString(("dd/MM/YYYY"));
                expectedEndDate = DateTime.now().minusDays(1).toString(("dd/MM/YYYY"));
                break;
            case TODAY:
                expectedStartDate = DateTime.now().toDateTime().toString(("dd/MM/YYYY"));
                expectedEndDate = DateTime.now().toDateTime().toString(("dd/MM/YYYY"));
                break;
            case TOMORROW:
                expectedStartDate = DateTime.now().plusDays(1).toString(("dd/MM/YYYY"));
                expectedEndDate = DateTime.now().plusDays(1).toString(("dd/MM/YYYY"));
                break;
            case THIS_WEEK:
                expectedStartDate = today.dayOfWeek().withMinimumValue().minusDays(1).toString("dd/MM/YYYY");
                expectedEndDate = today.dayOfWeek().withMaximumValue().minusDays(1).toString("dd/MM/YYYY");
                break;
            default:
                throw new Exception("Incorrect date range selected");
        }
        assertEquals(expectedStartDate, actualDates.get(0), "Wrong dates rage for Start Date of " + dateRangeSelection);
        assertEquals(expectedEndDate, actualDates.get(1), "Wrong dates rage for End Date of " + dateRangeSelection);
    }


}
