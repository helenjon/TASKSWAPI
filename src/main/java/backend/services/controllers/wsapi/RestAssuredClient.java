package backend.services.controllers.wsapi;

import io.restassured.response.Response;

public abstract class RestAssuredClient {

    public static <R> R convertResponse(Response response, Class<R> className) {
        try {
            return response.getBody().as(className);
        } catch (Exception var3) {
            throw new ApiException(String.format("Cannot cast response to class '%s'. Response%n%s%nReason%n%s", className, response.prettyPrint(), var3.getMessage()), response);
        }
    }
}
