package com.example.lutemongame;

import static com.example.lutemongame.BattleField.battleField;

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
                String battle = battleField.fight(fighter1, fighter2);

                battleLog.setText(battle);

                runBattleAnimation(fighter1, fighter2);

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



    private void runBattleAnimation(Lutemon f1, Lutemon f2) {
        Handler handler = new Handler();
        int delay = 1000; // one second for each turn

        // Set pictures
        ivFighter1.setImageResource(f1.getImage());
        ivFighter2.setImageResource(f2.getImage());

        // Animation
        handler.postDelayed(() -> {

            // Make explosion
            showVisualEffect(ivFighter2, R.drawable.explosion);

        }, delay);

        handler.postDelayed(() -> {
            if (f2.getHealth() <= 0) {
                ivFighter2.setImageResource(f2.getImage());
                showVisualEffect(ivFighter2, R.drawable.cross_mark); // Set cross mark
                ivFighter2.setAlpha(0.5f);
            }
        }, delay * 2);
    }
    //AI HELP

    private void showVisualEffect(ImageView target, int drawableResId) {
        ImageView ivEffect = findViewById(R.id.ivEffect);

        if (ivEffect != null) {
            ivEffect.setImageResource(drawableResId);

            ivEffect.setX(target.getX());
            ivEffect.setY(target.getY());

            ivEffect.setVisibility(View.VISIBLE);
        }

        new android.os.Handler().postDelayed(() -> {
            ivEffect.setVisibility(View.INVISIBLE);
        }, 500);
    }

    private void playExplosion(ImageView target) {
        ImageView ivEffect = findViewById(R.id.ivEffect);
        ivEffect.setImageResource(R.drawable.explosion); // Explosion

        // Set the place of the effect
        ivEffect.setX(target.getX());
        ivEffect.setY(target.getY());

        ivEffect.setVisibility(View.VISIBLE);

        // Hide effect with delay
        new android.os.Handler().postDelayed(() -> {
            ivEffect.setVisibility(View.INVISIBLE);
        }, 500);
    }
    //AI HELP
}