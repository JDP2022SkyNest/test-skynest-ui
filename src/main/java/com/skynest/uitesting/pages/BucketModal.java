package com.skynest.uitesting.pages;

import com.skynest.uitesting.models.Bucket;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BucketModal {

    private final WebDriver driver;
    private final PageActions pageActions;

    @FindBy(xpath = "//input[@id='nameInp']") private WebElement nameField;
    @FindBy(xpath = "//input[@id='descrInp']") private WebElement descriptionField;
    @FindBy(xpath = "//button[contains(text(),'Create')]") private WebElement createButton;

    public BucketModal(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        pageActions = new PageActions(driver);
    }

    public HomePage createBucket(Bucket bucket) {
        pageActions.clearAndType(nameField, bucket.getName());
        pageActions.clearAndType(descriptionField, bucket.getDescription());
        createButton.click();
        return new HomePage(driver);
    }
}
