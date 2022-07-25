package com.skynest.uitesting.pages;

import com.skynest.uitesting.config.ConfigurationManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomePage extends LoadableComponent<HomePage> {

    private final WebDriver driver;
    private final PageActions pageActions;
    String usersEmail = "nemanja.mihajlovic@htecgroup.com";
    String tagName = "MYTAG";
    private static final String URL = ConfigurationManager.getBrowserConfigInstance().baseUrl() + "/";
    private static final String USER_DROPDOWN_MENU_LOCATOR = "//div[@class = 'dropdown-menu-admin']";

    @FindBy(css = ".ml-1.mr-2.mr-sm-0.latte-background.custom-rounded.shadow") private WebElement createBucketButton;
    @FindBy(xpath = "//button[@class='btn admin mr-2']//*[name()='svg']") private WebElement adminPanelButton;
    @FindBy(how = How.ID, using = "dropdown-menu-align-end") private WebElement userMenuDropdown;
    @FindBy(how = How.XPATH, using = USER_DROPDOWN_MENU_LOCATOR + "/a[1]") private WebElement yourProfileListItem;
    @FindBy(how = How.XPATH, using = "//input[@id='nameInp']") private WebElement inputNameCreateBucket;
    @FindBy(how = How.XPATH, using = "//input[@id='descrInp']") private WebElement inputDescriptionCreateBucket;
    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Create')]") public WebElement createBucketModalButton;
    @FindBy(xpath = "//button[@id='react-aria346585100-87']//*[name()='svg']") private WebElement bucketElipsisButton;
    @FindBy(xpath = "//body/div[@id='root']/div[1]/div[1]/div[1]/div[2]/div[1]/div[24]/div[1]/div[1]") private WebElement bucketForDownload;
    @FindBy(xpath = "//span[contains(text(),'Invite User')]") private WebElement inviteUserDropdownOption;
    @FindBy(xpath = "//input[@id='emailInp']") private WebElement emailForInvitingUser;
    @FindBy(xpath = "//button[contains(text(),'Invite')]") private WebElement inviteButton;
    @FindBy(xpath = "//body/div[@id='root']/div[1]/div[1]/div[1]/div[1]/span[2]") private WebElement createTagButton;
    @FindBy(xpath = "//input[@id='nameInp']") private WebElement tagNameInputField;
    @FindBy(xpath = "//button[contains(text(),'Create')]") private WebElement createTagModalButton;
    @FindBy(xpath = "//body/div[@id='root']/div[1]/div[1]/div[1]/div[2]/div[1]/div[5]/div[1]/div[2]/div[1]/button[1]/*[1]") private WebElement bucketOptions;
    @FindBy(xpath = "//a[contains(text(),'Delete bucket')]") private WebElement deleteBucket;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        pageActions = new PageActions(driver);
    }

    public ProfilePage goToProfilePage() {
        userMenuDropdown.click();
        yourProfileListItem.click();
        return new ProfilePage(driver);
    }

    public AdminPanelPage goToAdminPanelPage() {
        adminPanelButton.click();
        return new AdminPanelPage(driver);
    }

    public BucketPage goToBucket() throws InterruptedException {
        pageActions.waitPersistentlyForElementToAppear(bucketForDownload, 5);
        bucketForDownload.click();
        return new BucketPage(driver);
    }

    public void sendEmailToInviteUser() {
        userMenuDropdown.click();
        inviteUserDropdownOption.click();
        emailForInvitingUser.sendKeys(usersEmail);
        inviteButton.click();
    }

    public void createTag() {
        //pageActions.waitPersistentlyForElementToAppear(createTagButton, 5);
        createTagButton.click();
        tagNameInputField.sendKeys(tagName);
        createTagModalButton.click();
    }

    public boolean isAlertPresent() {
        boolean presentFlag = true;

        try {
            Alert alert = driver.switchTo().alert();
            presentFlag = true;
            alert.accept();
        } catch (NoAlertPresentException ex) {
            ex.printStackTrace();
        }

        return presentFlag;
    }

    public void deleteBucket() {
        bucketOptions.click();
        deleteBucket.click();
    }

    public boolean isCorrectlyDisplayed() {
        boolean isCreateBucketDisplayed = createBucketButton.isDisplayed();
        boolean isUserMenuDisplayed = userMenuDropdown.isDisplayed();
        boolean isCreateTagButtonDisplayed = createTagButton.isDisplayed();
        return isCreateBucketDisplayed && isUserMenuDisplayed && isCreateTagButtonDisplayed;
    }

    public void createBucket() {
        //pageActions.waitPersistentlyForElementToAppear(createBucketButton, 5);
        createBucketButton.click();
        clearAndType(inputNameCreateBucket, "New Buccccket");
        clearAndType(inputDescriptionCreateBucket, "Description");
        createBucketModalButton.click();
    }

    public void displayMessage() {
        driver.switchTo().alert().getText();
    }

    private void clearAndType(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    @Override
    protected void load() {
        driver.get(URL);
    }

    @Override
    protected void isLoaded() throws Error {
        assertEquals(driver.getCurrentUrl(), URL);
        assertTrue(isCorrectlyDisplayed());
    }
}
