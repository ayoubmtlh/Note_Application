package com.mtlh.noteapplication.notes_manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.mtlh.noteapplication.Constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;


public class NotesManagerSqLite extends SQLiteOpenHelper implements INotesManager {
    private NotesManagerSqLite(@Nullable Context context) {
        super(context, Constants.NOTES_TABLE + ".db", null, 1);
    }

    static private INotesManager notesManager;

    static public INotesManager getInstance(Context context) {
        if (notesManager == null) {
            notesManager = new NotesManagerSqLite(context);
        }

        return notesManager;
    }

    private List<Note> notes = new ArrayList<Note>();
    private Function<List<Note>, Void> onChange;

    @Override
    public List<Note> getNotes() {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM Notes;", null);
        notes = new ArrayList<Note>();
        while (cursor.moveToNext()) {
            Note note = new Note();
            note.setId(cursor.getInt(0));
            note.setText(cursor.getString(1));
            note.setDate(new Date(cursor.getString(2)));
            notes.add(note);
        }
        return notes;
    }

    @Override
    public long addNote(Note note) {
        ContentValues values = new ContentValues();
        values.put(Constants.NOTES_TEXT_COLUMN, note.getText());
        values.put(Constants.NOTES_DATE_COLUMN, note.getDate().toString());
        long id = getWritableDatabase().insert(Constants.NOTES_TABLE, null, values);
        onChange.apply(getNotes());
        return id;
    }

    @Override
    public void deleteNote(int id) {
        getWritableDatabase().delete(Constants.NOTES_TABLE, "id == ?", new String[]{"" + id});
        onChange.apply(getNotes());
    }

    @Override
    public void updateNote(Note note) {
        ContentValues values = new ContentValues();
        values.put(Constants.NOTES_ID_COLUMN, note.getId());
        values.put(Constants.NOTES_TEXT_COLUMN, note.getText());
        values.put(Constants.NOTES_DATE_COLUMN, note.getDate().toString());
        getWritableDatabase().update(Constants.NOTES_TABLE, values, "id == ?", new String[]{"" + note.getId()});
        onChange.apply(getNotes());
    }

    @Override
    public void setOnChange(Function<List<Note>, Void> onChange) {
        this.onChange = onChange;
    }

    @Override
    public void clearDatabase() {
        notes.clear();
        getWritableDatabase().delete(Constants.NOTES_TABLE, "true", null);
        onChange.apply(getNotes());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.CREATE_DB_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Log.d(Constants.DB_MANAGER_TAG, "Upgrading");
    }
}
