package com.skynest.uitesting.pages;

import com.skynest.uitesting.models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage {

    private final WebDriver driver;

    private static final String INPUT_FORM_PREFIX = "(//input[@type= 'text'])";

    @FindBy(how = How.XPATH, using = "//button[(text() = 'Edit')]") private WebElement editButton;
    @FindBy(how = How.XPATH, using = "//button[(text() = 'Logout')]") private WebElement logoutButton;
    @FindBy(how = How.XPATH, using = "//button[contains(text(), 'back')]") private WebElement goBackButton;
    @FindBy(how = How.XPATH, using = INPUT_FORM_PREFIX + "[1]") private WebElement firstNameField;
    @FindBy(how = How.XPATH, using = INPUT_FORM_PREFIX + "[2]") private WebElement lastNameField;
    @FindBy(how = How.XPATH, using = INPUT_FORM_PREFIX + "[3]") private WebElement emailField;
    @FindBy(how = How.XPATH, using = "//input[@type= 'number'][1]") private WebElement phoneNumberField;
    @FindBy(how = How.XPATH, using = INPUT_FORM_PREFIX + "[4]") private WebElement positionField;
    @FindBy(how = How.XPATH, using = INPUT_FORM_PREFIX + "[5]") private WebElement addressField;
    @FindBy(how = How.XPATH, using = "//button[text() = 'Update']") private WebElement updateButton;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ProfilePage editInfoTo(User updatedUser) {
        editButton.click();
        clearAndType(firstNameField, updatedUser.getFirstName());
        clearAndType(lastNameField, updatedUser.getLastName());
        clearAndType(emailField, updatedUser.getEmailAddress());
        clearAndType(phoneNumberField, updatedUser.getPhoneNumber());
        clearAndType(positionField, updatedUser.getPosition());
        clearAndType(addressField, updatedUser.getHomeAddress());
        updateButton.click();
        return this;
    }

    // logout

    // go back

    private void clearAndType(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

}
