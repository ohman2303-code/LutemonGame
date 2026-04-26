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
import android.widget.TextView;
import android.widget.Toast;

import com.example.lutemongame.BattleField;
import com.example.lutemongame.Home;
import com.example.lutemongame.Lutemon;
import com.example.lutemongame.R;
import com.example.lutemongame.TrainingArea;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    
    private int clickCounter = 0;

    public TrainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrainFragment newInstance(String param1, String param2) {
        TrainFragment fragment = new TrainFragment();
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
        View view = inflater.inflate(R.layout.fragment_train, container, false);

        makeRadioButtons(view);
        TrainingArea trainingArea = TrainingArea.getInstance();

        RadioGroup rgTrainLutemons = view.findViewById(R.id.rgHomeLutemons);
        RadioGroup rgChooseLutemons = view.findViewById(R.id.rgChooseLutemonsFromTrainingArea);
        TextView txtPointsUntilLvlUp = view.findViewById(R.id.txtPointsUntilLvlUp);
        Button trainLutemonButton = view.findViewById(R.id.TrainLutemonButton);
        Button moveLutemonsButton = view.findViewById(R.id.btnMoveLutemonsFromTrainingArea);
        ImageView ivTrainLutemonImage = view.findViewById(R.id.ivTrainLutemonImage);
        
        // Handle Training Image
        if (ivTrainLutemonImage != null) {
            ivTrainLutemonImage.setVisibility(View.GONE);
        }

        txtPointsUntilLvlUp.setText("Valitse Lutemon treenattavaksi");

        // Reset the clickounter when a new Lutemon is selected
        rgTrainLutemons.setOnCheckedChangeListener((group, checkedId) -> { // AI HELP
            clickCounter = 0;
            updateTrainingStatus(txtPointsUntilLvlUp, checkedId);
            if (checkedId != -1 && ivTrainLutemonImage != null) {
                Lutemon lutemon = trainingArea.getLutemon(checkedId);
                if (lutemon != null) {
                    ivTrainLutemonImage.setImageResource(lutemon.getImage());
                    ivTrainLutemonImage.setVisibility(View.VISIBLE);
                }
            } else {
                ivTrainLutemonImage.setVisibility(View.GONE); //If lutemon is not chosen hide the image
            }
        });
        
        // Here we train the chosen Lutemon and update the status (possible until lvl 10)
        trainLutemonButton.setOnClickListener(v -> {
            int selectedLutemonId = rgTrainLutemons.getCheckedRadioButtonId();
            if (selectedLutemonId != -1) {
                Lutemon chosenLutemon = trainingArea.getLutemon(selectedLutemonId);
                
                if (chosenLutemon.getExperience() >= 10) {
                    txtPointsUntilLvlUp.setText("Maksimitaso 10 saavutettu!");
                    return;
                }

                clickCounter++;
                if (clickCounter >= 25) {
                    trainingArea.train(chosenLutemon);
                    clickCounter = 0;
                    Toast.makeText(getContext(), chosenLutemon.getName() + " nousi seuraavalle tasolle!", Toast.LENGTH_SHORT).show();
                }
                updateTrainingStatus(txtPointsUntilLvlUp, selectedLutemonId);
            } else {
                Toast.makeText(getContext(), "Valitse ensin Lutemon!", Toast.LENGTH_SHORT).show();
            }
        });

        // Here is the mechanic for moving Lutemons from TrainingArea to Home or BattleField
        moveLutemonsButton.setOnClickListener(view1 -> {
            int selectedLutemonId = rgTrainLutemons.getCheckedRadioButtonId();
            int rgId = rgChooseLutemons.getCheckedRadioButtonId();
            
            if (selectedLutemonId != -1){
                if (rgId == -1) {
                    Toast.makeText(getContext(), "Valitse kohde!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Lutemon chosenLutemon = trainingArea.getLutemon(selectedLutemonId);

                if (rgId == R.id.rbHomeFromTrainingArea){
                    // Here we move Lutemon to Home
                    trainingArea.moveLutemon(chosenLutemon, Home.getInstance());
                } else if (rgId == R.id.rbBattleFieldFromTrainingArea){
                    // Here we move Lutemon to BattleField
                    trainingArea.moveLutemon(chosenLutemon, BattleField.getInstance());
                }
                
                makeRadioButtons(view);
                if (ivTrainLutemonImage != null) ivTrainLutemonImage.setVisibility(View.GONE);
                txtPointsUntilLvlUp.setText("Valitse Lutemon treenattavaksi");
                clickCounter = 0;
            } else {
                Toast.makeText(getContext(), "Valitse siirrettävä Lutemon!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    // Here is the method for updating the training status of the chosen Lutemon
    private void updateTrainingStatus(TextView textView, int lutemonId) {
        Lutemon lutemon = TrainingArea.getInstance().getLutemon(lutemonId);
        if (lutemon != null) {
            int remaining = 25 - clickCounter;
            textView.setText(remaining + " klikkausta seuraavaan tasoon.\nNykyinen taso: " + lutemon.getExperience() + "/10");
        }
    }

    // Here we make the radiobuttons depending on the Lutemons in TrainingArea
    public RadioGroup makeRadioButtons(View view) {
        RadioGroup rgTrainLutemons = view.findViewById(R.id.rgHomeLutemons);
        TrainingArea trainingArea = TrainingArea.getInstance();
        if (rgTrainLutemons == null) return null;

        rgTrainLutemons.removeAllViews();
        for (Lutemon lutemon : trainingArea.getLutemons().values()) {
            RadioButton rb = new RadioButton(getContext());
            rb.setText(lutemon.getName() + ", HP: " + lutemon.getHealth() + "/" + lutemon.getMaxHealth() +  " (" + lutemon.getColor() + ")");
            rb.setId(lutemon.getId());
            rgTrainLutemons.addView(rb);
        }
        return rgTrainLutemons;
    }
    @Override
    public void onResume() {
        super.onResume();
        if (getView() != null) {
            makeRadioButtons(getView());
        } // AI HELP
    }

}
