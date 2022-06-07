package com.skynest.uitesting.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By firstName = By.id("firstNameInput");
    private By lastName = By.id("lastNameInput");
    private By emailAddress = By.id("emailInput");
    private By phoneNumber = By.id("phoneInput");
    private By homeAddress = By.id("adressInput");
    private By password = By.id("passwordInput");
    private By confirmPassword = By.id("confPasswordInput");
    private By registerButton = By.xpath("//*[@id=\"root\"]/div/div/form/div[6]/button");

    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.firstName)).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.lastName)).sendKeys(lastName);
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.emailAddress)).sendKeys(email);
    }

    public void enterPhoneNumber(String phoneNumber) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.phoneNumber)).sendKeys(phoneNumber);
    }

    public void enterHomeAddress(String homeAddress) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.homeAddress)).sendKeys(homeAddress);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.password)).sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.confirmPassword)).sendKeys(confirmPassword);
    }

    public void clickRegisterButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(registerButton)).click();
    }
}
