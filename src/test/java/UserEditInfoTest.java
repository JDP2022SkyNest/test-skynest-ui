import com.skynest.uitesting.models.EditedUser;
import com.skynest.uitesting.pages.EditUserInfoForm;
import com.skynest.uitesting.pages.UserInfoPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.skynest.uitesting.constants.PageUrlConstants.USER_PROFILE_URL;

public class UserEditInfoTest extends BaseTest {

    UserInfoPage selectOptions = new UserInfoPage(driver, wait);

    @Test()
    public void editing_users_details() throws InterruptedException {

        login();
        selectOptions.selectUserOptions();
        selectOptions.waitAndClickEditButton();

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

