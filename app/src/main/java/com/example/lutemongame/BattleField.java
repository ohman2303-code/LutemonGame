package com.example.lutemongame;

public class BattleField extends Storage{

    private static BattleField battleField = null;

    private BattleField(){
        this.name = "BattleField";
    }

    public static BattleField getInstance() {
        if (battleField == null) {
            battleField = new BattleField();
        }
        return battleField;
    }
}
