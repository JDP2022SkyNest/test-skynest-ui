package com.skynest.uitesting.pages;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

@RequiredArgsConstructor
//This adds a constructor for all fields that are either @NonNull or final.
public class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    @Setter
    private String pagePath;


    public void openPage() {
        driver.navigate().to(pagePath);
    }

//    public void openPage(String url) {
//        driver.navigate().to(url);
//    }

    public void scroll(int horizontalPixel, int verticalPixel) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(" + horizontalPixel + "," + verticalPixel + ")");
    }
}
