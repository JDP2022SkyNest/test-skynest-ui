import com.skynest.uitesting.pages.HomePage;
import com.skynest.uitesting.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestSetup {

    @Test
    public void valid_login_should_take_to_home_page() {
        // ACT
        LoginPage loginPage = new LoginPage(driver).get();
        loginPage.loginAs(email, password);

        // ASSERT
        Assert.assertEquals(getCurrentUrl(), HomePage.URL);
    }
}

