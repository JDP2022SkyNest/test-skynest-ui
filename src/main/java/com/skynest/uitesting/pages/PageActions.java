package com.skynest.uitesting.pages;

import lombok.AllArgsConstructor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

@AllArgsConstructor
class PageActions {

    private final WebDriver driver;

    public void clearAndType(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public void waitPersistentlyForElementToAppear(WebElement element, int seconds) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofSeconds(seconds))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
