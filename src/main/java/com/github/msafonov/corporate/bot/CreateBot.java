package com.github.msafonov.corporate.bot;

import com.github.msafonov.corporate.bot.Property.AdminsProperties;
import com.github.msafonov.corporate.bot.Property.BotProperties;
import com.github.msafonov.corporate.bot.Property.PropertiesReader;
import com.github.msafonov.corporate.bot.Property.StorageProperties;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CreateBot {
    CreateBot(BotProperties botProperties, AdminsProperties adminsProperties, StorageProperties storageProperties) {
        try {
            if (botProperties.getBotName() == null || botProperties.getToken() == null) {
                throw new Exception("botName or botToken not found");
            }
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Bot(botProperties,adminsProperties,storageProperties));
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
