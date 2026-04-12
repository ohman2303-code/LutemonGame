package com.example.lutemongame;

public class White extends Lutemon{
    public White(String name, String color, int attackPower, int defensePower, int experience, int health, int maxHealth, int id, int image) {
        super(name, color, attackPower, defensePower, experience, health, maxHealth, id, image);
        this.attackPower = 5;
        this.defensePower = 4;
    }
}
