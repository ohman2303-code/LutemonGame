package com.example.lutemongame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lutemongame.BattleField;
import com.example.lutemongame.Home;
import com.example.lutemongame.R;
import com.example.lutemongame.TrainingArea;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void switchToAddLutemonActivity(View view){
        Intent intent = new Intent(this, AddLutemonActivity.class);
        startActivity(intent);
    }

    public void switchToListLutemonsActivity(View view){
        Intent intent = new Intent(this, ListLutemonsActivity.class);
        startActivity(intent);
    }

    public void switchToMoveLutemonsActivity(View view){
        Intent intent = new Intent(this, MoveLutemonsActivity.class);
        startActivity(intent);
    }
    public void saveAllLutemons(View view) {
        Home.getInstance().saveLutemons(this);
        TrainingArea.getInstance().saveLutemons(this);
        BattleField.getInstance().saveLutemons(this);
    }
    public void loadAllLutemons(View view) {
        Home.getInstance().loadLutemons(this);
        TrainingArea.getInstance().loadLutemons(this);
        BattleField.getInstance().loadLutemons(this);
    }
}
