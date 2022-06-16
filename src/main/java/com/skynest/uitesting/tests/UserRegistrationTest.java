package com.skynest.uitesting.tests;

import com.skynest.uitesting.models.User;
import com.skynest.uitesting.pages.RegistrationForm;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.skynest.uitesting.constants.PageUrlConstants.LOGIN_URL;

public class UserRegistrationTest extends BaseTest {
    @Test
    public void registering_new_valid_user_should_navigate_to_login_page() {
        registrationPage.openPage();
        User user = User.generateValidUser();
        RegistrationForm registrationForm = registrationPage.fillRegistrationForm()
                .withFirstName(user.getFirstName())
                .withLastName(user.getLastName())
                .withEmail(user.getEmailAddress())
                .withPhoneNumber(user.getPhoneNumber())
                .withHomeAddress(user.getHomeAddress())
                .withPassword(user.getPassword())
                .withConfirmPassword(user.getConfirmPassword());
        registrationPage.scroll(registrationForm.registerButtonSelector);
        registrationForm.submitForm();
        waitForUrl(LOGIN_URL);
        Assert.assertEquals(getCurrentUrl(), LOGIN_URL);
    }
}
