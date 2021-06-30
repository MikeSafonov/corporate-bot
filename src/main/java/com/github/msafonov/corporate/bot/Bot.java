package com.github.msafonov.corporate.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


public class Bot extends TelegramLongPollingBot {
    Information information ;
Bot(Information information){
    this.information=information;
}
    @Override
    public void onUpdateReceived(Update update) {
        // TODO
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            message.setChatId(update.getMessage().getChatId().toString());
            System.out.println(update.getMessage().getText());

            message.setText(utf8("Добрый день, ваш запрос принят на обработку, ожидайте"));

//update.getMessage().getText()
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        // TODO
        return information.getBotName();
    }

    @Override
    public String getBotToken() {
        // TODO
        return information.getToken();
    }

    public String utf8(String str) {
        byte[] byteArray = str.getBytes();
        Charset utf8 = StandardCharsets.UTF_8;
        return new String(byteArray, utf8);
    }
}
