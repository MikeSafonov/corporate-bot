package com.github.msafonov.corporate.bot;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class PropertiesReader {


    private Properties properties;
    private String token;
    private String botName;
    private String vacationNo;
    private String vacationWith;
    private String discharge;
    private String dischargeList;


    PropertiesReader() throws IOException {
        String path = "src/main/resources/config.properties";
        File file = new File(path);

        properties = new Properties();
        try (var reader = new FileReader(file)) {
            properties.load(reader);
        }
        token = properties.getProperty("token");
        botName = properties.getProperty("botName");
        vacationNo = properties.getProperty("vacationNo");
        vacationWith = properties.getProperty("vacationWith");
        discharge = properties.getProperty("discharge");
        dischargeList = properties.getProperty("dischargeList");
    }


    public BotProperties getBotProperties() {
        return new BotProperties(token, botName);
    }

    public StorageProperties getStorageProperties() {
        return new StorageProperties(vacationNo, vacationWith, discharge, dischargeList);
    }
}
