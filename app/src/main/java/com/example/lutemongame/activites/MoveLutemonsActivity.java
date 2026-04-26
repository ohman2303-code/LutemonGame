package com.example.lutemongame.activites;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.lutemongame.R;
import com.example.lutemongame.TabAdapter;
import com.example.lutemongame.fragments.BattleFieldFragment;
import com.example.lutemongame.fragments.HomeFragment;
import com.example.lutemongame.fragments.TrainFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MoveLutemonsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_move_lutemons);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager = findViewById(R.id.viewPager);

        TabAdapter adapter = new TabAdapter(this);
        viewPager.setAdapter(adapter);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                // Find the fragment based on tag and update its radio buttons
                Fragment fragment = getSupportFragmentManager().findFragmentByTag("f" + position);
                if (fragment != null) {
                    if (fragment instanceof HomeFragment) {
                        ((HomeFragment) fragment).makeRadioButtons(fragment.getView());
                    } else if (fragment instanceof TrainFragment) {
                        ((TrainFragment) fragment).makeRadioButtons(fragment.getView());
                    } else if (fragment instanceof BattleFieldFragment) {
                        ((BattleFieldFragment) fragment).makeRadioButtons(fragment.getView());
                    }
                }
            }
        }); // AI HELP

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Koti");
                    break;
                case 1:
                    tab.setText("Treeni");
                    break;
                case 2:
                    tab.setText("Taistelu");
                    break;
            }
        }).attach(); // AI HELP

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        }); // AI HELP

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                // Find the fragment based on tag and clear its radio buttons
                Fragment fragment = getSupportFragmentManager().findFragmentByTag("f" + position);
                if (fragment != null) {
                    View view = fragment.getView();
                    if (view != null) {
                        RadioGroup rg = null;
                        ImageView iv = null; // Hide the image

                        if (fragment instanceof HomeFragment) {
                            rg = view.findViewById(R.id.rgHomeLutemons);
                            iv = view.findViewById(R.id.ivHomeLutemonImage);
                        } else if (fragment instanceof TrainFragment) {
                            rg = view.findViewById(R.id.rgHomeLutemons);
                            iv = view.findViewById(R.id.ivTrainLutemonImage);
                        } else if (fragment instanceof BattleFieldFragment) {
                            rg = view.findViewById(R.id.rgBattleFieldLutemons);
                            iv = view.findViewById(R.id.ivBattleFieldLutemonImage);
                        }

                        if (rg != null) rg.clearCheck();
                        if (iv != null) iv.setVisibility(View.GONE);
                    }
                }
            }
        });
    } // AI HELP

    public void switchToMain(View view){
        finish();
    }
}
