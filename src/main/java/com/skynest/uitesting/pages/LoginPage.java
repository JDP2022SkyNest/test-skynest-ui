package com.skynest.uitesting.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By registerLink = By.xpath("//*[@id=\"root\"]/div/div/form/div[5]/a");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void clickRegisterLink() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(registerLink)).click();
    }
}
