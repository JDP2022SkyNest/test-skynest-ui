package com.skynest.uitesting.pages;

import com.skynest.uitesting.models.Bucket;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BucketModal {

    private final WebDriver driver;
    private final PageActions pageActions;
    private final Bucket bucket;

    @FindBy(xpath = "//input[@id='nameInp']") private WebElement bucketModalNameField;
    @FindBy(xpath = "//input[@id='descrInp']") private WebElement bucketModalDescriptionField;
    @FindBy(xpath = "//button[contains(text(),'Create')]") private WebElement bucketModalCreateButton;

    public BucketModal(WebDriver driver, Bucket bucket) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        pageActions = new PageActions(driver);
        this.bucket = bucket;
    }

    public HomePage submitBucketRequest() {
        pageActions.clearAndType(bucketModalNameField, bucket.getName());
        pageActions.clearAndType(bucketModalDescriptionField, bucket.getDescription());
        bucketModalCreateButton.click();
        return new HomePage(driver);
    }
}
