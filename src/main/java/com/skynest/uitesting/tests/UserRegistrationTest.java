package com.skynest.uitesting.tests;

import com.skynest.uitesting.pages.RegistrationForm;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.skynest.uitesting.constants.PageUrlConstants.LOGIN_URL;

public class UserRegistrationTest extends BaseTest {

    @Test
    public void registering_new_valid_user_should_navigate_to_login_page() {
        registrationPage.openPage();
        loginPage.clickRegisterLink();
        RegistrationForm registrationForm = registrationPage.fillRegistrationForm()
                .withFirstName(firstName)
                .withLastName(lastName)
                .withEmail(emailAddress)
                .withPhoneNumber(phoneNumber)
                .withHomeAddress(homeAddress)
                .withPassword(password)
                .withConfirmPassword(confirmPassword);
        scroll(0, 400);
        registrationForm.submitForm();
        waitForUrl(LOGIN_URL);
        Assert.assertEquals(driver.getCurrentUrl(), LOGIN_URL);
    }
}
