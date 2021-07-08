package com.github.msafonov.corporate.bot;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Random;

public class UniqueCode {
Queries queries;


    public String generateCodeNumber(EntityManager entityManager) {
        queries=new Queries(entityManager);
        String newCode;
        Random random = new Random();
        do {
            int numb = random.nextInt(899999) + 100000;
            newCode = String.valueOf(numb);
        } while (!queries.uniqueCodeQuery(newCode).isEmpty());

        return newCode;
    }
}
