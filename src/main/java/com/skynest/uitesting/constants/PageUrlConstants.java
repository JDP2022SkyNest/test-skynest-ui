package com.skynest.uitesting.constants;

public class PageUrlConstants {
    //TODO Extract BASE_URL into environmental variable and use it in BasePage
    private static final String BASE_URL = "http://localhost:3000";
    public static final String LOGIN_URL = BASE_URL + "/login";
    public static final String REGISTER_URL = BASE_URL + "/signup";
}
