package com.skynest.uitesting.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationForm {
    public final By registerButtonSelector = By.xpath("//button[text()='REGISTER']");
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By firstNameSelector = By.id("firstNameInput");
    private final By lastNameSelector = By.id("lastNameInput");
    private final By emailAddressSelector = By.id("emailInput");
    private final By phoneNumberSelector = By.id("phoneInput");
    private final By homeAddressSelector = By.id("adressInput");
    private final By passwordSelector = By.id("passwordInput");
    private final By confirmPasswordSelector = By.id("confPasswordInput");

    protected RegistrationForm(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public RegistrationForm withFirstName(String firstName) {
        clearAndType(this.firstNameSelector, firstName);
        return this;
    }

    public RegistrationForm withLastName(String lastName) {
        clearAndType(this.lastNameSelector, lastName);
        return this;
    }

    public RegistrationForm withEmail(String email) {
        clearAndType(this.emailAddressSelector, email);
        return this;
    }

    public RegistrationForm withPhoneNumber(String phoneNumber) {
        clearAndType(this.phoneNumberSelector, phoneNumber);
        return this;
    }

    public RegistrationForm withHomeAddress(String homeAddress) {
        clearAndType(this.homeAddressSelector, homeAddress);
        return this;
    }

    public RegistrationForm withPassword(String password) {
        clearAndType(this.passwordSelector, password);
        return this;
    }

    public RegistrationForm withConfirmPassword(String confirmPassword) {
        clearAndType(this.confirmPasswordSelector, confirmPassword);
        return this;
    }

    public void submitForm() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButtonSelector)).click();
    }

    private void clearAndType(By locator, String text) {
        WebElement webElement = waitForVisibilityOfElement(locator);
        webElement.clear();
        webElement.sendKeys(text);
    }

    private WebElement waitForVisibilityOfElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}