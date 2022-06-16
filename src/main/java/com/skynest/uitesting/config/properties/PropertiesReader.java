package com.skynest.uitesting.config.properties;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class PropertiesReader {
    private static PropertiesReader instance;
    private static CompositeConfiguration configuration;

    private PropertiesReader() {
        configuration = new CompositeConfiguration();
        try {
            configuration.addConfiguration(new PropertiesConfiguration("config.properties"));
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static PropertiesReader getInstance() {
        if (instance == null) {
            synchronized (PropertiesReader.class) {
                if (instance == null) {
                    instance = new PropertiesReader();
                }
            }
        }
        return instance;
    }

    public String getProperty(String propertyName) {
        return configuration.getString(propertyName);
    }
}