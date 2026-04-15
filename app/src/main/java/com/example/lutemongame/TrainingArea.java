package com.example.lutemongame;

public class TrainingArea extends Storage{


        private static TrainingArea trainingarea = null;

        private TrainingArea(){
            this.name = "TrainingArea";
        }

        public static TrainingArea getInstance() {
            if (trainingarea == null) {
                trainingarea = new TrainingArea();
            }
            return trainingarea;
        }


    public void train(Lutemon lutemon){
        lutemon.setExperience(lutemon.getExperience() + 1);

    }
}
