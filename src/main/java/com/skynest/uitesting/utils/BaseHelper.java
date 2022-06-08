package com.skynest.uitesting.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BaseHelper {
    public static String randomEmail() {
        String myEmailAddress = "mymail" + System.nanoTime() + "@gmail.com";
        return myEmailAddress;
    }
}
