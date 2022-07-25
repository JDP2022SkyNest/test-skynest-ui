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
    @FindBy(how = How.XPATH, using = "//button[(text() = 'Logout')]") private WebElement logoutButton;
    @FindBy(how = How.XPATH, using = "//button[contains(text(), 'back')]") private WebElement goBackButton;
    @FindBy(how = How.XPATH, using = INPUT_FORM_PREFIX + "[1]") private WebElement firstNameField;
    @FindBy(how = How.XPATH, using = INPUT_FORM_PREFIX + "[2]") private WebElement lastNameField;
    @FindBy(how = How.XPATH, using = INPUT_FORM_PREFIX + "[3]") private WebElement emailField;
    //@FindBy(how = How.XPATH, using = "//input[@type= 'number'][1]") private WebElement phoneNumberField;
    @FindBy(xpath = "//body/div[@id='root']/section[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[4]/div[2]/input[1]") private WebElement phoneNumberField;
    @FindBy(how = How.XPATH, using = INPUT_FORM_PREFIX + "[4]") private WebElement positionField;
    @FindBy(how = How.XPATH, using = INPUT_FORM_PREFIX + "[5]") private WebElement addressField;
    @FindBy(how = How.XPATH, using = "//button[text() = 'Update']") private WebElement updateButton;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        pageActions = new PageActions(driver);
    }

    public ProfilePage editInfoTo(User updatedUser) {
        //pageActions.waitPersistentlyForElementToAppear(editButton, 3);
        editButton.click();
        pageActions.clearAndType(firstNameField, updatedUser.getFirstName());
        pageActions.clearAndType(lastNameField, updatedUser.getLastName());
        //pageActions.clearAndType(emailField, updatedUser.getEmailAddress());
        pageActions.clearAndType(phoneNumberField, updatedUser.getPhoneNumber());
        pageActions.clearAndType(positionField, updatedUser.getPosition());
        pageActions.clearAndType(addressField, updatedUser.getHomeAddress());
        updateButton.click();
        return this;
    }

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

    // logout

    // go back

}
