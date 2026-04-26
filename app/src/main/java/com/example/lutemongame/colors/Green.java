package com.example.lutemongame.colors;

import com.example.lutemongame.abstractclass.Lutemon;
import com.example.lutemongame.R;

public class Green extends Lutemon {
    public Green(String name) {
        super(name, "Vihreä", 6, 3, 0, 8, 8, idCounter++, R.drawable.lutemon_green);
        this.name = name;
    }
}
