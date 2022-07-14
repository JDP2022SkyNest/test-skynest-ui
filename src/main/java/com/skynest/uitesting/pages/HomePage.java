package com.skynest.uitesting.pages;

import com.skynest.uitesting.config.ConfigurationManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import static org.testng.Assert.assertEquals;

public class HomePage extends LoadableComponent<HomePage> {

    private final WebDriver driver;

    public static final String URL = ConfigurationManager.getBrowserConfigInstance().baseUrl() + "/";
    private static final String USER_DROPDOWN_MENU_LOCATOR = "//div[@class = 'dropdown-menu-admin']";

    @FindBy(how = How.CLASS_NAME, using = "side-bar") private WebElement sideBar;
    @FindBy(how = How.CLASS_NAME, using = "burger") private WebElement burgerMenu;
    @FindBy(how = How.XPATH, using = "//span[contains(text(), 'Create Bucket')]") private WebElement createBucketButton;
    @FindBy(how = How.ID, using = "dropdown-menu-align-end") private WebElement userMenuDropdown;
    @FindBy(how = How.XPATH, using = USER_DROPDOWN_MENU_LOCATOR + "/a[1]") private WebElement yourProfileListItem;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public ProfilePage goToProfilePage() {
        userMenuDropdown.click();
        yourProfileListItem.click();
        return new ProfilePage(driver);
    }

    @Override
    protected void load() {
        driver.get(URL);
    }

    @Override
    protected void isLoaded() throws Error {
        assertEquals(driver.getCurrentUrl(), URL);
    }
}
