import com.skynest.uitesting.pages.BucketPage;
import com.skynest.uitesting.pages.HomePage;
import com.skynest.uitesting.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UploadFileTest extends TestSetup  {

    @Test
    public void upload_file_from_folder() throws InterruptedException {
        // ARRANGE
        String desiredFileName = "uzivamUFanti";

        LoginPage loginPage = new LoginPage(driver).get();
        HomePage homePage = loginPage.loginAs(email, password);

        // ACT
        BucketPage bucketPage = homePage.goToBucket();
        bucketPage.uploadAFileToBucket(desiredFileName);

        // ASSERT
        Assert.assertTrue(bucketPage.isFilePresent(desiredFileName));
        //Assert.assertTrue(bucketPage.isPresentByFileName(desiredFileName));
        // Assert.assertTrue(homePage.isAlertPresent());
    }
}
