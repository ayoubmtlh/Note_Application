package com.mtlh.noteapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mtlh.noteapplication.notes_manager.INotesManager;
import com.mtlh.noteapplication.notes_manager.NotesManagerSqLite;

public class NotesListAdapter extends BaseAdapter {
    private INotesManager notesManager;
    private Context context;

    public NotesListAdapter(Context context) {
        this.context = context;
        this.notesManager = NotesManagerSqLite.getInstance(context);
    }

    @Override
    public int getCount() {
        return notesManager.getNotes().size();
    }

    @Override
    public Object getItem(int position) {
        return notesManager.getNotes().get(position);
    }

    @Override
    public long getItemId(int position) {
        return notesManager.getNotes().get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_element, parent, false);
        TextView title = (TextView) view.findViewById(R.id.note_title);
        TextView date = (TextView) view.findViewById(R.id.note_date);

        title.setText(notesManager.getNotes().get(position).getText());
        date.setText(notesManager.getNotes().get(position).getDate().toString());

        view.setId(notesManager.getNotes().get(position).getId());
     //   Log.d("NOTES_LIST", "" + view.getId());

        return view;
    }
}
