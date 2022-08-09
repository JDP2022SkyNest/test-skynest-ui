package com.skynest.uitesting.pages;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

@AllArgsConstructor
@Slf4j
class PageActions {

    private final WebDriver driver;

    public void clearAndType(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public void waitPersistentlyForElementToAppear(WebElement element, int seconds) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElement(WebDriver driver, final By locator, int seconds) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        wait.until(driver1 -> driver1.findElement(locator));
    }

    public void scrollElementIntoView(WebElement element) {
        int forcedExecutionTime = 1000;
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop - (viewPortHeight / 2));";

        ((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, element);
        ((JavascriptExecutor) driver).executeAsyncScript("window.setTimeout(arguments[arguments.length - 1], " + forcedExecutionTime + ");");
    }

    public boolean isSuccessMessageDisplayed(By locator) {
        try {
            waitForElement(driver, locator, 3);
            return true;
        } catch (NoSuchElementException | TimeoutException ex) {
            log.info("Success message was not displayed");
            return false;
        }
    }
}
