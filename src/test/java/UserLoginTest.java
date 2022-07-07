import com.skynest.uitesting.pages.HomePage;
import com.skynest.uitesting.pages.LoginForm;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.skynest.uitesting.constants.PageUrlConstants.*;

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
        WebElement logo = driver.findElement(By.xpath("//div[@class='tool-bar']"));
        Assert.assertTrue(logo.isDisplayed());
        driver.navigate().to(DASHBOARD_URL);
        HomePage.userProfileDropdown(driver).click();
        //HomePage.userProfileDropdown(driver).sendKeys(Keys.RETURN);
    }
}
