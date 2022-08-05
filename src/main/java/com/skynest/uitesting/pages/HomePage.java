package com.skynest.uitesting.pages;

import com.skynest.uitesting.api.BucketResponse;
import com.skynest.uitesting.config.ConfigurationManager;
import com.skynest.uitesting.models.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Slf4j
public class HomePage extends LoadableComponent<HomePage> {

    private final WebDriver driver;
    private final PageActions pageActions;

    private static final String URL = ConfigurationManager.getBrowserConfigInstance().baseUrl() + "/";
    private static final String USER_DROPDOWN_MENU_LOCATOR = "//div[@class = 'dropdown-menu-admin']";
    private static final String BUCKET_DELETE_LINK = "a.text-danger";

    @FindBy(className = "side-bar") private WebElement sideBar;
    @FindBy(className = "burger") private WebElement burgerMenu;
    @FindBy(xpath = "//span[contains(text(), 'Create')]") private WebElement createBucketButton;
    @FindBy(xpath = "//button[@class='btn admin mr-2']//*[name()='svg']") private WebElement adminPanelButton;
    @FindBy(id = "dropdown-menu-align-end") private WebElement userMenuDropdown;
    @FindBy(xpath = USER_DROPDOWN_MENU_LOCATOR + "/a[1]") private WebElement yourProfileListItem;
    @FindBy(css = BUCKET_DELETE_LINK) private WebElement bucketDeleteLink;
    @FindBy(xpath = "//p[contains(text(), 'Bucket Successfully Deleted')]") private WebElement bucketDeletedSuccessAlert;

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

    public BucketPage openBucket(BucketResponse bucket) {
        return new BucketPage(driver, bucket.getBucketId());
    }

    public BucketModal openBucketCreationModal() {
        createBucketButton.click();
        return new BucketModal(driver);
    }

    public void sendEmailToInviteUser() {
        userMenuDropdown.click();
//        inviteUserDropdownOption.click();
//        emailForInvitingUser.sendKeys(usersEmail);
//        inviteButton.click();
    }

    public void createTag() {
        //pageActions.waitPersistentlyForElementToAppear(createTagButton, 5);
//        createTagButton.click();
//        tagNameInputField.sendKeys(tagName);
//        createTagModalButton.click();
    }

    public void deleteBucket(Bucket bucket) {
        final String specificBucketContainer = "//div[contains(text(), '" + bucket.getName() + "')]";
        final String relatedBucketDropdownButton = "//ancestor::div[2]//button";
        By createdBucketDeleteLinkBy = By.cssSelector(BUCKET_DELETE_LINK);
        pageActions.waitForElement(driver, By.xpath(specificBucketContainer), 8);
        WebElement createdBucketDropdownMenu = driver.findElement(By.xpath(specificBucketContainer + relatedBucketDropdownButton));
        createdBucketDropdownMenu.click();
        pageActions.waitForElement(driver, createdBucketDeleteLinkBy, 1);
        bucketDeleteLink.click();
    }

    public boolean isCorrectlyDisplayed() {
        boolean isCreateBucketDisplayed = createBucketButton.isDisplayed();
        boolean isUserMenuDisplayed = userMenuDropdown.isDisplayed();
        return isCreateBucketDisplayed && isUserMenuDisplayed;
    }

    public boolean hasBucket(Bucket bucket) {
        By createdBucketNameDivBy = getDivContainingBucketInfoBy(bucket.getName());
        By createdBucketDescriptionDivBy = getDivContainingBucketInfoBy(bucket.getDescription());
        pageActions.waitForElement(driver, createdBucketNameDivBy, 10);
        List<WebElement> createdBucketName = driver.findElements(createdBucketNameDivBy);
        List<WebElement> createdBucketDescription = driver.findElements(createdBucketDescriptionDivBy);
        return !createdBucketName.isEmpty() && !createdBucketDescription.isEmpty();
    }

    public boolean hasDisplayedSuccessMessage() {
        By alertMessageBy = By.cssSelector(BUCKET_DELETE_LINK);
        pageActions.waitForElement(driver, alertMessageBy, 5);
        List<WebElement> alertMessage = driver.findElements(alertMessageBy);
        return !alertMessage.isEmpty();
    }

//    public boolean hasDisplayedMessageTest() {
//        Wait<WebDriver> wait = new WebDriverWait<>();
//        driver.findElement(By.linkText("See an example alert")).click();
//        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
//        alert.dismiss();
//    }

    public void displayMessage() {
        driver.switchTo().alert().getText();
    }

    private By getDivContainingBucketInfoBy(String bucketInfoPath) {
        return By.xpath("//div[contains(text(), '" + bucketInfoPath + "')]");
    }

    @Override
    protected void load() {
        driver.get(URL);
    }

    @Override
    protected void isLoaded() throws Error {
        assertEquals(driver.getCurrentUrl(), URL);
        pageActions.waitPersistentlyForElementToAppear(createBucketButton, 5);
        assertTrue(isCorrectlyDisplayed());
    }
}
