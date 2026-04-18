package com.example.lutemongame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

        String lutemonName = lutemon.getName() + " (" + lutemon.getColor() + ")";

        //Set name and stats
        holder.name.setText(lutemonName);

        String attack = "Hyökkäys: " + lutemon.getAttackPower();
        holder.attack.setText(attack);

        String defence = "Puolustus: " + lutemon.getDefensePower();
        holder.defence.setText(defence);

        String health = "Elämä: " + lutemon.getHealth();
        holder.health.setText(health);

        String exp = "Kokemus: " + lutemon.getExperience();
        holder.experience.setText(exp);

        //Set picture
        holder.lutemonImage.setImageResource(lutemon.getImage());

    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }
}
