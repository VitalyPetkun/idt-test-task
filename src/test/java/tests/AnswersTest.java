package tests;

import io.restassured.http.ContentType;
import models.Answer;
import models.Owner;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.JsonConverter;
import utils.SmartLogger;
import utils.api.Response;
import utils.api.StackExchangeApiUtils;

public class AnswersTest extends BaseTest {

    private Response response;

    @DataProvider
    public Object[][] getParameters() {
        return new Object[][]{
                {"stackoverflow", "1", "10", "desc", "activity", "default"}
        };
    }

    @Test(dataProvider = "getParameters")
    public void getAnswers(String site, String page, String pageSize, String order, String sort, String filter) {
        SmartLogger.logStep(1, "Get request for %s owners".concat(pageSize));
        response = StackExchangeApiUtils.getAnswers(site, page, pageSize, order, sort, filter, ContentType.JSON);
        Assert.assertEquals(response.getStatus(), HttpStatus.SC_OK, "Don't correct status code");

        Answer answer = JsonConverter.getObject(response.getBody(), Answer.class);
        Assert.assertEquals(answer.getItems().size(), Integer.parseInt(pageSize), "Don't correct items size");

        answer.getItems().stream().forEach(Item -> Assert.assertTrue(Item.getOwner().getClass().equals(Owner.class),
                "Not all items of array have Owner"));

        answer.getItems().stream().forEach(Item -> Assert.assertTrue(Item.getOwner().getLink().
                contains(Item.getOwner().getUser_id()), "Not all Owners links formed with user_id"));

        answer.getItems().stream().forEach(Item -> Assert.assertTrue(Item.getOwner().getLink().
                        contains(Item.getOwner().getDisplay_name().toLowerCase().replaceAll("[ .]", "-")),
                "Not all Owners links formed with display_name"));
    }
}
