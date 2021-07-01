package com.github.msafonov.corporate.bot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * @author Mike Safonov
 */
public class Main {
    public static void main(String[] args) {

        PropertiesReader propertiesReader=new PropertiesReader();
        BotProperties botProperties=propertiesReader.setBotProperties();
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Bot(botProperties));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
