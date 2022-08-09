package com.skynest.uitesting.models;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.skynest.uitesting.utils.RandomGenerator.generateValidEmail;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String homeAddress;
    private String position;
    private String password;
    private String confirmPassword;

    public static User generateValidUser() {
        Faker faker = new Faker();
        String randomPassword = "Selenium22";
        String randomPhoneNumber = faker.phoneNumber().phoneNumber()
                .replace("-", "")
                .replace("\\.", "");

        return User.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .emailAddress(generateValidEmail())
                .phoneNumber(randomPhoneNumber)
                .homeAddress(faker.address().streetAddress())
                .position(faker.lordOfTheRings().character())
                .password(randomPassword)
                .confirmPassword(randomPassword)
                .build();
    }
}
