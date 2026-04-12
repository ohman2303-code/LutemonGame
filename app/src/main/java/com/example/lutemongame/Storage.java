package com.example.lutemongame;

public abstract class Storage {
    protected HashMap<Integer, Lutemon> lutemons = new HashMap<>();

    public void moveLutemon(Lutemon lutemon, Storage target) {
        // 1. Poistetaan tästä varastosta
        this.lutemons.remove(lutemon.getId());

        // 2. Lisätään kohde-varastoon käyttämällä kohteen addLutemon-metodia
        target.addLutemon(lutemon);
    }

    public void addLutemon(Lutemon l) {
        lutemons.put(l.getId(), l);
    }
}
