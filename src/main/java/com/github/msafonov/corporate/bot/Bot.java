package com.github.msafonov.corporate.bot;

import com.github.msafonov.corporate.bot.controllers.EntityController;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.IOException;


public class Bot extends TelegramLongPollingBot {

    private final BotProperties botProperties;
    private Keyboard keyboard = new Keyboard();
    private final BotCommands botCommands;
    private final EntityController entityController;

    public Bot(BotProperties botProperties, FileStorage fileStorage, EntityController entityController) {
        this.botProperties = botProperties;
        this.entityController = entityController;
        this.botCommands = new BotCommands(fileStorage, this.keyboard, this.entityController);

    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            Message messageFromUpdate = update.getMessage();
            SendMessage message = new SendMessage();
            message.setChatId(messageFromUpdate.getChatId().toString());
            message.setText("Добрый день, ваш запрос принят на обработку, ожидайте");
            message.setReplyMarkup(keyboard.getReplyKeyboardMarkup());

            try {
                ReplyValues replyValues = botCommands.processCommand(messageFromUpdate.getText(), entityController, message.getChatId());
                message.setText(replyValues.getMessage());
                if (replyValues.getFile() != null) {
                    sendFile(message.getChatId(), replyValues.getFile());
                }
            } catch (IOException | TelegramApiException e) {
                e.printStackTrace();
            }

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }
    }

    public void sendFile(String chatId, File file) throws TelegramApiException {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setDocument(new InputFile(file));

        execute(sendDocument);
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
