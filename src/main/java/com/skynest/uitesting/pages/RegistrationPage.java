package com.skynest.uitesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public RegistrationPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public RegistrationForm fillRegistrationForm() {
        RegistrationForm registrationForm = new RegistrationForm(driver, wait);
        return registrationForm;
    }
}
