package com.mtlh.noteapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.mtlh.noteapplication.notes_manager.INotesManager;
import com.mtlh.noteapplication.notes_manager.NotesManagerSqLite;

public class NotesList extends AppCompatActivity {

    INotesManager notesManager = NotesManagerSqLite.getInstance(this);
    NotesListAdapter notesListAdapter = new NotesListAdapter(this);
    ListView notesListView;
    TextView greetingsTextView;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);
        greetingsTextView = (TextView) findViewById(R.id.greetings);
        Intent thisIntent = getIntent();
        username = thisIntent.getStringExtra(Constants.USERNAME_KEY);
        greetingsTextView.setText("Hello " + username + "!");

        notesListView = (ListView) findViewById(R.id.notesListView);

        notesListView.setAdapter(notesListAdapter);

        notesManager.setOnChange(notes -> {
            notesListAdapter.notifyDataSetChanged();
            return null;
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void openNote(View view) {
        String text = ((TextView) view.findViewById(R.id.note_title)).getText().toString();
        String date = ((TextView) view.findViewById(R.id.note_date)).getText().toString();
        int id = view.getId();
        Log.d("LIST_ELEMENT", "title: "+text+"; date: "+date);
        Intent intent = new Intent(this, NewNote.class);
        intent.putExtra(Constants.NOTE_TEXT, text);
        intent.putExtra(Constants.NOTE_DATE, date);
        intent.putExtra(Constants.NOTE_ID, id);
        startActivity(intent);
    }

    public void newNote(View view) {
        Intent intent = new Intent(this, NewNote.class);
        startActivity(intent);
    }

    public void clearData(View view) {
        SharedPreferences preferences = getSharedPreferences(Constants.SHARED_PREF_KEY,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(Constants.USERNAME_KEY);
        editor.apply();
        notesManager.clearDatabase();
        finish();
    }
}