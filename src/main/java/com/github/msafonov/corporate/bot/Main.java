package com.github.msafonov.corporate.bot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Properties;

/**
 * @author Mike Safonov
 */
public class Main {
    public static void main(String[] args) {
        Information information=new Information();

       String token=information.getToken();
        System.out.println(token);

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Bot(information));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
