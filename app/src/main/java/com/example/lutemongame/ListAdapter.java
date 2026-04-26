package com.example.lutemongame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lutemongame.abstractclass.Lutemon;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<LutemonsViewHolder>{

    private ArrayList<Lutemon> lutemons;
    private Context context;

    public ListAdapter(Context context, ArrayList<Lutemon> lutemons) {
        this.context = context;
        this.lutemons = lutemons;
    }

    @NonNull
    @Override
    public LutemonsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lutemon_view_holder, parent, false);
        return new LutemonsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonsViewHolder holder, int position) {
        Lutemon lutemon = lutemons.get(position);

        String lutemonName = lutemon.getName() + " " + context.getString(R.string.lutemon_color, lutemon.getColorString(context));

        //Set name and stats
        holder.name.setText(lutemonName);

        holder.attack.setText(context.getString(R.string.attack, lutemon.getAttackPower()));
        holder.defence.setText(context.getString(R.string.defence, lutemon.getDefensePower()));
        holder.health.setText(context.getString(R.string.hitpoints, lutemon.getHealth(), lutemon.getMaxHealth()));
        holder.experience.setText(context.getString(R.string.experience, lutemon.getExperience()));


        //Set picture
        holder.lutemonImage.setImageResource(lutemon.getImage());

    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }
}
