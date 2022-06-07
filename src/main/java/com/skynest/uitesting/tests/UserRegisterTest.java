package com.skynest.uitesting.tests;

import org.testng.annotations.Test;

public class UserRegisterTest extends BaseTest {
    @Test
    public void testUserRegister() {
        loginPage.clickRegisterLink();
        sleep(2000);
        registerPage.enterFirstName(firstName);
        registerPage.enterLastName(lastName);
        registerPage.enterEmail(emailAddress);
        sleep(2000);
        registerPage.enterPhoneNumber(phoneNumber);
        registerPage.enterHomeAddress(homeAddress);
        registerPage.enterPassword(password);
        registerPage.enterConfirmPassword(confirmPassword);
        sleep(2000);
        registerPage.clickRegisterButton();
    }
}
