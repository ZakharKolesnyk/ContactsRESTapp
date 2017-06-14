package com.gmail.kolesnyk.zakhar.config;

import java.io.IOException;
import java.util.Properties;

public class Environment {

    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
