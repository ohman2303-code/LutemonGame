package com.example.lutemongame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddLutemonActivity extends AppCompatActivity {

    EditText lutemonNameInput;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_lutemon);

        //Adding lutemons
        lutemonNameInput = findViewById(R.id.LutemonNameText);
        RadioGroup colorGroup = findViewById(R.id.LutemonRadioGroup);
        Button addButton = findViewById(R.id.AddLutemonButton);

        //Setting listener
        addButton.setOnClickListener(view -> {

                    String lutemonName = lutemonNameInput.getText().toString();

                    int selectedId = colorGroup.getCheckedRadioButtonId();
                    RadioButton selectedButton = findViewById(selectedId);
                    String color = selectedButton.getText().toString();
                });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void switchToMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}