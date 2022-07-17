import com.skynest.uitesting.pages.HomePage;
import com.skynest.uitesting.pages.LoginPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class LoginTest extends TestSetup {

    @Test
    public void valid_login_should_take_to_home_page() {
        // ACT
        LoginPage loginPage = new LoginPage(driver).get();
        HomePage homePage = loginPage.loginAs(email, password);

        // ASSERT
        assertTrue(homePage.isCorrectlyDisplayed());
    }
}

