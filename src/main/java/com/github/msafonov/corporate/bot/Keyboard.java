package com.github.msafonov.corporate.bot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Keyboard {
    private final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    private final List<KeyboardRow> keyboard = new ArrayList<>();
    private final KeyboardRow firstRow = new KeyboardRow();
    private final KeyboardRow secondRow = new KeyboardRow();
    private final KeyboardRow thirdRow = new KeyboardRow();
    private final KeyboardRow fourthRow = new KeyboardRow();
    private final KeyboardRow fifthRow = new KeyboardRow();

    public ReplyKeyboardMarkup getReplyKeyboardMarkup() {
        return replyKeyboardMarkup;
    }

    public Keyboard() {
    }

    public void clearKeyboard() {
        keyboard.clear();
        firstRow.clear();
        secondRow.clear();
        thirdRow.clear();
        fourthRow.clear();
        fifthRow.clear();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
    }

    public void createUserKeyboard() {
        clearKeyboard();

        KeyboardButton kbRequest = new KeyboardButton("Высказать предложение");
        KeyboardButton kbComplain = new KeyboardButton("Высказать жалобу");
        KeyboardButton kbThanks = new KeyboardButton("Сказать спасибо коллегам другого подразделения");
        KeyboardButton kbTemplates = new KeyboardButton("Запросить кадровые шаблоны");
        KeyboardButton kbWriteMessage = new KeyboardButton("Не нашли нужную тему? Напишите сообщение");

        firstRow.add(kbRequest);
        firstRow.add(kbComplain);
        secondRow.add(kbThanks);
        secondRow.add(kbTemplates);
        thirdRow.add(kbWriteMessage);

        keyboard.addAll(Arrays.asList(firstRow, secondRow, thirdRow));
        replyKeyboardMarkup.setKeyboard(keyboard);

    }

    public void createTemplatesKeyboard() {
        clearKeyboard();

        KeyboardButton vacation = new KeyboardButton("Заявление на ежегодный отпуск");
        KeyboardButton vacation_no = new KeyboardButton("Заявление на отпуск без сохранения зп");
        KeyboardButton discharge = new KeyboardButton("Заявление на увольнение");
        KeyboardButton dischargeList = new KeyboardButton("Запросить обходной лист");
        KeyboardButton back = new KeyboardButton("Назад");
        firstRow.add(vacation);
        secondRow.add(vacation_no);
        thirdRow.add(discharge);
        fourthRow.add(dischargeList);
        fifthRow.add(back);

        keyboard.addAll(Arrays.asList(firstRow, secondRow, thirdRow, fourthRow, fifthRow));
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    public void createAdminKeyboard() {
        clearKeyboard();

        KeyboardButton statistics = new KeyboardButton("Статистика");
        KeyboardButton mailing = new KeyboardButton("Рассылка");
        KeyboardButton newWorker = new KeyboardButton("Новый сотрудник");
        firstRow.add(statistics);
        firstRow.add(mailing);
        secondRow.add(newWorker);
        keyboard.add(firstRow);
        keyboard.add(secondRow);
        replyKeyboardMarkup.setKeyboard(keyboard);

    }

    public void createEmptyKeyboard() {
        clearKeyboard();
        KeyboardButton newWorker = new KeyboardButton("пустая кнопка");
        firstRow.add(newWorker);
        keyboard.add(firstRow);
        replyKeyboardMarkup.setKeyboard(keyboard);

    }
}
