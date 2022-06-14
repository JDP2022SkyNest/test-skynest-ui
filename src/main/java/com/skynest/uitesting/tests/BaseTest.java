package com.skynest.uitesting.tests;

import com.skynest.uitesting.config.properties.PropertiesReader;
import com.skynest.uitesting.pages.LoginPage;
import com.skynest.uitesting.pages.RegistrationPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    public static final int TIMEOUT = 20;
    public WebDriver driver;
    public WebDriverWait wait;
    protected LoginPage loginPage;
    protected RegistrationPage registrationPage;

    @BeforeMethod
    public void beforeMethod() {
        PropertiesReader propertiesReader = readPropertyFile();
        switch (propertiesReader.getProperty("targetBrowser").toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case "ie":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
        initPageObjects();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    private PropertiesReader readPropertyFile(){
        try {
            PropertiesReader reader = new PropertiesReader("properties-from-pom.properties");
        return reader;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
