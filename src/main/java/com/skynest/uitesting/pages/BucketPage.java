package com.skynest.uitesting.pages;

import com.skynest.uitesting.config.ConfigurationManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

public class BucketPage extends LoadableComponent<BucketPage> {

    private final WebDriver driver;
    private static final String URL = ConfigurationManager.getBrowserConfigInstance().baseUrl() + "/";
    private String localPath;

    @FindBy(xpath = "//body/div[@id='root']/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/button[1]/*[1]") private WebElement fileElipsisButton;
    @FindBy(xpath = "//a[normalize-space()='Download File']") private WebElement downloadFileOption;
    @FindBy(xpath = "//span[contains(text(), 'Upload')]") private WebElement uploadToBucketButton;
    @FindBy(xpath = "//input[@id='formFile']") private WebElement modalChooseFileButton;
    @FindBy(xpath = "//button[normalize-space()='Upload']") private WebElement modalUploadButton;
    @FindAll({ @FindBy(xpath = "") }) private List<WebElement> files;

    public BucketPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void downloadFile() {
        fileElipsisButton.click();
        downloadFileOption.click();
    }

    public void uploadAFileToBucket(String name) {
        uploadToBucketButton.click();
        localPath = "C:\\" + name + ".txt";
        createFile();
        modalChooseFileButton.sendKeys(localPath);
        modalUploadButton.click();
    }

    private void createFile() {
        new File(localPath);
    }

    public boolean isFilePresent(String fileName) {
        return getExistingFileNames().contains(fileName);
    }

    private List<String> getExistingFileNames() {
        return files
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
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
