package com.example.lutemongame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    public static Home home = new Home();
    public static TrainingArea trainingArea = new TrainingArea();
    public static BattleField battleField = new BattleField();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button GoToAddLutemon = findViewById(R.id.GoToAddLutemon);
        Button GoToListLutemons = findViewById(R.id.GoToListLutemons);
        Button GoToMoveLutemon = findViewById(R.id.GoToMoveLutemon);
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
}