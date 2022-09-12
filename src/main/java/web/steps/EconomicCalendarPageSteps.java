package web.steps;

import io.qameta.allure.Step;
import web.pages.EconomicCalendarPage;
import web.pages.HomePage;
import web.pages.RiskWarningPage;

public class EconomicCalendarPageSteps {

    HomePage homePage = new HomePage();
    EconomicCalendarPage economicCalendarPage = new EconomicCalendarPage();
    RiskWarningPage riskWarningPage = new RiskWarningPage();

    @Step("Go to  Economic Calendar Page")
    public void goToEconomicCalendarPage() {
        homePage.openHomePage()
                .clickResearchEducation()
                .clickEconomicCalendar();
        economicCalendarPage.clickHereLink();
        riskWarningPage.waitForPageLoading();
    }
}
