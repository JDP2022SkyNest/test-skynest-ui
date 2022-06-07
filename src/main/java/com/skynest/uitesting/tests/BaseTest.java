package com.skynest.uitesting.tests;

import com.github.javafaker.Faker;
import com.skynest.uitesting.pages.LoginPage;
import com.skynest.uitesting.pages.RegisterPage;
import com.skynest.uitesting.utils.BaseHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wait;
    public String browser = "chrome";
    public static final int TIMEOUT = 20;
    public static final String BASE_URL = "http://localhost:3000/login";
    protected LoginPage loginPage;
    protected RegisterPage registerPage;

    public Faker faker = new Faker();
    public BaseHelper helper = new BaseHelper();

    public String firstName;
    public String lastName;
    public String emailAddress;
    public String phoneNumber;
    public String homeAddress;
    public String password;
    public String confirmPassword;

    @BeforeClass
    public void setUserCredentials() {
        firstName = faker.name().firstName();
        lastName = faker.name().firstName();
        emailAddress = helper.randomEmail();
        phoneNumber = faker.phoneNumber().phoneNumber();
        homeAddress = faker.address().fullAddress();
        password = "Selenium1";
        confirmPassword = password;
    }

    @BeforeMethod
    public void beforeMethod() {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("ie")) {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));

        initPageObjects();

        driver.manage().window().maximize();
        driver.navigate().to(BASE_URL);
    }

    private void initPageObjects() {
        loginPage = new LoginPage(driver, wait);
        registerPage = new RegisterPage(driver, wait);
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    public void sleep(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void scroll(int h, int v) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(" + h + "," + v + ")");
    }
}
