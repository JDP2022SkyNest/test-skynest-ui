import com.skynest.uitesting.models.EditedUser;
import com.skynest.uitesting.pages.EditUserInfoForm;
import com.skynest.uitesting.pages.HomePage;
import com.skynest.uitesting.pages.LoginForm;
import com.skynest.uitesting.pages.UserInfoPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import org.testng.annotations.Test;

import static com.skynest.uitesting.constants.PageUrlConstants.DASHBOARD_URL;
import static com.skynest.uitesting.constants.PageUrlConstants.USER_PROFILE_URL;

public class UserEditInfoTest extends BaseTest {

    @Test(dependsOnMethods = {"login_valid_user_redirect_to_home_page"})
    public void editing_users_details() throws InterruptedException {

        userInfoPage.openPage();
        UserInfoPage.editUserInfoButton(driver).click();
        EditedUser editedUser = EditedUser.generateModifiedData();
        EditUserInfoForm editUserInfoForm = userInfoPage.fillEditedForm()
                .withFirstName(editedUser.getFirstName())
                .withLastName(editedUser.getLastName())
                .withPhoneNumber(editedUser.getPhoneNumber())
                .withCompanyPosition(editedUser.getCompanyPosition())
                .withAddress(editedUser.getAddress());
        userInfoPage.scroll(editUserInfoForm.editUserInfoButton);
        editUserInfoForm.submitEditedForm();
        //Assert.assertEquals();
    }

    @Test
    public void login_valid_user_redirect_to_home_page() throws InterruptedException {
        loginPage.openPage();
        LoginForm loginForm = loginPage.fillLoginForm()
                .withEmail("nemanja.mihajlovic@htecgroup.com")
                .withPassword("Uzivamufanti12345");
        loginPage.scroll(loginForm.loginButtonSelector);
        loginForm.submitForm();
        waitForUrl(DASHBOARD_URL);

        //userProfileDDbutton = Select(driver.findElement(By.xpath("//button[@id='dropdown-menu-align-end']")));

        //Select userProfileDDbutton = new Select(driver.findElement(By.xpath("//button[@id='dropdown-menu-align-end']")));
        Select userProfileDDbutton = new Select(driver.findElement(By.id("dropdown-menu-align-end")));
        //wait.until()
        userProfileDDbutton.selectByVisibleText("Your Profile");
        waitForUrl(USER_PROFILE_URL);
//        Assert.assertEquals(getCurrentUrl(), DASHBOARD_URL);
//        WebElement logo = driver.findElement(By.xpath("//div[@class='tool-bar']"));
//        Assert.assertTrue(logo.isDisplayed());
//        driver.navigate().to(DASHBOARD_URL);
//        HomePage.userProfileDropdown(driver).click();
    }
}
