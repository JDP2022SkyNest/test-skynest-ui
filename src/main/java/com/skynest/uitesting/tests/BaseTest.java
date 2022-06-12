package com.skynest.uitesting.tests;

import com.skynest.uitesting.pages.LoginPage;
import com.skynest.uitesting.pages.RegistrationPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    public static final int TIMEOUT = 20;
    public WebDriver driver;
    public WebDriverWait wait;
    public String browser = "chrome";
    protected LoginPage loginPage;
    protected RegistrationPage registrationPage;

    @BeforeMethod
    public void beforeMethod() {
        switch (browser.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/test/driver/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "ie":
                driver = new InternetExplorerDriver();
                break;
            default:
                driver = new ChromeDriver();
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        initPageObjects();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    private void initPageObjects() {
        loginPage = new LoginPage(driver, wait);
        registrationPage = new RegistrationPage(driver, wait);
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }


    public void waitForUrl(String url) {
        wait.until(arg -> getCurrentUrl().equals(url));
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
