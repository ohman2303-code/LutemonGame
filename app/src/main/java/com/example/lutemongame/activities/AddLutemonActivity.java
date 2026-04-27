package com.example.lutemongame.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lutemongame.Home;
import com.example.lutemongame.LocaleHelper;
import com.example.lutemongame.abstractclass.Lutemon;
import com.example.lutemongame.R;
import com.example.lutemongame.colors.Black;
import com.example.lutemongame.colors.Green;
import com.example.lutemongame.colors.Orange;
import com.example.lutemongame.colors.Turquoise;
import com.example.lutemongame.colors.White;

public class AddLutemonActivity extends AppCompatActivity {

    EditText lutemonNameInput;
    ImageView lutemonImage;
    @Override
    protected void attachBaseContext(Context newBase) {
        String lang = LocaleHelper.getLanguage(newBase);
        super.attachBaseContext(LocaleHelper.updateResources(newBase, lang));
    } //AI HELP

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

            if (lutemonName.isEmpty()) {
                Toast.makeText(this, getString(R.string.provide_name), Toast.LENGTH_SHORT).show();
                return;
            }
            if (selectedId == -1){
                Toast.makeText(this, getString(R.string.choose_color), Toast.LENGTH_SHORT).show();
                return;
            }
            Lutemon newLutemon = null;

            if (selectedId == R.id.WhiteButton) {
                newLutemon = new White(lutemonName);
            } else if (selectedId == R.id.BlackButton) {
                newLutemon = new Black(lutemonName);
            } else if (selectedId == R.id.GreenButton) {
                newLutemon = new Green(lutemonName);
            } else if (selectedId == R.id.TurqoiseButton) {
                newLutemon = new Turquoise(lutemonName);
            } else if (selectedId == R.id.OrangeButton) {
                newLutemon = new Orange(lutemonName);
            }

            if(newLutemon != null){
                Home.getInstance().addLutemon(newLutemon);
                lutemonNameInput.setText("");
                colorGroup.clearCheck();
                lutemonImage.setVisibility(View.GONE);
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

