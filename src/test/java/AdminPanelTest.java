import com.skynest.uitesting.pages.AdminPanelPage;
import com.skynest.uitesting.pages.HomePage;
import com.skynest.uitesting.pages.LoginPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class AdminPanelTest extends TestSetup{

    @Test
    public void admin_should_be_able_to_promote_user() {
        // ARRANGE
        LoginPage loginPage = new LoginPage(driver).get();
        HomePage homePage = loginPage.loginAs(email, password);

        // ACT
        AdminPanelPage adminPanelPage = homePage
                .goToAdminPanelPage()
                .promoteSomeUserIfAny();

        // ASSERT
        assertTrue(adminPanelPage.isSuccessMessageDisplayed());
    }
}
