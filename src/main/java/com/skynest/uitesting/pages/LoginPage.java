package com.skynest.uitesting.pages;

import com.skynest.uitesting.config.ConfigurationManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import static org.testng.Assert.assertTrue;

public class LoginPage extends LoadableComponent<LoginPage> {

    private final WebDriver driver;
    private final PageActions pageActions;

    private static final String LOGIN_PATH = "/login";
    private static final String URL = ConfigurationManager.getBrowserConfigInstance().baseUrl() + LOGIN_PATH;

    @FindBy(how = How.ID, using = "emailInput") private WebElement emailField;
    @FindBy(how = How.ID, using = "passwordInput") private WebElement passwordField;
    @FindBy(how = How.XPATH, using = "//button[text()='Login']") private WebElement submitButton;
    @FindBy(how = How.XPATH, using = "//a[contains(@href, '/signup')]") private WebElement registerLink;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        pageActions = new PageActions(driver);
    }

    public HomePage loginAs(String email, String password) {
        setEmail(email);
        setPassword(password);
        submitButton.click();
        return new HomePage(driver);
    }

    public RegistrationPage goToRegister() {
        registerLink.click();
        return new RegistrationPage(driver);
    }

    public boolean isFormDisplayed() {
        return emailField.isDisplayed() && passwordField.isDisplayed() && submitButton.isDisplayed();
    }

    private void setEmail(String email) {
        pageActions.clearAndType(emailField, email);
    }

    private void setPassword(String password) {
        pageActions.clearAndType(passwordField, password);
    }

    @Override
    protected void load() {
        driver.get(URL);
    }

    @Override
    protected void isLoaded() throws Error {
        assertTrue(driver.getCurrentUrl().contains(LOGIN_PATH));
        assertTrue(isFormDisplayed());
    }
}
