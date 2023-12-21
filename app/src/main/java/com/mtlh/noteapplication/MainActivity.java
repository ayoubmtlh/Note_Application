package com.mtlh.noteapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText usernameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameText = (EditText) findViewById(R.id.usernameID);
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences preferences = getSharedPreferences(Constants.SHARED_PREF_KEY,Context.MODE_PRIVATE);
        String username = preferences.getString(Constants.USERNAME_KEY, null);
        if (username != null) {
            goToNotesList(username);
        }
    }

    public void saveUsername(View view) {
        SharedPreferences prefs = getSharedPreferences(Constants.SHARED_PREF_KEY,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(Constants.USERNAME_KEY, usernameText.getText().toString());
        editor.apply();

        goToNotesList(usernameText.getText().toString());
    }

    void goToNotesList(String username) {
        Intent intent = new Intent(this, NotesList.class);
        intent.putExtra(Constants.USERNAME_KEY, username);

        startActivity(intent);
    }
}