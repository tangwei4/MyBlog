package com.suye.personalblog.model;

import java.io.Serializable;
import java.util.Date;

public class LogMessage implements Serializable {
    private String date;
    private String action;

    protected LogMessage(){}

    public LogMessage(String date,String action){
        this.date=date;
        this.action=action;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
