package com.skynest.uitesting.pages;


import com.skynest.uitesting.constants.PageUrlConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

public class UserInfoPage extends BasePage {

    private static final String USER_DROPDOWN_LOCATOR = "//button[@id='dropdown-menu-align-end']";
    private static final String YOUR_PROFILE_LOCATOR = "//a[normalize-space()='Your Profile']";
    private static final String COMPANY_INFO_LOCATOR = "//a[normalize-space()='Company Info']";
    private static final String CHANGE_PASSWORD_LOCATOR = "//div[normalize-space()='Change Password']";
    private static final String FULLSCREEN_LOCATOR = "//a[normalize-space()='Fullscreen']";
    private static final String LOGOUT_LOCATOR = "//div[normalize-space()='Logout']";

     private WebElement userDropDown;
     private WebElement yourProfile;
     private WebElement companyInfo;
     private WebElement changePassword;
     private WebElement fullScreen;
     private WebElement logOut;

    public static WebElement editUserInfoDropdownButton(WebDriver driver) {
        //WebElement button = driver.findElement(By.xpath("///button[normalize-space()='Edit']"));
        WebElement button = driver.findElement(By.id("dropdown-menu-align-end"));
        return button;
    }

    public static WebElement editUserInfoButton(WebDriver driver) {
        WebElement editButton = driver.findElement(By.xpath("//button[normalize-space()='Edit']"));
        return editButton;
    }

    public UserInfoPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        setPagePath(PageUrlConstants.USER_PROFILE_URL);
    }

    public EditUserInfoForm fillEditedForm() {
        EditUserInfoForm fillEditedForm = new EditUserInfoForm(driver, wait);
        return fillEditedForm;
    }


    public void  selectUserOptions() {
        userDropDown = driver.findElement(By.xpath(USER_DROPDOWN_LOCATOR));
        userDropDown.click();
        yourProfile = driver.findElement(By.xpath(YOUR_PROFILE_LOCATOR));
        yourProfile.click();
    }
}
