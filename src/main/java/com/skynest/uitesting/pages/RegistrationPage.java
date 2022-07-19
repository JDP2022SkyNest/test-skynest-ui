package com.skynest.uitesting.pages;

import com.skynest.uitesting.models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import static org.testng.Assert.assertTrue;

public class RegistrationPage extends LoadableComponent<RegistrationPage> {

    private final WebDriver driver;

    @FindBy(how = How.XPATH, using = "//form") private WebElement registrationForm;
    @FindBy(how = How.ID, using = "firstNameInput") private WebElement firstNameField;
    @FindBy(how = How.ID, using = "lastNameInput") private WebElement lastNameField;
    @FindBy(how = How.ID, using = "emailInput") private WebElement emailField;
    @FindBy(how = How.ID, using = "phoneInput") private WebElement phoneNumberField;
    @FindBy(how = How.ID, using = "addressInput") private WebElement homeAddressField;
    @FindBy(how = How.ID, using = "passwordInput") private WebElement passwordField;
    @FindBy(how = How.ID, using = "confPasswordInput") private WebElement confirmPasswordField;
    @FindBy(how = How.XPATH, using = "//button[text() = 'REGISTER']") private WebElement registerButton;
    @FindBy(how = How.XPATH, using = "//a[@href = '/login']") private WebElement loginLink;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage registerAs(User userInfo) {
        fillFormWith(userInfo);
        registerButton.click();
        return new HomePage(driver);
    }

    public LoginPage goToLogin() {
        loginLink.click();
        return new LoginPage(driver);
    }

    private void fillFormWith(User userDetails) {
        clearAndType(firstNameField, userDetails.getFirstName());
        clearAndType(lastNameField, userDetails.getLastName());
        clearAndType(emailField, userDetails.getEmailAddress());
        clearAndType(phoneNumberField, userDetails.getPhoneNumber());
        clearAndType(passwordField, userDetails.getPassword());
        clearAndType(confirmPasswordField, userDetails.getConfirmPassword());
    }

    private void clearAndType(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    @Override
    protected void load() {
        //
    }

    @Override
    protected void isLoaded() throws Error {
        assertTrue(driver.getCurrentUrl().contains("/signup"));
        assertTrue(registrationForm.isDisplayed());
    }
}
