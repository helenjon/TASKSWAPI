package backend.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ScreenResolutions {

    MAXIMUM ("Maximum", "", ""),
    RESOLUTION1024X768 ("1024X768", "1024", "768"),
    RESOLUTION800X600 ("800X600","800", "600");

    private String resolutionName;
    private String high;
    private String wight;


    @Override
    public String toString() {
        return resolutionName;
    }

    public String toRemoveSpaceAndLowerCase() {
        return resolutionName.toLowerCase().replace(" ", "");
    }

}
