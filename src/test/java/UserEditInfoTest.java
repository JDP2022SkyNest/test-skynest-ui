import com.skynest.uitesting.models.EditedUser;
import com.skynest.uitesting.models.User;
import com.skynest.uitesting.pages.EditUserInfoForm;
import com.skynest.uitesting.pages.UserInfoPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static com.skynest.uitesting.constants.PageUrlConstants.USER_PROFILE_URL;

public class UserEditInfoTest extends BaseTest {

    //UserLoginTest login = new UserLoginTest();


    @Test(
            //dependsOnMethods = {"login_valid_user_redirect_to_home_page"}
    )
    public void editing_users_details() throws InterruptedException {

        //new UserLoginTest().login_valid_user_redirect_to_home_page();
        //login.login_valid_user_redirect_to_home_page();
        login();

        //userInfoPage.openPage();
        //Select userProfileDropDownButton = new Select(driver.findElement(By.id("dropdown-menu-align-end")));
        //userProfileDropDownButton.selectByVisibleText("Your Profile");
        //userProfileDropDownButton.selectByIndex(0);
        //UserInfoPage.editUserInfoDropdownButton(driver).click();
        //UserInfoPage.editUserInfoButton(driver).click();

//        WebElement dropDownButton = driver.findElement(By.id("dropdown-menu-align-end"));
//        dropDownButton.click();
//        Select selectButton = new Select(dropDownButton);
//        selectButton.selectByIndex(0);
        UserInfoPage selectOptions = new UserInfoPage(driver, wait);
        selectOptions.selectUserOptions();
        Thread.sleep(3000);



//        WebElement userProfileButton = driver.findElement(By.cssSelector("#dropdown-menu-align-end"));
//        userProfileButton.click();
//        WebElement selectOption = driver.findElement(By.xpath("//a[normalize-space()='Your Profile']"));
//        Select select = new Select(selectOption);
//        Thread.sleep(3000);

        //driver.findElement(By.id("dropdown-menu-align-end")).sendKeys("Your Profile");

//        List<WebElement>profileOptions = userProfileButtonSelect.getOptions();
//        for (WebElement option:profileOptions) {
//            if (option.getText().trim().equals("Your Profile")) {
//                option.click();
//                break;
//            }
//        }


        EditedUser editedUser = EditedUser.generateModifiedData();
        EditUserInfoForm editUserInfoForm = userInfoPage.fillEditedForm()
                .withFirstName(editedUser.getFirstName())
                .withLastName(editedUser.getLastName())
                .withPhoneNumber(editedUser.getPhoneNumber())
                .withCompanyPosition(editedUser.getCompanyPosition())
                .withAddress(editedUser.getAddress());
        userInfoPage.scroll(editUserInfoForm.editUserInfoButton);
        editUserInfoForm.submitEditedForm();
        Assert.assertEquals(getCurrentUrl(), USER_PROFILE_URL);
    }
}

 //   @Test
//    public void login_valid_user_redirect_to_home_page() throws InterruptedException {
//        loginPage.openPage();
//        LoginForm loginForm = loginPage.fillLoginForm()
//                .withEmail("nemanja.mihajlovic@htecgroup.com")
//                .withPassword("Uzivamufanti12345");
//        loginPage.scroll(loginForm.loginButtonSelector);
//        loginForm.submitForm();
//        waitForUrl(DASHBOARD_URL);

        //userProfileDDbutton = Select(driver.findElement(By.xpath("//button[@id='dropdown-menu-align-end']")));

        //Select userProfileDDbutton = new Select(driver.findElement(By.xpath("//button[@id='dropdown-menu-align-end']")));
        //Select userProfileDDbutton = new Select(driver.findElement(By.id("dropdown-menu-align-end")));
        //wait.until()
        //userProfileDDbutton.selectByVisibleText("Your Profile");
        //waitForUrl(USER_PROFILE_URL);
//        Assert.assertEquals(getCurrentUrl(), DASHBOARD_URL);
//        WebElement logo = driver.findElement(By.xpath("//div[@class='tool-bar']"));
//        Assert.assertTrue(logo.isDisplayed());
//        driver.navigate().to(DASHBOARD_URL);
//        HomePage.userProfileDropdown(driver).click();

