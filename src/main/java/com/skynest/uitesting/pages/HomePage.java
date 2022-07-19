package com.skynest.uitesting.pages;

import com.skynest.uitesting.config.ConfigurationManager;
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

    private static final String URL = ConfigurationManager.getBrowserConfigInstance().baseUrl() + "/";
    private static final String USER_DROPDOWN_MENU_LOCATOR = "//div[@class = 'dropdown-menu-admin']";

    @FindBy(className = "side-bar") private WebElement sideBar;
    @FindBy(className = "burger") private WebElement burgerMenu;
    @FindBy(xpath = "//span[contains(text(), 'Create Bucket')]") private WebElement createBucketButton;
    @FindBy(id = "dropdown-menu-align-end") private WebElement userMenuDropdown;
    @FindBy(xpath = USER_DROPDOWN_MENU_LOCATOR + "/a[1]") private WebElement yourProfileListItem;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageActions pageActions = new PageActions(driver);
        PageFactory.initElements(driver, this);
        pageActions.waitPersistentlyForElementToAppear(createBucketButton, 3);
    }

    public ProfilePage goToProfilePage() {
        userMenuDropdown.click();
        yourProfileListItem.click();
        return new ProfilePage(driver);
    }

    public boolean isCorrectlyDisplayed() {
        boolean isSideMenuDisplayed = sideBar.isDisplayed();
        boolean isCreateBucketDisplayed = createBucketButton.isDisplayed();
        boolean isUserMenuDisplayed = userMenuDropdown.isDisplayed();
        return isSideMenuDisplayed && isCreateBucketDisplayed && isUserMenuDisplayed;
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
