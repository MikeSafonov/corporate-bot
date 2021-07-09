package com.github.msafonov.corporate.bot;

import com.github.msafonov.corporate.bot.controllers.EntityController;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CreateBot {
    CreateBot(BotProperties botProperties, FileStorage fileStorage) {
        try {
            PropertiesReader propertiesReader = new PropertiesReader();
            if (botProperties.getBotName() == null || botProperties.getToken() == null) {
                throw new Exception("botName or botToken not found");
            }
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("entities");
            EntityManager em = emf.createEntityManager();
            EntityController ec = new EntityController(em);
            botsApi.registerBot(new Bot(botProperties, fileStorage, ec));
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
