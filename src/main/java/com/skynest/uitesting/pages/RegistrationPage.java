package com.skynest.uitesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public RegistrationForm fillRegistrationForm() {
        RegistrationForm registrationForm = new RegistrationForm(driver, wait);
        return registrationForm;
    }
}
