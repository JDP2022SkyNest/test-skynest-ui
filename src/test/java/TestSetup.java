import com.skynest.uitesting.config.ConfigurationManager;
import com.skynest.uitesting.config.CredentialsConfig;
import com.skynest.uitesting.webdriver.WebDriverFactory;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

@Slf4j
public class TestSetup {
    protected WebDriver driver;
    protected String email;
    protected String password;

    @BeforeSuite
    public void globalSetup() {
        CredentialsConfig credentials = ConfigurationManager.getCredentialsConfigInstance();
        email = credentials.email();
        password = credentials.password();
    }

    @BeforeMethod
    public void setupWebDriver() {
        driver = getSupportedBrowser();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    private static WebDriver getSupportedBrowser() {
        String propertyDefinedDriver = ConfigurationManager.getBrowserConfigInstance().defaultBrowser();
        try {
            return WebDriverFactory.valueOf(propertyDefinedDriver.toUpperCase()).createDriver();
        } catch (IllegalArgumentException ex) {
            log.error("Non-supported browser defined in browser properties file: \"{}\", defaulting to Chrome", propertyDefinedDriver);
        }
        return WebDriverFactory.CHROME.createDriver();
    }

    @AfterMethod
    public void destroyWebDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}