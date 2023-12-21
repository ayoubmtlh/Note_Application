package com.mtlh.noteapplication.notes_manager;

import java.util.Comparator;
import java.util.Date;

public class Note {
    private int id;
    private String text;
    private Date date;

    public Note() {

    }

    public Note(String text, Date date) {
        this.text = text;
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
