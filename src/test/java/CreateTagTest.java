import com.skynest.uitesting.models.User;
import com.skynest.uitesting.pages.HomePage;
import com.skynest.uitesting.pages.LoginPage;
import com.skynest.uitesting.pages.ProfilePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateTagTest extends TestSetup{

    @Test
    public void create_tag() {
        // ARRANGE
        LoginPage loginPage = new LoginPage(driver).get();
        HomePage homePage = loginPage.loginAs(email, password);

        // ACT
        homePage.createTag();

        // ASSERT
        // Assert.assertTrue(homePage.isAlertPresent());
    }
}
