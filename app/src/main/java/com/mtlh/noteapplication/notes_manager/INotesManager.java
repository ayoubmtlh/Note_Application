package com.mtlh.noteapplication.notes_manager;

import java.util.List;
import java.util.function.Function;

public interface INotesManager {
    List<Note> getNotes();
    long addNote(Note note);
    void deleteNote(int id);
    void updateNote(Note note);
    void setOnChange(Function<List<Note>, Void> onChange);
    void clearDatabase();
}
