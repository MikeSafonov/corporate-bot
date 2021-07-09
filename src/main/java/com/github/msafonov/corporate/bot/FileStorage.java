package com.github.msafonov.corporate.bot;

import com.github.msafonov.corporate.bot.Property.StorageProperties;

import java.io.File;
import java.io.IOException;

public class FileStorage {
    private StorageProperties storageProperties;

    public FileStorage(StorageProperties storageProperties) {
        this.storageProperties = storageProperties;
    }

    public File getFile(TemplateTypes type) throws IOException {

        switch (type) {
            case DISCHARGE:
                return new File(storageProperties.getDischarge());
            case DISCHARGE_LIST:
                return new File(storageProperties.getDischargeList());
            case VACATION_NO:
                return new File(storageProperties.getVacationNo());
            case VACATION_WITH:
                return new File(storageProperties.getVacationWith());
        }
        return null;
    }
}
