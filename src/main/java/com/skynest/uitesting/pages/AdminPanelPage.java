package com.skynest.uitesting.pages;

import com.skynest.uitesting.api.User;
import com.skynest.uitesting.config.ConfigurationManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import static org.testng.Assert.assertEquals;

@Slf4j
public class AdminPanelPage extends LoadableComponent<AdminPanelPage> {

    private final WebDriver driver;
    private final PageActions pageActions;

    private static final String URL = ConfigurationManager.getBrowserConfigInstance().baseUrl() + "/admin-panel";
    private final By promoteButtonBy = By.cssSelector(".btn-secondary.button-width");
    private static final String SINGLE_SEARCH_RESULT_ACCORDION = ".shadow.accordion";
    private final By singleSearchResultAccordionBy = By.cssSelector(SINGLE_SEARCH_RESULT_ACCORDION);

    @FindBy(xpath = "//input[@type ='text']")
    private WebElement searchField;

    @FindBy(css = SINGLE_SEARCH_RESULT_ACCORDION)
    private WebElement singleSearchResultAccordion;

    public AdminPanelPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        pageActions = new PageActions(driver);
    }

    public AdminPanelPage searchAndPromoteUser(User user) {
        searchUserByEmail(user);
        singleSearchResultAccordion.click();
        WebElement promoteButton = driver.findElement(promoteButtonBy);
        pageActions.waitPersistentlyForElementToAppear(promoteButton, 2);
        pageActions.scrollElementIntoView(promoteButton);
        promoteButton.click();
        return this;
    }

    public AdminPanelPage searchUserByEmail(User user) {
        pageActions.clearAndType(searchField, user.getEmail());
        pageActions.waitForElement(driver, singleSearchResultAccordionBy, 2);
        return this;
    }

    public boolean isSuccessMessageDisplayed() {
        By alertMessageBy = By.cssSelector(".alert-success");
        return pageActions.isSuccessMessageDisplayed(alertMessageBy);
    }

    @Override
    protected void load() {
        driver.get(URL);
    }

    @Override
    protected void isLoaded() throws Error {
        assertEquals(driver.getCurrentUrl(), URL);
        pageActions.waitForElement(driver, singleSearchResultAccordionBy, 3);
    }
}
