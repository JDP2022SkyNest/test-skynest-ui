package com.skynest.uitesting.models;

import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.skynest.uitesting.utils.RandomGenerator.generateValidEmail;

@Getter
@RequiredArgsConstructor
public class User {

    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final String phoneNumber;
    private final String homeAddress;
    private final String password;
    private final String confirmPassword;

    public static User generateValidUser() {
        Faker faker = new Faker();
        String randomPassword = "Selenium22";
        String randomPhoneNumber = faker.phoneNumber().phoneNumber().replaceAll("-", "").replaceAll("\\.", "");
        return new User(faker.name().firstName(), faker.name().lastName(), generateValidEmail(), randomPhoneNumber, faker.address().fullAddress(), randomPassword, randomPassword);
    }
}
