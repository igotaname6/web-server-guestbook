package com.codecool.krk.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GuestbookEntry {

    private String message;
    private String userName;
    private Date dateTime;


    public GuestbookEntry(String message, String userName) {
        this.message = message;
        this.userName = userName;
        this.dateTime = Calendar.getInstance().getTime();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

}