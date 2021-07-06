package com.github.msafonov.corporate.bot;

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

        Migration migration = new Migration("jdbc:postgresql://localhost:5432/test_db", "postgres", "postgres", "public");
        migration.migrateToNewVersion();

        CreateBot createBot = new CreateBot(botProperties);
    }
}




