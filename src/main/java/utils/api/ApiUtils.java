package utils.api;

import io.restassured.http.ContentType;
import utils.SmartLogger;

import static io.restassured.RestAssured.*;

public class ApiUtils {

    protected ApiUtils() {
    }

    public static void setupBaseUri(String currentBaseUri) {
        SmartLogger.logInfo("Setup baseUri ".concat(currentBaseUri));
        baseURI = currentBaseUri;
    }

    protected static Response doGet(String endPoint, ContentType contentType) {
        SmartLogger.logInfo("Get request ".concat(baseURI).concat(endPoint));
        return new Response(given().accept(contentType).when().get(endPoint).then());
    }
}
