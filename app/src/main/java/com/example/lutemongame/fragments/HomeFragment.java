package com.example.lutemongame.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.lutemongame.BattleField;
import com.example.lutemongame.Home;
import com.example.lutemongame.Lutemon;
import com.example.lutemongame.MainActivity;
import com.example.lutemongame.R;
import com.example.lutemongame.TrainingArea;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RadioGroup rgHomeLutemons = makeRadioButtons(view);
        Button healLutemonButton = view.findViewById(R.id.btnHealLutemon);
        RadioGroup rgChooseLutemonsFromHome = view.findViewById(R.id.rgChooseLutemonsFromHome);
        ImageView ivHomeLutemonImage = view.findViewById(R.id.ivHomeLutemonImage);
        Button moveLutemonsButton = view.findViewById(R.id.btnMoveLutemonsFromHome);
        Home home = Home.getInstance();


        
        // First we hide the picture of the chosen Lutemon
        ivHomeLutemonImage.setVisibility(View.GONE);

        // Update the photo when the Lutemon is chosen
        rgHomeLutemons.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId != -1) {
                Lutemon chosenLutemon = home.getLutemon(checkedId);
                if (chosenLutemon != null) {
                    ivHomeLutemonImage.setImageResource(chosenLutemon.getImage());
                    ivHomeLutemonImage.setVisibility(View.VISIBLE);
                }
            } else {
                ivHomeLutemonImage.setVisibility(View.GONE);
            }
        });

        //Here we heal the chosen Lutemon

        healLutemonButton.setOnClickListener(View -> {
            int selectedLutemonId = rgHomeLutemons.getCheckedRadioButtonId();
            if (selectedLutemonId != -1) {
                Lutemon chosenLutemonForHeal = home.getLutemon(selectedLutemonId);
                home.healLutemon(chosenLutemonForHeal);
                Toast.makeText(getContext(), chosenLutemonForHeal.getName() + " on parannettu!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getContext(), "Valitse ensin parannettava Lutemon!", Toast.LENGTH_SHORT).show();
            }
            makeRadioButtons(view);
        });


        //Here we move Lutemons to TrainingArea or BattleField (They are currently in Home)

        moveLutemonsButton.setOnClickListener(view1 -> {
            int selectedLutemonId = rgHomeLutemons.getCheckedRadioButtonId();
            int rgId = rgChooseLutemonsFromHome.getCheckedRadioButtonId();
            
            if (selectedLutemonId != -1){
                if (rgId == -1) {
                    Toast.makeText(getContext(), "Valitse kohde!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Lutemon chosenLutemon = home.getLutemon(selectedLutemonId);

                if (rgId == R.id.rbTrainingAreaFromHome){
                    //Here we move lutemons to trainingarea
                    home.moveLutemon(chosenLutemon, TrainingArea.getInstance());
                } else if (rgId == R.id.rbBattleFieldFromHome){
                    //Here we move lutemons to battleField
                    home.moveLutemon(chosenLutemon, BattleField.getInstance());
                }

                makeRadioButtons(view);
                ivHomeLutemonImage.setVisibility(View.INVISIBLE);
            } else {
                Toast.makeText(getContext(), "Valitse siirrettävä Lutemon!", Toast.LENGTH_SHORT).show();
            }

        });
        return view;
    }
    public RadioGroup makeRadioButtons(View view) {
        RadioGroup rgHomeLutemons = view.findViewById(R.id.rgHomeLutemons);
        Home home = Home.getInstance();
        if (rgHomeLutemons == null) return null;

        rgHomeLutemons.removeAllViews(); // Clear the old ones if there is any

        // Check what Lutemons are in Home
        for (Lutemon lutemon : home.getLutemons().values()) {
            RadioButton rb = new RadioButton(getContext());
            rb.setText(lutemon.getName() + ", HP: " + lutemon.getHealth() + "/" + lutemon.getMaxHealth() +  " (" + lutemon.getColor() + ")");

            // Set the radiobutton id to Lutemons own id
            rb.setId(lutemon.getId());

            rgHomeLutemons.addView(rb);
        }
        return rgHomeLutemons;
    }
    @Override
    public void onResume() {
        super.onResume();
        if (getView() != null) {
            makeRadioButtons(getView());
        }
    }
}