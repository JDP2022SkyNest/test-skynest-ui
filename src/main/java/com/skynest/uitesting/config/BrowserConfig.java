package com.skynest.uitesting.config;

import org.aeonbits.owner.Config;

@Config.Sources("file:${user.dir}/src/main/resources/browser-config.properties")
public interface BrowserConfig extends Config {

    @Key("targetBrowser")
    @DefaultValue("CHROME")
    String defaultBrowser();

    @Key("baseUrl")
    @DefaultValue("http://13.94.241.83:3000")
    String baseUrl();

    @Key("apiBaseUrl")
    @DefaultValue("http://13.94.241.83:8080")
    String apiBaseUrl();
}
