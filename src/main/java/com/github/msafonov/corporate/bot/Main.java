package com.github.msafonov.corporate.bot;

import com.github.msafonov.corporate.bot.controllers.EntityController;
import com.github.msafonov.corporate.bot.entities.Action;
import com.github.msafonov.corporate.bot.entities.AuthorizationCode;
import com.github.msafonov.corporate.bot.entities.Employee;
import com.github.msafonov.corporate.bot.entities.TypeOfAction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * @author Mike Safonov
 */
public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        PropertiesReader propertiesReader = new PropertiesReader();
        StorageProperties storageProperties = propertiesReader.getStorageProperties();
        BotProperties botProperties = propertiesReader.getBotProperties();

        Migration migration = new Migration("jdbc:postgresql://localhost:5432/bot", "postgres", "spfpgsdb", "public");
        migration.migrateToNewVersion();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("entities");
        EntityManager em = emf.createEntityManager();
        EntityController ec = new EntityController(em);
        EmployeeLoader el = new EmployeeLoader(ec);
        Authorization auth = new Authorization(ec);
        AuthorizationCode ac = ec.read(AuthorizationCode.class, 45);
        var e = new Employee();
        e.setFio(null);
        e.setPhone(null);
        e.setEmail(null);
        e.setUserId("8678");
        ec.save(e);
//
//        for (Employee e: list
//             ) {
//            System.out.println(e);
//        }
        CreateBot createBot = new CreateBot(botProperties);
    }
}