package com.example.lutemongame.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.lutemongame.BattleField;
import com.example.lutemongame.FightActivity;
import com.example.lutemongame.Home;
import com.example.lutemongame.Lutemon;
import com.example.lutemongame.MainActivity;
import com.example.lutemongame.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BattleFieldFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BattleFieldFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BattleFieldFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BattleFieldFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BattleFieldFragment newInstance(String param1, String param2) {
        BattleFieldFragment fragment = new BattleFieldFragment();
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
        View view = inflater.inflate(R.layout.fragment_battlefield, container, false);


        RadioGroup rgBattleFieldLutemons = makeRadioButtons(view);
        BattleField battleField = BattleField.getInstance();

        //RadioGroup for choosing where to move lutemons
        RadioGroup rgChooseLutemons = view.findViewById(R.id.rgChooseLutemonsFromBattleField);
        RadioButton rbHome = view.findViewById(R.id.rbHomeFromBattleField);
        RadioButton rbTrainingArea = view.findViewById(R.id.rbTrainingAreaFromBattleField);

        Button btnGoToFight = view.findViewById(R.id.btnGoToFight);
        btnGoToFight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToFightActivity(v);
            }
        });


        //Here we move Lutemons to Home or TrainingArea (They are currently in BattleField)
        Button moveLutemonsButton = view.findViewById(R.id.btnMoveLutemonsFromBattleField);
        moveLutemonsButton.setOnClickListener(view1 -> {
            int selectedLutemonId = rgBattleFieldLutemons.getCheckedRadioButtonId();
            int rgId = rgChooseLutemons.getCheckedRadioButtonId();
            if (selectedLutemonId != -1){
                Lutemon chosenLutemon = battleField.getLutemon(selectedLutemonId);

                if (rgId == R.id.rbHomeFromBattleField){
                    //Here we move lutemons to home
                    battleField.moveLutemon(chosenLutemon, Home.getInstance());

                } else if (rgId == R.id.rbBattleFieldFromTrainingArea){
                    //Here we move lutemons to battleField
                    battleField.moveLutemon(chosenLutemon, BattleField.getInstance());
                }
            }

        });




        return view;
    }
    public RadioGroup makeRadioButtons(View view) {
        RadioGroup rgBattleFieldLutemons = view.findViewById(R.id.rgBattleFieldLutemons);
        BattleField battleField= BattleField.getInstance();
        if (rgBattleFieldLutemons == null) return null;

        rgBattleFieldLutemons.removeAllViews(); // Clear the old ones if there is any

        // Check what Lutemons are in BattleField
        for (Lutemon lutemon : battleField.getLutemons().values()) {
            RadioButton rb = new RadioButton(getContext());
            rb.setText(lutemon.getName() + " (" + lutemon.getColor() + ")");

            // Set the radiobutton id to Lutemons own id
            rb.setId(lutemon.getId());

            rgBattleFieldLutemons.addView(rb);
        }
        return rgBattleFieldLutemons;
    }
    public void switchToFightActivity(View view){
        Intent intent = new Intent(getActivity(), FightActivity.class);
        startActivity(intent);
    }
}