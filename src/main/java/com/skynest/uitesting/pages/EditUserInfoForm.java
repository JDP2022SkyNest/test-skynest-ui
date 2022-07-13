package com.skynest.uitesting.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditUserInfoForm {

    private final WebDriver driver;
    private final WebDriverWait wait;
    
    public final By editUserInfoButton = By.xpath("//button[normalize-space()='Edit']");

    public final By logoutButton = By.xpath("//button[normalize-space()='Logout']");
    public final By updateUserInfoButton = By.xpath("//button[normalize-space()='Update']");
    private final By firstNameModifier = By.cssSelector("div[class='card-body'] div:nth-child(1) div:nth-child(2) input:nth-child(1)");
    private final By lastNameModifier = By.xpath("//div[@class='col-lg-8 mb-3']//div[2]//div[2]//input[1]");
    private final By phoneNumberModifier = By.xpath("//input[@type='number']");
    private final By companyPositionModifier = By.xpath("//input[@placeholder='Please enter a value here']");
    private final By addressModifier = By.xpath("//div[@class='col-lg-8 mb-3']//div[2]//div[2]//input[1]");

    protected EditUserInfoForm(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public EditUserInfoForm withFirstName(String firstName) {
        clearAndType(this.firstNameModifier, firstName);
        return this;
    }

    public EditUserInfoForm withLastName(String lastName) {
        clearAndType(this.lastNameModifier, lastName);
        return this;
    }

    public EditUserInfoForm withPhoneNumber(String phoneNumber) {
        clearAndType(this.phoneNumberModifier, phoneNumber);
        return this;
    }

    public EditUserInfoForm withCompanyPosition(String companyPosition) {
        clearAndType(this.companyPositionModifier, companyPosition);
        return this;
    }

    public EditUserInfoForm withAddress(String address) {
        clearAndType(this.addressModifier, address);
        return this;
    }

    public void submitEditedForm() {
        wait.until(ExpectedConditions.elementToBeClickable(updateUserInfoButton)).click();
    }

    private void clearAndType(By locator, String text) {
        WebElement webElement = waitForVisibilityOfElement(locator);
        webElement.clear();
        webElement.sendKeys(text);
    }

    private WebElement waitForVisibilityOfElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

//    public void waitAndClickEditButton() {
//        wait.until(ExpectedConditions.visibilityOfElementLocated (By.xpath("//button[contains(text(),'Edit')]")));
//        WebElement m = driver.findElement(By.xpath("//button[contains(text(),'Edit')]"));
//        m.click();
//    }

//    EditedUser editedUser = EditedUser.generateModifiedData();
//    public static UserInfoPage userInfoPage;
//
//    EditUserInfoForm editUserInfoForm = userInfoPage.fillEditedForm()
//            .withFirstName(editedUser.getFirstName())
//            .withLastName(editedUser.getLastName())
//            .withPhoneNumber(editedUser.getPhoneNumber())
//            .withCompanyPosition(editedUser.getCompanyPosition())
//            .withAddress(editedUser.getAddress());

}
