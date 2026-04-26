package com.example.lutemongame;

import com.example.lutemongame.abstractclass.Lutemon;
import com.example.lutemongame.abstractclass.Storage;

public class Home extends Storage {

    //create singleton
    private static Home home = null;

    private Home(){
        this.name = "Home";
    }

    public static Home getInstance() {
        if (home == null) {
            home = new Home();
        }
        return home;
    }

    public void healLutemon(Lutemon lutemon){
        lutemon.setHealth(lutemon.getMaxHealth());
    }

}
