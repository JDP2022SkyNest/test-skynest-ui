package com.skynest.uitesting.pages;

import com.skynest.uitesting.config.ConfigurationManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import static org.testng.Assert.assertEquals;

public class AdminPanelPage extends LoadableComponent<AdminPanelPage> {

    private final WebDriver driver;
    private final PageActions pageActions;
    public static final String URL = ConfigurationManager.getBrowserConfigInstance().baseUrl() + "/";
    @FindBy(how = How.XPATH, using = "//div[@class='shadow accordion']//div[1]//h2[1]//button[1]") private WebElement firstUserDropDown;
    @FindBy(css = "div.admin-page-body div.container div.row:nth-child(5) div.col-12.col-lg-8.offset-lg-2 div.shadow.accordion div.false.accordion-item:nth-child(1) div.accordion-collapse.collapse.show div.accordion-body div.d-flex.justify-content-between.mt-3:nth-child(5) div:nth-child(1) > button.btn.btn-secondary.button-width:nth-child(1)")
    public WebElement promoteUserButton;

    @FindBy(css = "div.admin-page-body div.container div.row:nth-child(5) div.col-12.col-lg-8.offset-lg-2 div.shadow.accordion div.false.accordion-item:nth-child(1) div.accordion-collapse.collapse.show div.accordion-body div.d-flex.justify-content-between.mt-3:nth-child(5) div:nth-child(1) > button.btn.btn-secondary.button-width:nth-child(1)") private WebElement promoteButton;

    @FindBy(linkText = "Demote") private WebElement demoteButton;

    public AdminPanelPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        pageActions = new PageActions(driver);
    }

    public void promoteFirstUser() {
        //pageActions.waitPersistentlyForElementToAppear(firstUserDropDown, 5);
        firstUserDropDown.click();
        promoteButton.click();
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
