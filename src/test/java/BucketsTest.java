import com.skynest.uitesting.models.User;
import com.skynest.uitesting.pages.HomePage;
import com.skynest.uitesting.pages.LoginPage;
import org.testng.annotations.Test;

public class BucketsTest extends TestSetup{

    @Test
    public void create_bucket() throws InterruptedException {
        // ARRANGE
        LoginPage loginPage = new LoginPage(driver).get();
        HomePage homePage = loginPage.loginAs(email, password);

        // ACT
        homePage.clickAndCreateBucket();
        homePage.fillCreatingBucketModal();

        // ASSERT

    }
}
