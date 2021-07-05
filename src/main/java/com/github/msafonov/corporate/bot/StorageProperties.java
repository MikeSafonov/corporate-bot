package com.github.msafonov.corporate.bot;

public class StorageProperties {
    private String vacationNo;
    private String vacationWith;
    private String discharge;
    private String dischargeList;

    public StorageProperties(String vacationNo, String vacationWith, String discharge, String dischargeList) {
        this.vacationNo = vacationNo;
        this.vacationWith = vacationWith;
        this.discharge = discharge;
        this.dischargeList = dischargeList;
    }

    public String getVacationNo() {
        return vacationNo;
    }

    public String getVacationWith() {
        return vacationWith;
    }

    public String getDischarge() {
        return discharge;
    }

    public String getDischargeList() {
        return dischargeList;
    }
}
