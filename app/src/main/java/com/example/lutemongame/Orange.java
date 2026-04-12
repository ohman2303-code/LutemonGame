package com.example.lutemongame;

public class Orange extends Lutemon{
    public Orange(String name, String color, int attackPower, int defensePower, int experience, int health, int maxHealth, int id, int image) {
        super(name, color, attackPower, defensePower, experience, health, maxHealth, id, image);
        this.attackPower = 8;
        this.defensePower = 1;
    }
}
