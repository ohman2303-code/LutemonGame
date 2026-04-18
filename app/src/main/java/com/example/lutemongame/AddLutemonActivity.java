package com.example.lutemongame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    ImageView lutemonImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_lutemon);
        
        lutemonImage = findViewById(R.id.chooseLutemonImage);
        lutemonImage.setVisibility(View.GONE);

        //Adding lutemons
        lutemonNameInput = findViewById(R.id.LutemonNameText);
        RadioGroup colorGroup = findViewById(R.id.LutemonRadioGroup);
        Button addButton = findViewById(R.id.AddLutemonButton);

        colorGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.WhiteButton) {
                    lutemonImage.setImageResource(R.drawable.lutemon_white);
                } else if (checkedId == R.id.BlackButton) {
                    lutemonImage.setImageResource(R.drawable.lutemon_black);
                } else if (checkedId == R.id.GreenButton) {
                    lutemonImage.setImageResource(R.drawable.lutemon_green);
                } else if (checkedId == R.id.TurqoiseButton) {
                    lutemonImage.setImageResource(R.drawable.lutemon_turqoise);
                } else if (checkedId == R.id.OrangeButton) {
                    lutemonImage.setImageResource(R.drawable.lutemon_orange);
                }
                lutemonImage.setVisibility(View.VISIBLE);
            }
        });

        //Setting listener
        addButton.setOnClickListener(view -> {

                    String lutemonName = lutemonNameInput.getText().toString();

                    int selectedId = colorGroup.getCheckedRadioButtonId();
                    RadioButton selectedButton = findViewById(selectedId);
                    String color = selectedButton.getText().toString();

                    Lutemon newLutemon = null;

                    //create Lutemon based on color

                    switch(color){
                        case "Valkoinen":
                            newLutemon = new White(lutemonName);
                            break;
                        case "Musta":
                            newLutemon = new Black(lutemonName);
                            break;
                        case "Vihreä":
                            newLutemon = new Green(lutemonName);
                            break;
                        case "Turkoosi":
                            newLutemon = new Turquoise(lutemonName);
                            break;
                        case "Oranssi":
                            newLutemon = new Orange(lutemonName);
                            break;
                    }

                    if(newLutemon != null){
                        Home.getInstance().addLutemon(newLutemon);

                        lutemonNameInput.setText("");

                    }
                });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void switchToMain(View view){
        finish();
    }
}
