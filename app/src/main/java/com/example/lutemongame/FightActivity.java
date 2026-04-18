package com.example.lutemongame;

import static com.example.lutemongame.BattleField.battleField;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class FightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fight);

        TextView battleLog = findViewById(R.id.FightLog);
        ScrollView scrollView = findViewById(R.id.ScrollViewFight);

        ArrayList<Integer> selectedIds = getIntent().getIntegerArrayListExtra("selectedLutemonIds");

        if (selectedIds != null && selectedIds.size() == 2) {
            BattleField battleField = BattleField.getInstance();

            Lutemon fighter1 = battleField.getLutemon(selectedIds.get(0));
            Lutemon fighter2 = battleField.getLutemon(selectedIds.get(1));

        if(fighter1 != null && fighter2 != null){
            String battle = battleField.fight(fighter1, fighter2);

            battleLog.setText(battle);

            //ScrollView to bottom
            scrollView.post(() -> scrollView.fullScroll(View.FOCUS_DOWN));
            }
        }else{
            String mistake = "Virhe! Ei löytynyt lutemonia!";
            battleLog.setText(mistake);

        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void returnToBattleFieldFragment(View view){
        finish();
    }
}