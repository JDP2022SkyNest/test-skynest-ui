import com.skynest.uitesting.pages.HomePage;
import com.skynest.uitesting.pages.LoginPage;
import org.testng.annotations.Test;

public class InviteUserTest extends TestSetup{

    @Test
    public void invite_user_by_sending_email() {
        // ARRANGE
        LoginPage loginPage = new LoginPage(driver).get();
        HomePage homePage = loginPage.loginAs(email, password);

        // ACT
        homePage.sendEmailToInviteUser();

        // ASSERT

    }
}
