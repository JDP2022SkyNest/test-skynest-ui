import com.skynest.uitesting.pages.LoginForm;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.skynest.uitesting.constants.PageUrlConstants.DASHBOARD_URL;

public class UserLoginTest extends BaseTest {

    @Test
    public void login_valid_user_redirect_to_home_page() throws InterruptedException {
        loginPage.openPage();
        LoginForm loginForm = loginPage.fillLoginForm()
                .withEmail("nemanja.mihajlovic@htecgroup.com")
                .withPassword("Uzivamufanti12345");
        loginPage.scroll(loginForm.loginButtonSelector);
        loginForm.submitForm();
        waitForUrl(DASHBOARD_URL);
        Assert.assertEquals(getCurrentUrl(), DASHBOARD_URL);
        driver.close();
        driver.quit();
    }
}
