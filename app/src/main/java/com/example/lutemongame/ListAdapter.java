package com.example.lutemongame;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class ListAdapter {
    TextView NoteIdText, NoteTimeAndDateText, NoteTitleText, NoteContentText;


    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);
        NoteIdText = itemView.findViewById(R.id.NoteIdText);
        NoteTimeAndDateText = itemView.findViewById(R.id.NoteTimeAndDateText);
        NoteTitleText = itemView.findViewById(R.id.NoteTitleText);
        NoteContentText = itemView.findViewById(R.id.NoteContentText);
    }
}
