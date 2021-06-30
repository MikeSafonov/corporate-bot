package com.github.msafonov.corporate.bot;


import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class Information {


    private Properties properties;
    private String token;
    private String botName;


    Information() {
        String path="src/main/resources/config.properties";
        File file = new File(path);


        properties = new Properties();

        try {
            properties.load(new FileReader(file));
        } catch (IOException e) {
            System.err.println("Файл config.properties не найден или испорчен");
        }
        token = properties.getProperty("token");
        botName = properties.getProperty("botName");
    }

    public Properties getProperties() {
        return properties;
    }

    public String getToken() {
        return token;
    }

    public String getBotName() {
        return botName;
    }

}
