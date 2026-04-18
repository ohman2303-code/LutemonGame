package com.example.lutemongame;

public class Orange extends Lutemon{
    public Orange(String name) {
        super(name, "Oranssi", 10, 6, 0, 8, 8, idCounter++, R.drawable.lutemon_orange);
        this.name = name;
    }
}
