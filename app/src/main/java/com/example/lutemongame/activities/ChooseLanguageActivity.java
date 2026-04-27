package com.example.lutemongame.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lutemongame.LocaleHelper;
import com.example.lutemongame.R;

public class ChooseLanguageActivity extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        String lang = LocaleHelper.getLanguage(newBase);
        super.attachBaseContext(LocaleHelper.updateResources(newBase, lang));
    } //AI HELP

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_choose_language);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button finnishButton = findViewById(R.id.FinnishButton);
        Button englishButton = findViewById(R.id.EnglishButton);

        finnishButton.setOnClickListener(v -> {
            setAppLocale("fi");
        });

        englishButton.setOnClickListener(v -> {
            setAppLocale("en");
        });
    }
    private void setAppLocale(java.lang.String language) {
        LocaleHelper.setLocale(this, language);
        // Siirrytään MainActivityyn
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
} //AI HELP