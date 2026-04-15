package com.example.lutemongame;

public class Turquoise extends Lutemon{
    public Turquoise(String name, String color, int attackPower, int defensePower, int experience, int health, int maxHealth, int id, int image) {
        super(name, color, attackPower, defensePower, experience, health, maxHealth, id, image);
        this.attackPower = 7;
        this.defensePower = 2;
        image = R.drawable.lutemon_turqoise;
    }
}
