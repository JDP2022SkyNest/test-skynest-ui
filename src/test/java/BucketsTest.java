import com.skynest.uitesting.models.Bucket;
import com.skynest.uitesting.pages.BucketModal;
import com.skynest.uitesting.pages.HomePage;
import com.skynest.uitesting.pages.LoginPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.URISyntaxException;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class BucketsTest extends TestSetup {

    private static final String VALID_BUCKET_PROVIDER = "validBucketProvider";

    @Test(dataProvider = VALID_BUCKET_PROVIDER)
    public void logged_user_can_create_a_bucket(Bucket desiredBucket) throws URISyntaxException {
        // ARRANGE
        new LoginPage(driver).get();
        setBrowserAuthToken();
        HomePage homePage = new HomePage(driver).get();

        // ACT
        BucketModal bucketModal = homePage.openBucketCreationModal();
        homePage = bucketModal.createBucket(desiredBucket);

        // ASSERT
        assertTrue(homePage.hasBucket(desiredBucket), "Bucket is not visible or has incorrect details");
    }

    @Test(dataProvider = VALID_BUCKET_PROVIDER)
    public void logged_user_can_delete_bucket(Bucket desiredBucket) throws URISyntaxException {
        // ARRANGE
        new LoginPage(driver).get();
        setBrowserAuthToken();
        HomePage homePage = new HomePage(driver).get();
        BucketModal bucketModal = homePage.openBucketCreationModal();
        homePage = bucketModal.createBucket(desiredBucket);

        // ACT
        homePage.deleteBucket(desiredBucket);

        // ASSERT
        assertTrue(homePage.hasDisplayedSuccessMessage(), "No success feedback has been displayed");
    }

    @DataProvider(name = VALID_BUCKET_PROVIDER)
    public Object[][] validBucketProvider() {
        return new Object[][] {
                { Bucket.createRandomValidBucket() }
        };
    }
}
