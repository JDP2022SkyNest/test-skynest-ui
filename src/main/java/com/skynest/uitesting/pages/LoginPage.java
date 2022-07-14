package com.skynest.uitesting.pages;

import com.skynest.uitesting.config.ConfigurationManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class LoginPage extends LoadableComponent<LoginPage> {

    private final WebDriver driver;

    public static final String BASE_URL = ConfigurationManager.getBrowserConfigInstance().baseUrl();
    public static final String URL = BASE_URL + "/login";

    @FindBy(how = How.ID, using = "emailInput") private WebElement emailField;
    @FindBy(how = How.ID, using = "passwordInput") private WebElement passwordField;
    @FindBy(how = How.XPATH, using = "//button[text()='Login']") private WebElement submitButton;
    @FindBy(how = How.XPATH, using = "//a[contains(@href, '/signup')]") private WebElement registerLink;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage loginAs(String email, String password) {
        setEmail(email);
        setPassword(password);
        submitButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(arg -> !driver.getCurrentUrl().equals(URL));
        return new HomePage(driver);
    }

    public RegistrationPage goToRegister() {
        registerLink.click();
        return new RegistrationPage(driver);
    }

    private void setEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    private void setPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    @Override
    protected void load() {
        driver.get(URL);
    }

    @Override
    protected void isLoaded() throws Error {
        assertTrue(driver.getCurrentUrl().contains("/login"));
        emailField.isDisplayed();
        passwordField.isDisplayed();
    }
}
