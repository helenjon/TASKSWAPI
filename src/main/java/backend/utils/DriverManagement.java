package backend.utils;

import backend.enums.ScreenResolutions;
import org.openqa.selenium.Dimension;

import static driver.DriverInit.getDriver;

public class DriverManagement {

    public static void setTestWindowResolution(ScreenResolutions screenResolution) {
        switch (screenResolution.name()) {
            case "MAXIMUM":
                getDriver().manage().window().maximize();
                break;
            case "RESOLUTION1024X768":
                getDriver().manage().window().setSize(new Dimension(1024, 768));
                break;
            case "RESOLUTION800X600":
                getDriver().manage().window().setSize(new Dimension(800, 600));
                 break;
            default:
                throw new IllegalArgumentException(String.format("Driver '%s' is not found", screenResolution));
        }
    }
}
