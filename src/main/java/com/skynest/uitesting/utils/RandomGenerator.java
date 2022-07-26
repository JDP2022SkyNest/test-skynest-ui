package com.skynest.uitesting.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RandomGenerator {

    public static int generateRandomIntWithinRange(int from, int to) {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        return from + threadLocalRandom.nextInt(to);
    }

    public static String generateValidEmail() {
        return "mymail" + System.nanoTime() + "@gmail.com";
    }
}
