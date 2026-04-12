package com.example.lutemongame;

public class Green extends Lutemon{
    public Green(String name, String color, int attackPower, int defensePower, int experience, int health, int maxHealth, int id, int image) {
        super(name, color, attackPower, defensePower, experience, health, maxHealth, id, image);
        this.attackPower = 6;
        this.defensePower = 3;
    }
}
