package com.github.msafonov.corporate.bot;

import com.github.msafonov.corporate.bot.controllers.EntityController;
import org.mockito.Mock;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Random;

public class UniqueCode {



    public String generateCodeNumber(EntityController entityController) {
        String newCode;
        Random random = new Random();

            do {
                int numb = random.nextInt(899999) + 100000;
                newCode = String.valueOf(numb);
            } while (!entityController.uniqueCodeQuery(newCode).isEmpty());
       

        return newCode;
    }
}
