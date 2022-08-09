import com.skynest.uitesting.api.BucketResponse;
import com.skynest.uitesting.models.Bucket;
import com.skynest.uitesting.pages.BucketPage;
import com.skynest.uitesting.pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.util.UUID;

import static org.testng.Assert.assertTrue;

public class DownloadFileTest extends TestSetup {

    @Test
    public void logged_users_should_be_able_to_download_files_from_buckets() {
        // ARRANGE
        Bucket bucket = Bucket.createRandomValidBucket();

        new LoginPage(driver).get();
        setBrowserAuthToken();
        BucketResponse createdBucket = apiClient.createBucket(bucket);
        UUID createdBucketUUID = createdBucket.getBucketId();
        apiClient.uploadTestFileToBucket(createdBucketUUID);

        // ACT
        BucketPage bucketPage = new BucketPage(driver, createdBucketUUID).get();
        bucketPage.downloadFirstFile();

        // ASSERT
        assertTrue(bucketPage.isFileDownloaded());
    }

    @AfterMethod
    public void fileCleanup() {
        File file = new File(BucketPage.DOWNLOADED_FILE_DEFAULT_PATH);
        file.deleteOnExit();
    }
}
