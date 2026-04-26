package com.example.lutemongame;

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

    public String fight(Lutemon A, Lutemon B) {
        StringBuilder sb = new StringBuilder();

        while (A.getHealth() > 0 && B.getHealth() > 0) {

            //fightlog

            sb.append("1: ").append(A.getName()).append("; att: ").append(A.getAttackPower()).append("; health: ").append(A.getHealth()).append("; def: ").append(A.getDefensePower()).append("; exp: ").append(A.getExperience()).append("\n");
            sb.append("2: ").append(B.getName()).append("; att: ").append(B.getAttackPower()).append("; health: ").append(B.getHealth()).append("; def: ").append(B.getDefensePower()).append("; exp: ").append(B.getExperience()).append("\n");

            sb.append(A.getName()).append(" attacks ").append(B.getName()).append("\n");
            B.defense(A);

            if (B.getHealth() > 0) {
                sb.append(B.getName()).append(" manages to escape death.\n\n");

                //Change the roles of A and B

                Lutemon temp = A;
                A = B;
                B = temp;
            } else {

                sb.append(B.getName()).append(" gets killed.\n");
                sb.append("The battle is ovahh!\n");

                A.setExperience(A.getExperience() + 1);

                this.lutemons.remove(B.getId());
                break;
        }
    }
        return sb.toString();} // AI HELP
}
