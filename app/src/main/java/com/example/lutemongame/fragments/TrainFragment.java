package com.example.lutemongame.fragments;

import static com.example.lutemongame.MainActivity.trainingArea;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.lutemongame.Lutemon;
import com.example.lutemongame.MainActivity;
import com.example.lutemongame.R;

import java.util.HashMap;

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

        RadioGroup rgTrainLutemons = makeRadioButtons(view);

        //RadioGroup for choosing where to move lutemons
        RadioGroup rgChooseLutemons = view.findViewById(R.id.rgChooseLutemons);
        RadioButton rbHome = view.findViewById(R.id.rbHome);
        RadioButton rbBattleField = view.findViewById(R.id.rbBattleField);


        //Here we move Lutemons to Home or BattleField (They are currently in TrainingArea)
        Button moveLutemonsButton = view.findViewById(R.id.MoveLutemonsButton);
        moveLutemonsButton.setOnClickListener(view1 -> {
            int selectedLutemonId = rgTrainLutemons.getCheckedRadioButtonId();
            int rgId = rgChooseLutemons.getCheckedRadioButtonId();
            if (selectedLutemonId != -1){
                Lutemon chosenLutemon = MainActivity.trainingArea.getLutemon(selectedLutemonId);

                if (rgId == R.id.rbHome){
                    //Here we move lutemons to home
                    trainingArea.moveLutemon(chosenLutemon, MainActivity.home);

                } else if (rgId == R.id.rbBattleField){
                    //Here we move lutemons to battleField
                    trainingArea.moveLutemon(chosenLutemon, MainActivity.battleField);
                }
            }

        });



        //Button for leveling up chosen Lutemon. 25 presses to level up once and max 10 levels (for now)
        Button trainLutemonButton = view.findViewById(R.id.TrainLutemonButton);
        trainLutemonButton.setOnClickListener(new View.OnClickListener() {
            int clickCounter = 0;
            int timesLevelledUp = 0;
            @Override
            public void onClick(View view) {
                clickCounter = clickCounter + 1;
                if (clickCounter == 25 && timesLevelledUp <= 10){
                    //Here we level up Lutemon using TrainingArea
                    int selectedLutemonId = rgTrainLutemons.getCheckedRadioButtonId();
                    Lutemon chosenLutemon = MainActivity.trainingArea.getLutemon(selectedLutemonId);
                    trainingArea.train(chosenLutemon);

                    //After we set the clickCounter back to 0 and increase the timesLevelledUp by 1
                    clickCounter = 0;
                    timesLevelledUp += 1;
                }


            }
        });

        return view;
    }

    public RadioGroup makeRadioButtons(View view) {
        RadioGroup rgTrainLutemons = view.findViewById(R.id.rgTrainLutemons);
        if (rgTrainLutemons == null) return null;

        rgTrainLutemons.removeAllViews(); // Clear the old ones if there is any

        // Check what Lutemons are in TrainingArea
        for (Lutemon lutemon : MainActivity.trainingArea.getLutemons().values()) {
            RadioButton rb = new RadioButton(getContext());
            rb.setText(lutemon.getName() + " (" + lutemon.getColor() + ")");

            // Set the radiobutton id to Lutemons own id
            rb.setId(lutemon.getId());

            rgTrainLutemons.addView(rb);
        }
        return rgTrainLutemons;
    }
}
