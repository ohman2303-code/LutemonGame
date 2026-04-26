package com.example.lutemongame.colors;

import com.example.lutemongame.abstractclass.Lutemon;
import com.example.lutemongame.R;

public class Turquoise extends Lutemon {
    public Turquoise(String name) {
        super(name, R.string.color_turquoise, 4, 10, 0, 20, 20, idCounter++, R.drawable.lutemon_turqoise);
        this.name = name;
    }
}
