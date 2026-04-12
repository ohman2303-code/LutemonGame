package com.example.lutemongame;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.lutemongame.fragments.BattleFieldFragment;
import com.example.lutemongame.fragments.HomeFragment;
import com.example.lutemongame.fragments.TrainFragment;

public class MoveLutemonsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_move_lutemons);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button homeFragmentButton = findViewById(R.id.HomeFragmentButton);
        Button trainFragmentButton = findViewById(R.id.TrainFragmentButton);
        Button fightFragmentButton = findViewById(R.id.FightFragmentButton);

        homeFragmentButton.setOnClickListener(listener);
        trainFragmentButton.setOnClickListener(listener);
        fightFragmentButton.setOnClickListener(listener);
    }
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Fragment fragment;
            if (v.getId() == R.id.HomeFragmentButton) {
                fragment = new HomeFragment();
            } else if (v.getId() == R.id.TrainFragmentButton) {
                fragment = new TrainFragment();
            } else {
                fragment = new BattleFieldFragment();
            }
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame, fragment)
                    .commit();
        }
    };
    public void switchToMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}