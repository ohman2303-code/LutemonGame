package com.example.lutemongame;

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
    public void saveLutemons(){

    }
    public void loadLutemons(){

    }


    public void moveLutemon(Lutemon lutemon, Storage target) {
        // delete from this storage
        this.lutemons.remove(lutemon.getId());

        // add to target storage
        target.addLutemon(lutemon);
    }

}
