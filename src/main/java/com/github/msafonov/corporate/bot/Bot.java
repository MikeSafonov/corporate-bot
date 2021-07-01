package com.github.msafonov.corporate.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


public class Bot extends TelegramLongPollingBot {
    private BotProperties botProperties;

    Bot(BotProperties botProperties) {
        this.botProperties = botProperties;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText("Добрый день, ваш запрос принят на обработку, ожидайте");

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {

        return botProperties.getBotName();
    }

    @Override
    public String getBotToken() {

        return botProperties.getToken();
    }


}
