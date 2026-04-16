package com.example.lutemongame;

import android.content.Context;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public abstract class Storage {
    protected String name;
    protected HashMap<Integer, Lutemon> lutemons = new HashMap<>();
    public void addLutemon(Lutemon l) {
        lutemons.put(l.getId(), l);
    }
    public Lutemon getLutemon(int id){
        return lutemons.get(id);
    }
    public HashMap<Integer, Lutemon> getLutemons() {
        return lutemons;
    }
    public void listLutemons(){

    }
    public void saveLutemons(Context context) {
        try {
        ObjectOutputStream out = new ObjectOutputStream(context.openFileOutput(name + ".data", Context.MODE_PRIVATE));
        out.writeObject(lutemons);
        out.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    public void loadLutemons(Context context) {
        try {
            ObjectInputStream in = new ObjectInputStream(context.openFileInput(name + ".data"));
            lutemons = (HashMap<Integer, Lutemon>) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
        }
    }


    public void moveLutemon(Lutemon lutemon, Storage target) {
        // delete from this storage
        this.lutemons.remove(lutemon.getId());

        // add to target storage
        target.addLutemon(lutemon);
    }

}
