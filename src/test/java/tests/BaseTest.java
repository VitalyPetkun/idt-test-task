package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.api.StackExchangeApiUtils;

public class BaseTest {

    @Parameters({"uri"})
    @BeforeMethod
    public void setUp(@Optional("https://api.stackexchange.com") String uri) {
        StackExchangeApiUtils.setupBaseUri(uri);
    }
}
