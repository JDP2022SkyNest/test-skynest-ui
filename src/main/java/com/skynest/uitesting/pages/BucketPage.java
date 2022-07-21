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
import static org.testng.Assert.assertTrue;

public class BucketPage extends LoadableComponent<BucketPage> {

    private final WebDriver driver;
    private final PageActions pageActions;
    private static final String URL = ConfigurationManager.getBrowserConfigInstance().baseUrl() + "/";

    @FindBy(xpath = "//body/div[@id='root']/div[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[2]/div[1]/button[1]/*[1]") private WebElement fileElipsisButton;
    @FindBy(xpath = "//a[normalize-space()='Download File']") private WebElement downloadFileOption;
    @FindBy(xpath = "//body/div[@id='root']/div[1]/div[1]/div[1]/div[1]/span[1]") private WebElement uploadFile;
    @FindBy(xpath = "//input[@id='formFile']") private WebElement chooseUploadFileInModal;
    @FindBy(xpath = "//button[normalize-space()='Upload']") private WebElement uploadButtonInModal;
    @FindAll({ @FindBy(xpath = "") }) List<WebElement> files;

    public BucketPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        pageActions = new PageActions(driver);
    }

    public void downloadFile() {
        fileElipsisButton.click();
        downloadFileOption.click();
    }

    public void upLoadFileToBucket(String name) {
        uploadFile.click();
        createFile(name);
        chooseUploadFileInModal.sendKeys("C:\\Users\\Nemanja\\Downloads\\" + name + ".txt");
        uploadButtonInModal.click();
    }

    private void createFile(String name) {
        new File("C:\\Users\\Nemanja\\Downloads\\" + name + ".txt");
    }

    public boolean isPresentByFileName(String fileName) {
        return getExistingFileNames().contains(fileName);
    }

    private List<String> getExistingFileNames() {
        // TODO return list motherfucker!
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
        //assertTrue(isCorrectlyDisplayed());
    }
}
