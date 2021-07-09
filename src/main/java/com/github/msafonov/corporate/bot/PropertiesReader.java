package com.github.msafonov.corporate.bot.Property;


import com.github.msafonov.corporate.bot.AdminsProperties;
import com.github.msafonov.corporate.bot.BotProperties;
import com.github.msafonov.corporate.bot.StorageProperties;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class PropertiesReader {


    private final String token;
    private final String botName;
    private final String chatId;
    private String vacationNo;
    private String vacationWith;
    private String discharge;
    private String dischargeList;

    public PropertiesReader() throws IOException {
        String path = "src/main/resources/config.properties";
        File file = new File(path);


        Properties properties = new Properties();
        try (var reader = new FileReader(file)) {
            properties.load(reader);
        }
        token = properties.getProperty("token");
        botName = properties.getProperty("botName");
        vacationNo = properties.getProperty("vacationNo");
        vacationWith = properties.getProperty("vacationWith");
        discharge = properties.getProperty("discharge");
        dischargeList = properties.getProperty("dischargeList");
        path = "src/main/resources/admins.properties";
        file = new File(path);
        try (var reader = new FileReader(file)) {
            properties.load(reader);
        }
        chatId = properties.getProperty("chatId");
    }


    public BotProperties getBotProperties() {
        return new BotProperties(token, botName);
    }

    public AdminsProperties getAdminsProperties() {
        return new AdminsProperties(chatId);
    }

    public StorageProperties getStorageProperties() {
        return new StorageProperties(vacationNo, vacationWith, discharge, dischargeList);
    }
}