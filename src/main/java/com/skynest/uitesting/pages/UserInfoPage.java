package com.skynest.uitesting.pages;


import com.skynest.uitesting.constants.PageUrlConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserInfoPage extends BasePage {

    public static WebElement editUserInfoButton(WebDriver driver) {
        WebElement button = driver.findElement(By.xpath("///button[normalize-space()='Edit']"));
        return button;
    }

    public UserInfoPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        setPagePath(PageUrlConstants.USER_PROFILE_URL);
    }

    public EditUserInfoForm fillEditedForm() {
        EditUserInfoForm fillEditedForm = new EditUserInfoForm(driver, wait);
        return fillEditedForm;
    }
}
