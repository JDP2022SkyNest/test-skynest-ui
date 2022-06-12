package com.skynest.uitesting.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RandomGenerator {

    public static String generateValidEmail() {
        return "mymail" + System.nanoTime() + "@gmail.com";
    }
}
