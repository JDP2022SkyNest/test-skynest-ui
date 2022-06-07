package com.skynest.uitesting.utils;

public class BaseHelper {
    public String randomEmail(){
        String myEmailAddress = "mymail" + System.nanoTime() + "@gmail.com";
        return myEmailAddress;
    }
}
