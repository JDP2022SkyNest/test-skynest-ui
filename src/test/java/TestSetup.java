import com.skynest.uitesting.api.ApiClient;
import com.skynest.uitesting.api.LoginRequest;
import com.skynest.uitesting.config.ConfigurationManager;
import com.skynest.uitesting.config.CredentialsConfig;
import com.skynest.uitesting.webdriver.WebDriverFactory;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.net.URISyntaxException;

@Slf4j
public class TestSetup {
    protected WebDriver driver;
    protected static String email;
    protected static String password;
    protected ApiClient apiClient;
    private LocalStorage localStorage;

    @BeforeSuite
    public void globalSetup() throws URISyntaxException {
        apiClient = new ApiClient(ConfigurationManager.getBrowserConfigInstance().apiBaseUrl());
        CredentialsConfig credentials = ConfigurationManager.getCredentialsConfigInstance();
        email = credentials.email();
        password = credentials.password();
    }

    @BeforeMethod
    public void setupWebDriver() {
        driver = getSupportedBrowser();
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

    protected void setBrowserAuthToken() throws URISyntaxException {
        CredentialsConfig credentials = ConfigurationManager.getCredentialsConfigInstance();
        apiClient = new ApiClient(ConfigurationManager.getBrowserConfigInstance().apiBaseUrl());
        LoginRequest loginRequest = new LoginRequest(credentials.email(), credentials.password());
        apiClient.login(loginRequest);
        localStorage = ((WebStorage) driver).getLocalStorage();
        localStorage.setItem("accessToken", apiClient.getAuthToken());
    }

    @AfterMethod
    public void destroyWebDriver() {
        if (driver != null) {
            localStorage.clear();
            driver.quit();
        }
    }
}