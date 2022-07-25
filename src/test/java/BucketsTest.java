import com.skynest.uitesting.models.Bucket;
import com.skynest.uitesting.pages.BucketModal;
import com.skynest.uitesting.pages.HomePage;
import com.skynest.uitesting.pages.LoginPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class BucketsTest extends TestSetup{

    @Test
    public void logged_user_can_create_a_bucket() throws InterruptedException {
        // ARRANGE
        Bucket desiredBucket = Bucket.createRandomValidBucket();
        LoginPage loginPage = new LoginPage(driver).get();
        HomePage homePage = loginPage.loginAs(email, password);

        // ACT
        BucketModal bucketModal = homePage.attemptBucketCreation(desiredBucket);
        homePage = bucketModal.submitBucketRequest();

        // ASSERT
        assertTrue(homePage.hasBucket(desiredBucket), "Bucket is visible with correct details");
    }
}
