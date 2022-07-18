package com.skynest.uitesting.pages;

import com.skynest.uitesting.config.ConfigurationManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import static org.testng.Assert.assertEquals;

public class AdminPanelPage extends LoadableComponent<AdminPanelPage> {

    private final WebDriver driver;

    public static final String URL = ConfigurationManager.getBrowserConfigInstance().baseUrl() + "/";

    @FindBy(how = How.XPATH, using = "//div[@class='shadow accordion']//div[1]//h2[1]//button[1]")
    private WebElement firstUserDropDown;
    @FindBy(how = How.XPATH, using = "//body/div[@id='root']/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]")
    private WebElement promoteUserButton;

    public AdminPanelPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    protected void load() {driver.get(URL);}

    @Override
    protected void isLoaded() throws Error {
        assertEquals(driver.getCurrentUrl(), URL);
    }

    public void promoteUser() throws InterruptedException {
        firstUserDropDown.click();
        promoteUserButton.click();
    }
}
