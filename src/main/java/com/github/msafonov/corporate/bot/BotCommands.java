package com.github.msafonov.corporate.bot;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.IOException;

public class BotCommands {
    private FileStorage fileStorage;
    private Keyboard keyboard;

    public BotCommands(FileStorage fileStorage, Keyboard keyboard) {
        this.fileStorage = fileStorage;
        this.keyboard = keyboard;
    }

    public ReplyValues processCommand(String message) throws IOException, TelegramApiException {
        switch (message) {
            case "Запросить кадровые шаблоны":
                keyboard.createTemplatesKeyboard();
                return new ReplyValues("Сейчас получите кадровые шаблоны");
            case "Заявление на ежегодный отпуск":
                File vac = fileStorage.getFile(TemplateTypes.VACATION_WITH);
                System.out.println(vac.exists());
                System.out.println(vac.getAbsolutePath());
                return new ReplyValues("Введите команду", vac);
            case "Заявление на отпуск без сохранения зп":
                File vac_no = fileStorage.getFile(TemplateTypes.VACATION_NO);
                return new ReplyValues("Введите команду", vac_no);
            case "Заявление на увольнение":
                File discharge = fileStorage.getFile(TemplateTypes.DISCHARGE);
                return new ReplyValues("Введите команду", discharge);
            case "Запросить обходной лист":
                File dischargeList = fileStorage.getFile(TemplateTypes.DISCHARGE_LIST);
                return new ReplyValues("Введите команду", dischargeList);
            default:
                keyboard.createUserKeyboard();
                return new ReplyValues("Введите команду");
        }
    }
}
