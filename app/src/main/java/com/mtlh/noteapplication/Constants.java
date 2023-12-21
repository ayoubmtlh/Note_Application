package com.mtlh.noteapplication;

public class Constants {
    public static final String NOTE_TEXT = "NOTE_TEXT";
    public static final String NOTE_DATE = "NOTE_DATE";
    public static final String NOTE_ID = "NOTE_ID";
    public static final String USERNAME_KEY = "USERNAME";
    public static final String SHARED_PREF_KEY = "SHARED_PREF_KEY";
    public static final String DB_MANAGER_TAG = "DB_MANAGER_TAG";
    public static final String NOTES_TABLE = "Notes";
    public static final String NOTES_TEXT_COLUMN = "text";
    public static final String NOTES_DATE_COLUMN = "date";
    public static final String NOTES_ID_COLUMN = "id";
    public static final String CREATE_DB_QUERY = "CREATE TABLE Notes (\n" +
            "  id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "  text VARCHAR(255) NOT NULL,\n" +
            "  date DATE NOT NULL\n" +
            ");";
}
