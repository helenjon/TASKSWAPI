package backend.utils.helpers;

import java.util.HashMap;
import java.util.Map;

public class RestParametersHelper {
    private final Map<String, String> mapParameters = new HashMap<>();

    public static RestParametersHelper parametersBuilder() {
        return new RestParametersHelper();
    }

    public RestParametersHelper setPersonNumber(String personNumber) {
        mapParameters.put("", personNumber);
        return this;
    }

    public Map<String, String> build() {
        return mapParameters;
    }
}
