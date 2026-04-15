package com.example.lutemongame;

public class Home extends Storage{
    private static Home home = null;

    private Home(){
        this.name = "Home";
    }

    public static Home getInstance() {
        if (home == null) {
            home = new Home();
        }
        return home;
    }

    public void addLutemon(Lutemon l) {
        l.setHealth(l.getMaxHealth());
        super.addLutemon(l);
    }

    public void createLutemon(String name, String type){


    }

}
