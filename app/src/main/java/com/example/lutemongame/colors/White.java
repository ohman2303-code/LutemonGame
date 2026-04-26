package com.example.lutemongame.colors;

import com.example.lutemongame.abstractclass.Lutemon;
import com.example.lutemongame.R;

public class White extends Lutemon {
    public White(String name) {
        super(name, "Valkoinen", 15, 8, 0, 15, 15, idCounter++, R.drawable.lutemon_white);
        this.name = name;
    }
}
