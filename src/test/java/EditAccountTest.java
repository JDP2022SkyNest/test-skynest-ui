import com.skynest.uitesting.models.User;
import com.skynest.uitesting.pages.LoginPage;
import com.skynest.uitesting.pages.ProfilePage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class EditAccountTest extends TestSetup {

    @Test
    public void user_should_be_able_to_edit_his_account_info() {
        // ARRANGE
        User newUserInfo = User.generateValidUser();

        new LoginPage(driver).get();
        setBrowserAuthToken();

        // ACT
        ProfilePage profilePage = new ProfilePage(driver).get();
        profilePage.editInfoTo(newUserInfo);

        // ASSERT
        assertTrue(profilePage.isSuccessMessageDisplayed());
    }
}
