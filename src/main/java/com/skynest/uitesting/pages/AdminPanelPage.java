package com.skynest.uitesting.pages;

import com.skynest.uitesting.config.ConfigurationManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import static org.testng.Assert.assertEquals;

@Slf4j
public class AdminPanelPage extends LoadableComponent<AdminPanelPage> {

    private final WebDriver driver;
    private final PageActions pageActions;
    public static final String URL = ConfigurationManager.getBrowserConfigInstance().baseUrl() + "/";

    @FindBy(xpath = "//div[@class='shadow accordion']//div[1]//h2[1]//button[1]") private WebElement firstUserDropDown;
    @FindBy(css = "div.admin-page-body div.container div.row:nth-child(5) div.col-12.col-lg-8.offset-lg-2 div.shadow.accordion div.false.accordion-item:nth-child(1) div.accordion-collapse.collapse.show div.accordion-body div.d-flex.justify-content-between.mt-3:nth-child(5) div:nth-child(1) > button.btn.btn-secondary.button-width:nth-child(1)") private WebElement promoteUserButton;
    @FindBy(linkText = "Demote") private WebElement demoteButton;
    private final By promoteButtonBy = By.cssSelector(".btn-secondary.button-width");

    public AdminPanelPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        pageActions = new PageActions(driver);
        pageActions.waitForElement(driver, By.cssSelector(".shadow.accordion"), 3);
    }

    public void promoteSomeUserIfAny() {
        Actions actions = new Actions(driver);
        if (areTherePromotableUsers()) {
            String collapsedAncestorRowOfFoundPromoteButtonXpath = "//button[@class= 'btn btn-secondary button-width']//ancestor::div[5]";
            WebElement foundPromoteButtonContainer = driver.findElement(By.xpath(collapsedAncestorRowOfFoundPromoteButtonXpath));
            actions.moveToElement(foundPromoteButtonContainer);
            actions.perform();
            foundPromoteButtonContainer.click();
            WebElement promoteButton = driver.findElement(promoteButtonBy);
            pageActions.waitPersistentlyForElementToAppear(promoteButton, 2);
            pageActions.scrollElementIntoView(promoteButton);
            promoteButton.click();
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
