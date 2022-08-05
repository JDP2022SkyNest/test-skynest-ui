package com.skynest.uitesting.pages;

import com.skynest.uitesting.config.ConfigurationManager;
import com.skynest.uitesting.webdriver.WebDriverFactory;
import com.skynest.uitesting.webdriver.WebDriverPreferenceConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

public class BucketPage extends LoadableComponent<BucketPage> {

    private final WebDriver driver;
    private final PageActions pageActions;
    private static final String BASE_URL = ConfigurationManager.getBrowserConfigInstance().baseUrl() + "/bucket/";
    private final String bucketSpecificUrl;
    private String localPath;
    public static final String DOWNLOADED_FILE_DEFAULT_PATH = WebDriverPreferenceConstants.DOWNLOAD_PATH + "\\upload_test_file.txt";

    @FindBy(xpath = "(//div[@class = 'dropdown'])[2]/button") private WebElement fileDropdownButton;
    @FindBy(xpath = "//a[normalize-space()='Download File']") private WebElement downloadFileOption;
    @FindBy(xpath = "//span[contains(text(), 'Upload')]") private WebElement uploadToBucketButton;
    @FindBy(xpath = "//input[@id='formFile']") private WebElement modalChooseFileButton;
    @FindBy(xpath = "//button[normalize-space()='Upload']") private WebElement modalUploadButton;
    @FindAll({ @FindBy(xpath = "") }) private List<WebElement> files;

    public BucketPage(WebDriver driver, UUID bucketUUID) {
        this.driver = driver;
        bucketSpecificUrl = BASE_URL + bucketUUID.toString();
        PageFactory.initElements(driver, this);
        pageActions = new PageActions(driver);
    }

    public void downloadFirstFile() {
        fileDropdownButton.click();
        downloadFileOption.click();
    }

    public void uploadAFileToBucket(String name) {
        uploadToBucketButton.click();
        localPath = "C:\\" + name + ".txt";
        createFile();
        modalChooseFileButton.sendKeys(localPath);
        modalUploadButton.click();
    }

    public boolean isFileDownloaded() {
        By downloadSuccessfulAlert = By.cssSelector(".alert-success");
        pageActions.waitForElement(driver, downloadSuccessfulAlert, 2);
        File file = new File(DOWNLOADED_FILE_DEFAULT_PATH);
        return file.exists();
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
        driver.get(bucketSpecificUrl);
    }

    @Override
    protected void isLoaded() throws Error {
        assertEquals(driver.getCurrentUrl(), bucketSpecificUrl);
        By fileLocator = By.xpath("//div[@class = 'row files']/div");
        pageActions.waitForElement(driver, fileLocator, 3);
    }
}
