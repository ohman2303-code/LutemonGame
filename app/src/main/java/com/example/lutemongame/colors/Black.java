package com.example.lutemongame.colors;

import com.example.lutemongame.abstractclass.Lutemon;
import com.example.lutemongame.R;

public class Black extends Lutemon {
    public Black(String name) {
        super(name, R.string.color_black, 12, 9, 0, 20, 20, idCounter++, R.drawable.lutemon_black);
        this.name = name;
    }
}
