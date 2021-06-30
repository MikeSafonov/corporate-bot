package com.github.msafonov.corporate.bot;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class PropertiesReader {


    private Properties properties;
    private String token;
    private String botName;


    PropertiesReader() throws IOException {
        String path = "src/main/resources/config.properties";
        File file = new File(path);


        properties = new Properties();
        try (var reader = new FileReader(file)) {
            properties.load(reader);
        }
        token = properties.getProperty("token");
        botName = properties.getProperty("botName");
    }


    public BotProperties getBotProperties() {
        return new BotProperties(token, botName);
    }
}
