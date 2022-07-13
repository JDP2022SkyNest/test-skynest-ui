package com.skynest.uitesting.pages;


import com.skynest.uitesting.constants.PageUrlConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class UserInfoPage extends BasePage {

    private static final String USER_DROPDOWN_LOCATOR = "//button[@id='dropdown-menu-align-end']";
    private static final String YOUR_PROFILE_LOCATOR = "//a[normalize-space()='Your Profile']";
    private static final String COMPANY_INFO_LOCATOR = "//a[normalize-space()='Company Info']";
    private static final String CHANGE_PASSWORD_LOCATOR = "//div[normalize-space()='Change Password']";
    private static final String FULLSCREEN_LOCATOR = "//a[normalize-space()='Fullscreen']";
    private static final String LOGOUT_LOCATOR = "//div[normalize-space()='Logout']";
    private static final String EDIT_USER_LOCATOR = "//*[@id=\"root\"]/section/div/div/div[1]/div[1]/div/div[2]/button[1]";
    private static final String UPDATE_USER_LOCATOR = "//button[contains(text(),'Update')]";
    private static final String EDIT_USER_FIRST_NAME_LOCATOR = "//body/div[@id='root']/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/input[1]";
    private static final String EDIT_USER_LAST_NAME_LOCATOR = "//body/div[@id='root']/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/input[1]";
    private static final String EDIT_PHONE_NUMBER_LOCATOR = "//body/div[@id='root']/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[4]/div[2]/input[1]";
    private static final String EDIT_COMPANY_POSITION_LOCATOR = "//body/div[@id='root']/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[5]/div[2]/input[1]";
    private static final String EDIT_ADDRESS_LOCATOR = "//body/div[@id='root']/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[6]/div[2]/input[1]";

     private WebElement userDropDown;
     private WebElement yourProfile;
     private WebElement companyInfo;
     private WebElement changePassword;
     private WebElement fullScreen;
     private WebElement logOut;
     private WebElement editButton;
     private WebElement updateButton;

     private WebElement editFirstNameInputField;
     private WebElement editLastNameInputField;
     private WebElement editPhoneNumberInputField;
     private WebElement editCompanyPositionInputField;
     private WebElement editAddressInputField;

    public static WebElement editUserInfoDropdownButton(WebDriver driver) {
        //WebElement button = driver.findElement(By.xpath("///button[normalize-space()='Edit']"));
        WebElement button = driver.findElement(By.id("dropdown-menu-align-end"));
        return button;
    }

    public static WebElement editUserInfoButton(WebDriver driver) {
        //WebElement editButton = driver.findElement(By.xpath("//button[normalize-space()='Edit']"));
        return driver.findElement(By.linkText("Edit"));
    }

    public UserInfoPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        setPagePath(PageUrlConstants.USER_PROFILE_URL);
    }

    public EditUserInfoForm fillEditedForm() {
        EditUserInfoForm fillEditedForm = new EditUserInfoForm(driver, wait);
        return fillEditedForm;
    }

    public void waitForElement(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void  selectUserOptions() {
        //waitForElement(By.xpath(USER_DROPDOWN_LOCATOR));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(USER_DROPDOWN_LOCATOR)));
//        userDropDown = driver.findElement(By.id(USER_DROPDOWN_LOCATOR));
//        userDropDown.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dropdown-menu-align-end")));
        WebElement e = driver.findElement(By.id("dropdown-menu-align-end"));
        e.click();
        yourProfile = driver.findElement(By.xpath(YOUR_PROFILE_LOCATOR));
        yourProfile.click();
    }

    public void clickEditButton() {
        editButton = driver.findElement(By.xpath(EDIT_USER_LOCATOR));
        editButton.click();
    }

    public void waitAndClickEditButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated (By.xpath("//button[contains(text(),'Edit')]")));
        WebElement m = driver.findElement(By.xpath("//button[contains(text(),'Edit')]"));
        m.click();
    }

    public void clickClearAndFillFirstName() {
        editFirstNameInputField = driver.findElement(By.xpath(EDIT_USER_FIRST_NAME_LOCATOR));
        editFirstNameInputField.click();
        editFirstNameInputField.sendKeys("John");
    }

    public void clickClearAndFillLastName() {
        editLastNameInputField = driver.findElement(By.xpath(EDIT_USER_LAST_NAME_LOCATOR));
        editLastNameInputField.click();
        editLastNameInputField.sendKeys("Wick");
    }

    public void clickClearAndFillPhoneNumber() {
        editPhoneNumberInputField = driver.findElement(By.xpath(EDIT_PHONE_NUMBER_LOCATOR));
        editPhoneNumberInputField.click();
        editPhoneNumberInputField.sendKeys("06600118852");
    }

    public void clickClearAndFillCompanyPosition() {
//        editCompanyPositionInputField = driver.findElement(By.xpath(EDIT_COMPANY_POSITION_LOCATOR));
//        editCompanyPositionInputField.click();
//        editCompanyPositionInputField.sendKeys("Quality Assurance");
        wait.until(ExpectedConditions.visibilityOfElementLocated (By.xpath(EDIT_COMPANY_POSITION_LOCATOR)));
        WebElement m = driver.findElement(By.xpath(EDIT_COMPANY_POSITION_LOCATOR));
        m.click();
        m.sendKeys("Quality Assurance");
    }

    public void clickClearAndFillAddressField() {
//        editAddressInputField = driver.findElement(By.xpath(EDIT_ADDRESS_LOCATOR));
//        editAddressInputField.click();
//        editAddressInputField.sendKeys("Street 12");
        wait.until(ExpectedConditions.visibilityOfElementLocated (By.xpath(EDIT_ADDRESS_LOCATOR)));
        WebElement m = driver.findElement(By.xpath(EDIT_ADDRESS_LOCATOR));
        m.click();
        m.sendKeys("Street Address 12");
    }

    public void clickUpdateAfterEditing() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(UPDATE_USER_LOCATOR)));
        updateButton = driver.findElement(By.xpath(UPDATE_USER_LOCATOR));
        updateButton.click();
    }


//    private By newEditButton = By.xpath("//button[contains(text(),'Edit')]");
//    public void waitForElement(By element) {
//        boolean loop = true;
//        while (loop) {
//            if(driver.findElement(newEditButton).isDisplayed() > 0) {
//                loop = false;
//            } else {
//                try {
//                    TimeUnit.SECONDS.sleep(2);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
