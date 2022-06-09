package com.skynest.uitesting.pages;

import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.skynest.uitesting.constants.PageUrlConstants.LOGIN_URL;

@AllArgsConstructor
public class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public void openPage() {
        driver.navigate().to(LOGIN_URL);
    }

    public void openPage(String url) {
        driver.navigate().to(url);
    }
}
