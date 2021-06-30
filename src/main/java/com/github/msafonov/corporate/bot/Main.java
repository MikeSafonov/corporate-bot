package com.github.msafonov.corporate.bot;

import java.sql.SQLException;

/**
 * @author Mike Safonov
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        Migration migration = new Migration("jdbc:postgresql://localhost:5432/test_db", "postgres", "postgres", "public");
        migration.migrateToNewVersion();

        CreateBot createBot =new CreateBot();
    }
}




