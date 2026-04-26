package com.example.lutemongame.activites;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lutemongame.BattleField;
import com.example.lutemongame.abstractclass.Lutemon;
import com.example.lutemongame.R;
import java.util.ArrayList;

public class FightActivity extends AppCompatActivity {

    private ImageView ivFighter1, ivFighter2;
    private TextView battleLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fight);

        ivFighter1 = findViewById(R.id.ivFighter1);
        ivFighter2 = findViewById(R.id.ivFighter2);
        battleLog = findViewById(R.id.FightLog);
        ScrollView scrollView = findViewById(R.id.ScrollViewFight);

        ArrayList<Integer> selectedIds = getIntent().getIntegerArrayListExtra("selectedLutemonIds");

        if (selectedIds != null && selectedIds.size() == 2) {
            BattleField battleField = BattleField.getInstance();

            Lutemon fighter1 = battleField.getLutemon(selectedIds.get(0));
            Lutemon fighter2 = battleField.getLutemon(selectedIds.get(1));

            if (fighter1 != null && fighter2 != null) {


                String battleResult = battleField.fight(this, fighter1, fighter2);

                battleLog.setText("");

                runBattleAnimation(fighter1, fighter2, battleResult);

                //ScrollView to bottom
                scrollView.post(() -> scrollView.fullScroll(View.FOCUS_DOWN));
            }
        } else {
            String mistake = "Virhe! Ei löytynyt lutemonia!";
            battleLog.setText(mistake);

        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    } // AI HELP

    public void returnToBattleFieldFragment(View view) {
        finish();
    }



    private void runBattleAnimation(Lutemon f1, Lutemon f2, String fullLog) {
        Handler handler = new Handler();

        // Set pictures
        ivFighter1.setImageResource(f1.getImage());
        ivFighter2.setImageResource(f2.getImage());


        //Split fight into lines
        String[] log = fullLog.split("\n");
        int delayPerLine = 800;

        for (int i = 0; i < log.length; i++) {
            final String line = log[i];
            final int index = i;


            // Animation
            handler.postDelayed(() -> {

                battleLog.append(line + "\n");

                ScrollView scrollView = findViewById(R.id.ScrollViewFight);
                if (scrollView != null) {
                    scrollView.post(() -> scrollView.fullScroll(View.FOCUS_DOWN));
                }

                // Make explosion
                if (line.contains(f1.getName())) {
                    showVisualEffect(ivFighter2, R.drawable.explosion);
                }else if (line.contains(f2.getName())) {
                    showVisualEffect(ivFighter1, R.drawable.explosion);

                }

                if (index == log.length - 1) {
                    if (f1.getHealth() <= 0) {
                        showVisualEffect(ivFighter1, R.drawable.cross_mark);
                        ivFighter1.setAlpha(0.5f);
                    } else if (f2.getHealth() <= 0) {
                        showVisualEffect(ivFighter2, R.drawable.cross_mark);
                        ivFighter2.setAlpha(0.5f);
                    }
                }

            }, (long) i * delayPerLine);

        }
    }


    private void showVisualEffect(ImageView target, int drawableResId) {
        ImageView ivEffect = findViewById(R.id.ivEffect);

        if (ivEffect != null) {
            ivEffect.setImageResource(drawableResId);

            ivEffect.setX(target.getX());
            ivEffect.setY(target.getY());

            ivEffect.setVisibility(View.VISIBLE);

            new android.os.Handler().postDelayed(() -> {
                ivEffect.setVisibility(View.INVISIBLE);


            }, 500);
        }
    }
}