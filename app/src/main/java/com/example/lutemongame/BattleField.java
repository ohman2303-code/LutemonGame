package com.example.lutemongame;

import android.content.Context;

public class BattleField extends Storage{

    //create singleton

    public static BattleField battleField = null;

    private BattleField(){
        this.name = "BattleField";
    }

    public static BattleField getInstance() {
        if (battleField == null) {
            battleField = new BattleField();
        }
        return battleField;
    }

    public String fight(Context context, Lutemon A, Lutemon B) {
        StringBuilder sb = new StringBuilder();

        while (A.getHealth() > 0 && B.getHealth() > 0) {

            //fightlog

            sb.append(context.getString(R.string.battle_stats, 1, A.getName(), A.getAttackPower(), A.getHealth(), A.getDefensePower(), A.getExperience()));
            sb.append(context.getString(R.string.battle_stats, 2, B.getName(), B.getAttackPower(), B.getHealth(), B.getDefensePower(), B.getExperience()));



            sb.append(context.getString(R.string.battle_attacks, A.getName(), B.getName()));
            B.defense(A);

            if (B.getHealth() > 0) {
                sb.append(context.getString(R.string.battle_escapes, B.getName()));

                //Change the roles of A and B

                Lutemon temp = A;
                A = B;
                B = temp;
            } else {

                sb.append(context.getString(R.string.battle_killed, B.getName()));
                sb.append(context.getString(R.string.battle_over));

                A.setExperience(A.getExperience() + 1);

                this.lutemons.remove(B.getId());
                break;
        }
    }
        return sb.toString();} // AI HELP
}
