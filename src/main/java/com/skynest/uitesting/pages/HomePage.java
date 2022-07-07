package com.skynest.uitesting.pages;

import com.skynest.uitesting.constants.PageUrlConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    private static WebElement element = null;

    public HomePage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        setPagePath(PageUrlConstants.DASHBOARD_URL);
    }

//    public final By adminPanelDropdown = By.xpath("//button[@class='btn admin mr-2']//*[name()='svg']");
//    public final By userProfileDropdown = By.id("dropdown-menu-align-end");
//    public final By burgerSideBar = By.xpath("//div[@class='burger']//*[name()='svg']");

    public static WebElement adminPanelDropdown(WebDriver driver) {
        element = driver.findElement(By.xpath("//button[@class='btn admin mr-2']//*[name()='svg']"));
        return element;
    }

    public static WebElement userProfileDropdown(WebDriver driver) {
        element = driver.findElement(By.id("dropdown-menu-align-end"));
        return element;
    }

    public static WebElement burgerSideBar(WebDriver driver) {
        element = driver.findElement(By.xpath("//div[@class='burger']//*[name()='svg']"));
        return element;
    }

}
