package com.example.lutemongame;

public class Orange extends Lutemon{
    public Orange(String name, String color, int attackPower, int defensePower, int experience, int health, int maxHealth, int id, int image) {
        super(name, color, attackPower, defensePower, experience, health, maxHealth, id, image);
        this.color = "Orange";
        this.attackPower = 10;
        this.defensePower = 6;
        this.experience = 10;
        this.maxHealth = 8;
        image = R.drawable.lutemon_orange;
    }
}
