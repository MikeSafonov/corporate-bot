package com.github.msafonov.corporate.bot;

import com.github.msafonov.corporate.bot.Property.AdminsProperties;
import com.github.msafonov.corporate.bot.Property.BotProperties;
import com.github.msafonov.corporate.bot.Property.PropertiesReader;
import com.github.msafonov.corporate.bot.Property.StorageProperties;
import com.github.msafonov.corporate.bot.controllers.EntityController;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Mike Safonov
 */
public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        PropertiesReader propertiesReader = new PropertiesReader();
        StorageProperties storageProperties = propertiesReader.getStorageProperties();
        BotProperties botProperties = propertiesReader.getBotProperties();
        AdminsProperties adminsProperties=propertiesReader.getAdminsProperties();

        FileStorage fileStorage = new FileStorage(storageProperties);

        Migration migration = new Migration("jdbc:postgresql://localhost:5432/test_db", "postgres", "postgres", "public");
        migration.migrateToNewVersion();

        //Create Bot
        try {

            if (botProperties.getBotName() == null || botProperties.getToken() == null) {
                throw new Exception("botName or botToken not found");
            }
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("entities");
            EntityManager em = emf.createEntityManager();
            EntityController ec = new EntityController(em);
            Authorization authorization=new Authorization(ec,adminsProperties);
            botsApi.registerBot(new Bot(botProperties, fileStorage, ec, authorization));
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}