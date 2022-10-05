package utils.api;

import io.restassured.http.ContentType;

import static services.StackExchangeEndPoints.*;
import static services.StackExchangeParameters.*;

public class StackExchangeApiUtils {

    private StackExchangeApiUtils() {
    }

    public Response getAnswers(String site, String page, String pageSize, String order, String sort, String filter) {
        return ApiUtils.doGet(VERSION.getVersion().concat(ANSWERS.getPoint()).
                concat(SITE.getParameter(site)).concat(PAGE.getParameter(page)).
                concat(PAGE_SIZE.getParameter(pageSize)).concat(ORDER.getParameter(order)).
                concat(SORT.getParameter(sort)).concat(FILTER.getParameter(filter)), ContentType.JSON);
    }
}
