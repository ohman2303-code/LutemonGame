package com.example.lutemongame.colors;

import com.example.lutemongame.abstractclass.Lutemon;
import com.example.lutemongame.R;

public class Orange extends Lutemon {
    public Orange(String name) {
        super(name, R.string.color_orange, 10, 6, 0, 8, 8, idCounter++, R.drawable.lutemon_orange);
        this.name = name;
    }
}
