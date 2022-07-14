package com.skynest.uitesting.config;

import org.aeonbits.owner.Config;

@Config.Sources("file:${user.dir}/src/main/resources/credentials.properties")
public interface CredentialsConfig extends Config {

    @Key("email")
    String email();

    @Key("password")
    String password();
}
