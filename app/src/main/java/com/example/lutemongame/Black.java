package com.example.lutemongame;

public class Black extends Lutemon{
    public Black(String name, String color, int attackPower, int defensePower, int experience, int health, int maxHealth, int id, int image) {
        super(name, color, attackPower, defensePower, experience, health, maxHealth, id, image);
        this.attackPower = 9;
        this.defensePower = 0;
        image = R.drawable.lutemon_black;
    }
}
