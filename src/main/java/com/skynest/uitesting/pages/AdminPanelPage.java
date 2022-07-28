package com.skynest.uitesting.pages;

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
    public static final String URL = ConfigurationManager.getBrowserConfigInstance().baseUrl() + "/";

    @FindBy(linkText = "Demote") private WebElement demoteButton;
    private final By promoteButtonBy = By.cssSelector(".btn-secondary.button-width");

    public AdminPanelPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        pageActions = new PageActions(driver);
        pageActions.waitForElement(driver, By.cssSelector(".shadow.accordion"), 3);
    }

    public AdminPanelPage promoteSomeUserIfAny() {
        if (areTherePromotableUsers()) {
            String collapsedAncestorRowOfFoundPromoteButtonXpath = "//button[@class = 'btn btn-secondary button-width']//ancestor::div[5]";
            WebElement foundPromoteButtonContainer = driver.findElement(By.xpath(collapsedAncestorRowOfFoundPromoteButtonXpath));
            pageActions.scrollElementIntoView(foundPromoteButtonContainer);
            foundPromoteButtonContainer.click();
            WebElement promoteButton = driver.findElement(promoteButtonBy);
            pageActions.waitPersistentlyForElementToAppear(promoteButton, 2);
            pageActions.scrollElementIntoView(promoteButton);
            promoteButton.click();
        }
        return this;
    }

    public boolean isSuccessMessageDisplayed() {
        By alertMessageBy = By.cssSelector(".alert-success");
        try {
            pageActions.waitForElement(driver, alertMessageBy, 3);
            return true;
        } catch (NoSuchElementException ex) {
            log.info("Success message was not displayed");
            return false;
        }
    }

    private boolean areTherePromotableUsers() {
        try {
            pageActions.waitForElement(driver, promoteButtonBy, 3);
            return true;
        } catch (NoSuchElementException ex) {
            log.info("There are no users that can be promoted.");
            return false;
        }
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
