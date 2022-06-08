package com.skynest.uitesting.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationForm {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By firstNameSelector = By.id("firstNameInput");
    private final By lastNameSelector = By.id("lastNameInput");
    private final By emailAddressSelector = By.id("emailInput");
    private final By phoneNumberSelector = By.id("phoneInput");
    private final By homeAddressSelector = By.id("adressInput");
    private final By passwordSelector = By.id("passwordInput");
    private final By confirmPasswordSelector = By.id("confPasswordInput");
    private final By registerButtonSelector = By.xpath("//button[text()='REGISTER']");

    protected RegistrationForm(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public RegistrationForm withFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.firstNameSelector)).sendKeys(firstName);
        return this;
    }

    public RegistrationForm withLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.lastNameSelector)).sendKeys(lastName);
        return this;
    }

    public RegistrationForm withEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.emailAddressSelector)).sendKeys(email);
        return this;
    }

    public RegistrationForm withPhoneNumber(String phoneNumber) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.phoneNumberSelector)).sendKeys(phoneNumber);
        return this;
    }

    public RegistrationForm withHomeAddress(String homeAddress) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.homeAddressSelector)).sendKeys(homeAddress);
        return this;
    }

    public RegistrationForm withPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.passwordSelector)).sendKeys(password);
        return this;
    }

    public RegistrationForm withConfirmPassword(String confirmPassword) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.confirmPasswordSelector)).sendKeys(confirmPassword);
        return this;
    }

    public void submitForm() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButtonSelector)).click();
    }
}
