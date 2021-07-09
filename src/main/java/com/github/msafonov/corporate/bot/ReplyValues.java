package com.github.msafonov.corporate.bot;

import java.io.File;

public class ReplyValues {
    private String message;
    private File file;

    public ReplyValues(String message) {
        this.message = message;
    }

    public ReplyValues(String message, File file) {
        this.message = message;
        this.file = file;
    }

    public String getMessage() {
        return message;
    }

    public File getFile() {
        return file;
    }
}
