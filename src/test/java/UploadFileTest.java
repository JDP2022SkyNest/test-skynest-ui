import com.skynest.uitesting.models.Bucket;
import com.skynest.uitesting.pages.BucketModal;
import com.skynest.uitesting.pages.HomePage;
import org.testng.annotations.Test;

import java.net.URISyntaxException;

public class UploadFileTest extends TestSetup  {

    @Test
    public void logged_admin_can_upload_files_to_existing_buckets() throws URISyntaxException {
        // ARRANGE
        String desiredFileName = "uzivamUFanti";
        Bucket desiredBucket = Bucket.createRandomValidBucket();

        navigateToAPageAndSetToken();
        HomePage homePage = new HomePage(driver).get();
        BucketModal bucketModal = homePage.openBucketCreationModal();
        homePage = bucketModal.createBucket(desiredBucket);

        // ACT
//        BucketPage bucketPage = homePage.openBucket(desiredBucket);
//        bucketPage.uploadAFileToBucket(desiredFileName);
//
//        // ASSERT
//        Assert.assertTrue(bucketPage.isFilePresent(desiredFileName));
    }
}
