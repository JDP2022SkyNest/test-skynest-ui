package com.skynest.uitesting.models;

import com.skynest.uitesting.utils.RandomGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Bucket {

    private final String name;
    private final String description;

    public static Bucket createRandomValidBucket() {
        final int randomInt = RandomGenerator.generateRandomIntWithinRange(0, 9999);
        String name = "bucket" + randomInt;
        String description = "description" + randomInt;
        return new Bucket(name, description);
    }
}
