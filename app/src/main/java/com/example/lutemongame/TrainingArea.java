package com.example.lutemongame;

public class TrainingArea extends Storage{

    //create singleton

    private static TrainingArea trainingarea = null;

    public void train(Lutemon lutemon){
        lutemon.setExperience(lutemon.getExperience() + 1);

    }

    private TrainingArea(){
        this.name = "TrainingArea";
    }

    public static TrainingArea getInstance() {
        if (trainingarea == null) {
            trainingarea = new TrainingArea();
        }
        return trainingarea;
    }

}

