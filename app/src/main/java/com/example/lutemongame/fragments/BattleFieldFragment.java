package com.example.lutemongame.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.lutemongame.BattleField;
import com.example.lutemongame.FightActivity;
import com.example.lutemongame.Home;
import com.example.lutemongame.Lutemon;
import com.example.lutemongame.R;
import com.example.lutemongame.TrainingArea;

import java.util.ArrayList;

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


        updateLutemonLists(view);

        //RadioGroup for choosing where to move lutemons
        RadioGroup rgChooseTarget = view.findViewById(R.id.rgChooseTargetFromBattleField);
        BattleField battleField = BattleField.getInstance();
        RadioGroup rgBattleFieldLutemons = view.findViewById(R.id.rgBattleFieldLutemons);
        ImageView ivBattleFieldLutemonImage = view.findViewById(R.id.ivBattleFieldLutemonImage);
        Button moveLutemonsButton = view.findViewById(R.id.btnMoveLutemonsFromBattleField);

        Button btnGoToFight = view.findViewById(R.id.btnGoToFight);

        btnGoToFight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToFightActivity(view);
            }
        });


        // First we hide the picture of the chosen Lutemon
        ivBattleFieldLutemonImage.setVisibility(View.GONE);

        // Update the photo when the Lutemon is chosen and make sure some place is chosen
        rgBattleFieldLutemons.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId != -1) {
                Lutemon chosenLutemon = battleField.getLutemon(checkedId);
                if (chosenLutemon != null) {
                    ivBattleFieldLutemonImage.setImageResource(chosenLutemon.getImage());
                    ivBattleFieldLutemonImage.setVisibility(View.VISIBLE);
                }
            } else {
                ivBattleFieldLutemonImage.setVisibility(View.GONE);

            }
        });




        //Here we move Lutemons to Home or TrainingArea (They are currently in BattleField)

        moveLutemonsButton.setOnClickListener(view1 -> {
            int selectedLutemonId = rgBattleFieldLutemons.getCheckedRadioButtonId();
            int rgId = rgChooseTarget.getCheckedRadioButtonId();
            
            if (selectedLutemonId != -1){
                Lutemon chosenLutemon = battleField.getLutemon(selectedLutemonId);

                if (rgId == R.id.rbHomeFromBattleField){
                    battleField.moveLutemon(chosenLutemon, Home.getInstance());
                } else if (rgId == R.id.rbTrainingAreaFromBattleField){
                    battleField.moveLutemon(chosenLutemon, TrainingArea.getInstance());
                } else{
                    Toast.makeText(getContext(), "Valitse kohde!", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                // Refresh both lists and the picture after moving
                updateLutemonLists(view);
                ivBattleFieldLutemonImage.setVisibility(View.INVISIBLE);
            } else{
                Toast.makeText(getContext(), "Valitse siirrettävä Lutemon!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void updateLutemonLists(View view) {
        makeRadioButtons(view);
        makeCheckBoxes(view);
    }

    public void makeRadioButtons(View view) {
        RadioGroup rgBattleFieldLutemons = view.findViewById(R.id.rgBattleFieldLutemons);
        BattleField battleField = BattleField.getInstance();
        if (rgBattleFieldLutemons == null) return;

        rgBattleFieldLutemons.removeAllViews();

        for (Lutemon lutemon : battleField.getLutemons().values()) {
            RadioButton rb = new RadioButton(getContext());
            rb.setText(lutemon.getName() + ", HP: " + lutemon.getHealth() + "/" + lutemon.getMaxHealth() +  " (" + lutemon.getColor() + ")");
            rb.setId(lutemon.getId());
            rgBattleFieldLutemons.addView(rb);
        }
    }

    public void makeCheckBoxes(View view) {
        LinearLayout llLutemonsForBattle = view.findViewById(R.id.llLutemonsForBattle);
        BattleField battleField = BattleField.getInstance();
        if (llLutemonsForBattle == null) return;

        llLutemonsForBattle.removeAllViews();

        for (Lutemon lutemon : battleField.getLutemons().values()) {
            CheckBox cb = new CheckBox(getContext());
            cb.setText(lutemon.getName() + " (" + lutemon.getColor() + ")");
            cb.setId(lutemon.getId());
            llLutemonsForBattle.addView(cb);
        }
    }

    public void switchToFightActivity(View view){
        ArrayList<Integer> selectedLutemonIds = new ArrayList<>();
        LinearLayout llLutemonsForBattle = view.findViewById(R.id.llLutemonsForBattle);
        
        for (int i = 0; i < llLutemonsForBattle.getChildCount(); i++) {
            View v = llLutemonsForBattle.getChildAt(i);
            if (v instanceof CheckBox) {
                CheckBox cb = (CheckBox) v;
                if (cb.isChecked()) {
                    selectedLutemonIds.add(cb.getId());
                }
            }
        }

        if (selectedLutemonIds.size() == 2) {
            Intent intent = new Intent(getActivity(), FightActivity.class);
            intent.putIntegerArrayListExtra("selectedLutemonIds", selectedLutemonIds);
            startActivity(intent);
        } else {
            Toast.makeText(getContext(), "Valitse tasan kaksi Lutemonia taisteluun!", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        if (getView() != null) {
            updateLutemonLists(getView());
        }
    }
}
