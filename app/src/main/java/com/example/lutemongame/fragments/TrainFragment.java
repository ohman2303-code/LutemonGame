package com.example.lutemongame.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        makeRadioButtons();

        //RadioGroup for choosing where to move lutemons
        RadioGroup rgChooseLutemons = rgChooseLutemons.findViewById(R.id.rgChooseLutemons);
        RadioButton rbHome;
        RadioButton rbTrainingArea;
        RadioButton rbBattleField;
        int rgId = rgChooseLutemons.getCheckedRadioButtonId();

        if (rgId == R.id.rbHome){
            //Here we move lutemons to home
            MainActivity.home.moveLutemon(lutemon, MainActivity.Home);
        } else if (rgId == R.id.rbTrainingArea){
            //Here we move lutemons to training area
        } else if (rgId == R.id.rbBattleField){
            //Here we move lutemons to battle field
        }
        return view;
    }
    public void makeRadioButtons(){
        // Radiogroups from fragments
        RadioGroup rgTrainLutemons = rgTrainLutemons.findViewById(R.id.rgTrainLutemons);
        RadioGroup rgChooseLutemons = rgChooseLutemons.findViewById(R.id.rgChooseLutemons);
        //Lutemonlist from TrainingArea
        HashMap<Integer, String> lutemons = trainingArea.getLutemons();
        // Creating radiobuttons for chosen lutemons to be moved
        RadioButton rbChooseLutemon;
        int i = 0;
        for (Lutemon lutemon : lutemons.values(){
            rbChooseLutemon = new RadioButton(this);
            rbChooseLutemon.setText(lutemon.getName());
            rbChooseLutemon.setId(i++);
            rgTrainLutemons.addView(rbChooseLutemon);
        }



    }
}