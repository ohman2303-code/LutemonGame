package com.example.lutemongame.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.lutemongame.Home;
import com.example.lutemongame.Lutemon;
import com.example.lutemongame.MainActivity;
import com.example.lutemongame.R;

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
        Home home = Home.getInstance();


        //RadioGroup for choosing where to move lutemons
        RadioGroup rgChooseLutemonsFromHome = view.findViewById(R.id.rgChooseLutemonsFromHome);
        RadioButton rbTrainingArea = view.findViewById(R.id.rbTrainingAreaFromHome);
        RadioButton rbBattleField = view.findViewById(R.id.rbBattleFieldFromHome);


        //Here we move Lutemons to TrainingArea or BattleField (They are currently in Home)
        Button moveLutemonsButton = view.findViewById(R.id.btnMoveLutemonsFromHome);
        moveLutemonsButton.setOnClickListener(view1 -> {
            int selectedLutemonId = rgHomeLutemons.getCheckedRadioButtonId();
            int rgId = rgChooseLutemonsFromHome.getCheckedRadioButtonId();
            if (selectedLutemonId != -1){
                Lutemon chosenLutemon = home.getLutemon(selectedLutemonId);

                if (rgId == R.id.rbTrainingAreaFromHome){
                    //Here we move lutemons to trainingarea
                    home.moveLutemon(chosenLutemon, MainActivity.trainingArea);

                } else if (rgId == R.id.rbBattleFieldFromHome){
                    //Here we move lutemons to battleField
                    home.moveLutemon(chosenLutemon, MainActivity.battleField);
                }
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
            rb.setText(lutemon.getName() + " (" + lutemon.getColor() + ")");

            // Set the radiobutton id to Lutemons own id
            rb.setId(lutemon.getId());

            rgHomeLutemons.addView(rb);
        }
        return rgHomeLutemons;
    }
}