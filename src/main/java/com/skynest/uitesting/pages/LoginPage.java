package com.skynest.uitesting.pages;

import com.skynest.uitesting.constants.PageUrlConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        setPagePath(PageUrlConstants.LOGIN_URL);
    }

    public LoginForm fillLoginForm() {
        return new LoginForm(driver, wait);
    }
}
