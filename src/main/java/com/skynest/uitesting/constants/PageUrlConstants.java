package com.skynest.uitesting.constants;

public class PageUrlConstants {
    //TODO Extract BASE_URL into environmental variable and use it in BasePage
    private static final String BASE_URL = "http://localhost:3000";
    public static final String LOGIN_URL = BASE_URL + "/login";
    public static final String REGISTER_URL = BASE_URL + "/signup";
    public static final String DASHBOARD_URL = BASE_URL + "";
    public static final String USER_PROFILE_URL = BASE_URL + "/user-info";

    public static final String ADMIN_PANEL_URL  = BASE_URL + "/admin-panel";


}
