import com.skynest.uitesting.pages.BucketPage;
import com.skynest.uitesting.pages.HomePage;
import com.skynest.uitesting.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DownloadFileTest extends TestSetup {

    @Test
    public void download_file_from_folder() throws InterruptedException {
        // ARRANGE
        LoginPage loginPage = new LoginPage(driver).get();
        HomePage homePage = loginPage.loginAs(email, password);

        // ACT
        BucketPage bucketPage = homePage.goToBucket();
        bucketPage.downloadFile();

        // ASSERT
        Assert.assertTrue(homePage.isAlertPresent());
    }
}
