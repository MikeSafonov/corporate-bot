package com.github.msafonov.corporate.bot.Properties;

public class BotProperties {
    private String token;
    private String botName;


    public BotProperties(String token, String botName) {
        this.token = token;
        this.botName = botName;
    }

    public String getToken() {
        return token;
    }

    public String getBotName() {
        return botName;
    }

}
