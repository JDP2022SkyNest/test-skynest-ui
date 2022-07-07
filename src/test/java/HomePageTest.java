import com.skynest.uitesting.pages.HomePage;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void testing_homePage_and_navigate_to_adminPanel() throws InterruptedException {

        homePage.openPage();
        HomePage.userProfileDropdown(driver).sendKeys(Keys.RETURN);

    }
}
