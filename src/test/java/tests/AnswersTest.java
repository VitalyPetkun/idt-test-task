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

import java.util.Arrays;

public class AnswersTest extends BaseTest {

    @DataProvider
    public Object[][] getParameters() {
        return new Object[][]{
                {"stackoverflow", "1", "10", "desc", "activity", "default"}
        };
    }

    @Test(dataProvider = "getParameters")
    public void getAnswers(String site, String page, String pageSize, String order, String sort, String filter) {
        SmartLogger.logStep(1, String.format("Get request for %s owners", pageSize));
        Response response = StackExchangeApiUtils.getAnswers(site, page, pageSize, order, sort, filter, ContentType.JSON);
        Answer answer = JsonConverter.getObject(response.getBody(), Answer.class);
        Assert.assertEquals(response.getStatus(), HttpStatus.SC_OK, "Don't correct status code");

        SmartLogger.logInfo("Checking response for not null");
        Assert.assertNotNull(answer, "Answer is null");
        Arrays.stream(answer.getClass().getFields()).forEach(field ->
                Assert.assertNotNull(field, "Answer field is null"));
        answer.getItems().forEach(Item -> Arrays.stream(Item.getClass().getFields()).forEach(field ->
                Assert.assertNotNull(field, "Item field is null")));
        answer.getItems().forEach(Item -> Arrays.stream(Item.getOwner().getClass().getFields()).forEach(field ->
                Assert.assertNotNull(field, "Owner field is null")));

        SmartLogger.logInfo("Checking correct items size");
        Assert.assertEquals(answer.getItems().size(), Integer.parseInt(pageSize), "Don't correct items size");

        SmartLogger.logInfo("Checking all items of array for have Owner");
        answer.getItems().forEach(Item -> Assert.assertEquals(Item.getOwner().getClass(), Owner.class,
                "Not all items of array have Owner"));

        SmartLogger.logInfo("Checking all Owners links for formed with user_id");
        answer.getItems().forEach(Item -> Assert.assertTrue(Item.getOwner().getLink().
                contains(Item.getOwner().getUser_id()), "Not all Owners links formed with user_id"));

        SmartLogger.logInfo("Checking all Owners links for formed with display_name");
        answer.getItems().forEach(Item -> Assert.assertTrue(Item.getOwner().getLink().
                        contains(Item.getOwner().getDisplay_name().toLowerCase().replaceAll("[ .]", "-")),
                "Not all Owners links formed with display_name"));
    }
}
