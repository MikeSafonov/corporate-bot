package com.github.msafonov.corporate.bot.Properties;

import java.util.Arrays;
import java.util.List;

public class AdminsProperties {
    private List<String> chatId;

    public List<String> getChatId() {
        return chatId;
    }

    public AdminsProperties(String chatId) {
        this.chatId= Arrays.asList(chatId.split(","));
    }
}
