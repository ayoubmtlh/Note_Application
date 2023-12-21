package com.mtlh.noteapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.mtlh.noteapplication.notes_manager.INotesManager;
import com.mtlh.noteapplication.notes_manager.Note;
import com.mtlh.noteapplication.notes_manager.NotesManagerSqLite;

import java.util.Date;

public class NewNote extends AppCompatActivity {

    INotesManager notesManager = NotesManagerSqLite.getInstance(this);
    EditText noteDetailsText;

    String text;
    String date;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        noteDetailsText = (EditText) findViewById(R.id.editNoteText);
        text = getIntent().getStringExtra(Constants.NOTE_TEXT);
        date = getIntent().getStringExtra(Constants.NOTE_DATE);
        id =  getIntent().getIntExtra(Constants.NOTE_ID, -1);

        if(id == -1) {
            findViewById(R.id.delete_button).setVisibility(View.GONE);
        } else {
            findViewById(R.id.delete_button).setVisibility(View.VISIBLE);
            noteDetailsText.setText(text);
        }
    }

    public void saveNote(View view) {
        Note note = new Note(noteDetailsText.getText().toString(), new Date());
        if(id == -1) {
            notesManager.addNote(note);
        } else {
            note.setId(id);
            notesManager.updateNote(note);
        }
        finish();
    }

    public void deleteNote(View view) {
        notesManager.deleteNote(id);
        finish();
    }
}