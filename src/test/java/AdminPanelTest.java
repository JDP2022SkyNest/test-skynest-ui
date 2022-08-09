import com.skynest.uitesting.api.LoginRequest;
import com.skynest.uitesting.api.User;
import com.skynest.uitesting.api.UsersApiUtil;
import com.skynest.uitesting.pages.AdminPanelPage;
import com.skynest.uitesting.pages.HomePage;
import com.skynest.uitesting.pages.LoginPage;
import io.restassured.response.Response;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class AdminPanelTest extends TestSetup {

    @Test
    public void admin_should_be_able_to_promote_existing_worker_users() {
        // ARRANGE
        User user = getAWorkerIfAny();

        new LoginPage(driver).get();
        setBrowserAuthToken();
        HomePage homePage = new HomePage(driver).get();
        AdminPanelPage adminPanelPage = homePage.goToAdminPanelPage();

        // ACT
        adminPanelPage.searchAndPromoteUser(user);

        // ASSERT
        assertTrue(adminPanelPage.isSuccessMessageDisplayed());
    }

    private User getAWorkerIfAny() {
        LoginRequest loginRequest = new LoginRequest(email, password);
        apiClient.login(loginRequest);
        Response getUsers = apiClient.getAllUsers();
        List<User> users = getUsers.then().extract().jsonPath().getList(".", User.class);
        User user = UsersApiUtil.getFirstWorkerIfAny(users);
        if (user == null) {
            throw new SkipException("Test prerequisite failed: no available workers users to be promoted");
        }
        return user;
    }
}
