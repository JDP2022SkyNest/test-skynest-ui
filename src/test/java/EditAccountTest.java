import com.skynest.uitesting.models.User;
import com.skynest.uitesting.pages.HomePage;
import com.skynest.uitesting.pages.LoginPage;
import com.skynest.uitesting.pages.ProfilePage;
import org.testng.annotations.Test;

public class EditAccountTest extends TestSetup {

    @Test
    public void user_should_be_able_to_edit_his_account_info_with_valid_details() throws InterruptedException {
        // ARRANGE
        User newUserInfo = User.generateValidUser();
        LoginPage loginPage = new LoginPage(driver).get();
        HomePage homePage = loginPage.loginAs(email, password);

        // ACT
        ProfilePage profilePage = homePage.goToProfilePage();
        profilePage.editInfoTo(newUserInfo);

        // ASSERT
    }
}
