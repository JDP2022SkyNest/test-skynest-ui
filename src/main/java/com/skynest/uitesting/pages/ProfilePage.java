package com.skynest.uitesting.pages;

import com.skynest.uitesting.config.ConfigurationManager;
import com.skynest.uitesting.models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import static org.testng.Assert.assertTrue;

public class ProfilePage extends LoadableComponent<ProfilePage> {

    private final WebDriver driver;
    private final PageActions pageActions;

    private static final String URL = ConfigurationManager.getBrowserConfigInstance().baseUrl() + "/user-info";
    private static final String INPUT_FORM_PREFIX = "(//input[@type= 'text'])";

    @FindBy(xpath = "//button[(text() = 'Edit')]") private WebElement editButton;
    @FindBy(xpath = "//button[(text() = 'Logout')]") private WebElement logoutButton;
    @FindBy(xpath = "//button[contains(text(), 'back')]") private WebElement goBackButton;
    @FindBy(xpath = INPUT_FORM_PREFIX + "[1]") private WebElement firstNameField;
    @FindBy(xpath = INPUT_FORM_PREFIX + "[2]") private WebElement lastNameField;
    @FindBy(xpath = INPUT_FORM_PREFIX + "[3]") private WebElement emailField;
    @FindBy(xpath = "//input[@type= 'number'][1]") private WebElement phoneNumberField;
    @FindBy(xpath = INPUT_FORM_PREFIX + "[4]") private WebElement positionField;
    @FindBy(xpath = INPUT_FORM_PREFIX + "[5]") private WebElement addressField;
    @FindBy(xpath = "//button[text() = 'Update']") private WebElement updateButton;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        pageActions = new PageActions(driver);
    }

    public ProfilePage editInfoTo(User updatedUser) {
        pageActions.waitPersistentlyForElementToAppear(editButton, 3);
        editButton.click();
        pageActions.clearAndType(firstNameField, updatedUser.getFirstName());
        pageActions.clearAndType(lastNameField, updatedUser.getLastName());
        pageActions.clearAndType(emailField, updatedUser.getEmailAddress());
        pageActions.clearAndType(phoneNumberField, updatedUser.getPhoneNumber());
        pageActions.clearAndType(positionField, updatedUser.getPosition());
        pageActions.clearAndType(addressField, updatedUser.getHomeAddress());
        updateButton.click();
        return this;
    }

    // logout

    // go back

    public boolean isDisplayedCorrectly() {
        return editButton.isDisplayed() && logoutButton.isDisplayed() && goBackButton.isDisplayed();
    }

    @Override
    protected void load() {
        driver.get(URL);
    }

    @Override
    protected void isLoaded() throws Error {
        assertTrue(driver.getCurrentUrl().equalsIgnoreCase(URL));
        assertTrue(isDisplayedCorrectly());
    }
}
