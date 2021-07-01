package com.github.msafonov.corporate.bot;


import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class PropertiesReader {


    private Properties properties;
    private String token;
    private String botName;


    PropertiesReader() {
        String path="src/main/resources/config.properties";
        File file = new File(path);


        properties = new Properties();

        try {
            FileReader fileReader= new FileReader(file);
            properties.load(fileReader);
            fileReader.close();
        } catch (IOException e) {
            System.err.println("File config.properties was not found or spoiled");
        }

        token = properties.getProperty("token");
        botName = properties.getProperty("botName");
    }

    public BotProperties setBotProperties(){
        return new BotProperties(token, botName);
    }
}
