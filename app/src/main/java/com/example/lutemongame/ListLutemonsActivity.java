package com.example.lutemongame;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListLutemonsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_lutemons);

        recyclerView = findViewById(R.id.ListLutemonsRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Gather Lutemons from all the storages
        ArrayList<Lutemon> allLutemons = new ArrayList<>();
        allLutemons.addAll(Home.getInstance().getLutemons().values());
        allLutemons.addAll(TrainingArea.getInstance().getLutemons().values());
        allLutemons.addAll(BattleField.getInstance().getLutemons().values());

        adapter = new ListAdapter(this, allLutemons);
        recyclerView.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void returnBack(View view) {
        finish();
    }
}
