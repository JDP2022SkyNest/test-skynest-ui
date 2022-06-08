package com.skynest.uitesting.tests;

import com.skynest.uitesting.pages.RegistrationForm;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserRegistrationTest extends BaseTest {

    @Test
    public void registering_new_valid_user_should_navigate_to_login_page() {
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
        waitForUrl(BASE_URL);
        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL);
    }
}
