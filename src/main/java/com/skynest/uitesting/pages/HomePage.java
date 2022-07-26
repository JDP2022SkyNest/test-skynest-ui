package com.skynest.uitesting.pages;

import com.skynest.uitesting.config.ConfigurationManager;
import com.skynest.uitesting.models.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Slf4j
public class HomePage extends LoadableComponent<HomePage> {

    private final WebDriver driver;
    private final PageActions pageActions;

    private static final String URL = ConfigurationManager.getBrowserConfigInstance().baseUrl() + "/";
    private static final String USER_DROPDOWN_MENU_LOCATOR = "//div[@class = 'dropdown-menu-admin']";

    @FindBy(className = "side-bar") private WebElement sideBar;
    @FindBy(className = "burger") private WebElement burgerMenu;
    @FindBy(xpath = "//span[contains(text(), 'Create')]") private WebElement createBucketButton;
    @FindBy(xpath = "//button[@class='btn admin mr-2']//*[name()='svg']") private WebElement adminPanelButton;
    @FindBy(id = "dropdown-menu-align-end") private WebElement userMenuDropdown;
    @FindBy(xpath = USER_DROPDOWN_MENU_LOCATOR + "/a[1]") private WebElement yourProfileListItem;
    @FindBy(xpath = "//button[@id='react-aria346585100-87']//*[name()='svg']") private WebElement bucketElipsisButton;
    @FindBy(xpath = "//body/div[@id='root']/div[1]/div[3]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]") private WebElement bucket3;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        pageActions = new PageActions(driver);
        pageActions.waitPersistentlyForElementToAppear(createBucketButton, 5);
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

    public BucketPage goToBucket() {
        bucket3.click();
        return new BucketPage(driver);
    }

    public BucketModal openBucketCreationModal() {
        createBucketButton.click();
        return new BucketModal(driver);
    }

    public boolean isCorrectlyDisplayed() {
        boolean isSideMenuDisplayed = sideBar.isDisplayed();
        boolean isCreateBucketDisplayed = createBucketButton.isDisplayed();
        boolean isUserMenuDisplayed = userMenuDropdown.isDisplayed();
        return isSideMenuDisplayed && isCreateBucketDisplayed && isUserMenuDisplayed;
    }

    public boolean hasBucket(Bucket bucket) {
        By createdBucketNameDiv = concatenateCommonBucketBy(bucket.getName());
        By createdBucketDescriptionDiv = concatenateCommonBucketBy(bucket.getDescription());
        try {
            pageActions.waitForElement(driver, createdBucketNameDiv, 5);
            pageActions.waitForElement(driver, createdBucketDescriptionDiv, 1);
            return true;
        } catch (NoSuchElementException ex) {
            log.error("Could not find created bucket element; {}", ex.getMessage());
            return false;
        }
    }

    private By concatenateCommonBucketBy(String bucketInfoPath) {
        return By.xpath("//div[contains(text(), '" + bucketInfoPath + "')]");
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
