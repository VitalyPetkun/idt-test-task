package tests;

import org.testng.annotations.BeforeMethod;
import utils.PropertiesManager;
import utils.api.StackExchangeApiUtils;

import static services.FilesPaths.*;
import static services.FilesNames.*;
import static services.TestDataVariables.*;

public class BaseTest {

    @BeforeMethod
    public void setUp() {
        StackExchangeApiUtils.setupBaseUri(PropertiesManager.
                getValue(TEST_RESOURCES_PATH.getFilesPath(), TEST_DATA.getFileName(), STACK_EXCHANGE_URI.getVariable()));
    }
}
