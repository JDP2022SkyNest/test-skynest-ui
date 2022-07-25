import com.skynest.uitesting.pages.HomePage;
import com.skynest.uitesting.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteBucketTest extends TestSetup{

    @Test
    public void delete_bucket() {
        // ARRANGE
        LoginPage loginPage = new LoginPage(driver).get();
        HomePage homePage = loginPage.loginAs(email, password);

        // ACT
        homePage.deleteBucket();

        // ASSERT
        Assert.assertTrue(homePage.isAlertPresent());
    }
}
