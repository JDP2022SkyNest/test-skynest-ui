package com.skynest.uitesting.models;

import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EditedUser {

        private final String firstName;
        private final String lastName;
        private final String phoneNumber;
        private final String companyPosition;
        private final String address;

    public static EditedUser generateModifiedData() {
        Faker fakeData = new Faker();
        String randomPosition = "DevOps";
        String randomPhoneNumber = fakeData.phoneNumber().phoneNumber().replaceAll("-", "").replaceAll("\\.", "");
        return new EditedUser(
                fakeData.name().firstName(),
                fakeData.name().lastName(),
                randomPhoneNumber,
                randomPosition,
                fakeData.address().streetAddress()
                );
    }
}
