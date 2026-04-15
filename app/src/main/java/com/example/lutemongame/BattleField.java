package com.example.lutemongame;

public class BattleField extends Storage{

    private static BattleField battlefield = null;

    private BattleField(){
        this.name = "BattleField";
    }

    public static BattleField getInstance() {
        if (battlefield == null) {
            battlefield = new BattleField();
        }
        return battlefield;
    }

    public void fight(Lutemon A, Lutemon B) {

        while (A.getHealth() > 0 && B.getHealth() > 0) {
            //B.defense(A);
            if (B.getHealth() > 0) {
                //A.defense(B);
            }
        }
    }
}
