package com.github.msafonov.corporate.bot;

import java.util.List;
import java.util.Random;

public class UniqueCode {


    public String generateCodeNumber(List<String> existingCode) {
        String newCode;
        Random random = new Random();
        do {
            int numb = random.nextInt(899999) + 100000;
            newCode = String.valueOf(numb);
        } while (existingCode.contains(newCode));

        return newCode;
    }
}
