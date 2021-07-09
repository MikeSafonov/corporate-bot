package com.github.msafonov.corporate.bot;

import com.github.msafonov.corporate.bot.controllers.EntityController;
import org.mockito.Mock;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Random;

public class UniqueCode {

Queries queries;


    public String generateCodeNumber(EntityManager entityManager) {
        queries=new Queries(entityManager);
        String newCode = "0";
        Random random = new Random();
        try {
            do {
                int numb = random.nextInt(899999) + 100000;
                newCode = String.valueOf(numb);
            } while (!queries.uniqueCodeQuery(newCode).isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
        }
       

        return newCode;
    }
}
