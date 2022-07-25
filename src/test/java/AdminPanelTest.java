import com.skynest.uitesting.pages.AdminPanelPage;
import com.skynest.uitesting.pages.HomePage;
import com.skynest.uitesting.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminPanelTest extends TestSetup{

    @Test
    public void admin_should_be_able_to_promote_user() throws InterruptedException {
        // ARRANGE
        LoginPage loginPage = new LoginPage(driver).get();
        HomePage homePage = loginPage.loginAs(email, password);

        // ACT
        AdminPanelPage adminPanelPage = homePage.goToAdminPanelPage();
        adminPanelPage.promoteFirstUser();

        // ASSERT
        Assert.assertTrue(adminPanelPage.promoteUserButton.isDisplayed());
    }
}
