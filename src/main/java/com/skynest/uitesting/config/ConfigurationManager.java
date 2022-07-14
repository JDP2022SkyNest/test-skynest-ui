package com.skynest.uitesting.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.aeonbits.owner.ConfigCache;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConfigurationManager {

    public static BrowserConfig getBrowserConfigInstance() { return ConfigCache.getOrCreate(BrowserConfig.class); }
    public static CredentialsConfig getCredentialsConfigInstance() { return ConfigCache.getOrCreate(CredentialsConfig.class); }
}
