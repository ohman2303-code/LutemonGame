package com.example.lutemongame;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LutemonsViewHolder extends RecyclerView.ViewHolder {
    ImageView lutemonImage;
    TextView name, stats;
    public LutemonsViewHolder(@NonNull View itemView) {
        super(itemView);
        lutemonImage = itemView.findViewById(R.id.imageViewLutemon);
        name = itemView.findViewById(R.id.lutemonName);
    }
}
