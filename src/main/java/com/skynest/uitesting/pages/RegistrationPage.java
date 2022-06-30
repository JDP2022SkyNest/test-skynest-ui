package com.skynest.uitesting.pages;

import com.skynest.uitesting.constants.PageUrlConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        setPagePath(PageUrlConstants.REGISTER_URL);
    }

    public RegistrationForm fillRegistrationForm() {
        RegistrationForm registrationForm = new RegistrationForm(driver, wait);
        return registrationForm;
    }
}
