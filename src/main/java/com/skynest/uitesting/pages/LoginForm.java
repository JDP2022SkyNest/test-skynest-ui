package com.skynest.uitesting.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginForm {

    public final By loginButtonSelector = By.xpath("//button[text()='Login']");
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By emailAddressSelector = By.id("emailInput");
    private final By passwordSelector = By.id("passwordInput");

    protected LoginForm(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private void clearAndType(By locator, String text) {
        WebElement webElement = waitForVisibilityOfElement(locator);
        webElement.clear();
        webElement.sendKeys(text);
    }

    private WebElement waitForVisibilityOfElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public LoginForm withEmail(String email) {
        clearAndType(this.emailAddressSelector, email);
        return this;
    }

    public LoginForm withPassword(String password) {
        clearAndType(this.passwordSelector, password);
        return this;
    }

    public void submitForm() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButtonSelector)).click();
    }

}
