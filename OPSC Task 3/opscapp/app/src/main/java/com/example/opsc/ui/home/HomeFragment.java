package com.example.opsc.ui.home;


import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.opsc.Nav;
import com.example.opsc.R;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    //Creating variables
    Bundle counter;
    Switch swi;
    String dis, goaldis;
    TextView km,step,goal;
    ProgressBar progress;
    public HomeFragment(){
        counter = Nav.run();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        //Linking views
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        //setting text
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        //finding and setting components
        step = (TextView) root.findViewById(R.id.steps);
        km =  root.findViewById(R.id.km);
         goal = (TextView) root.findViewById(R.id.goal);
        swi = root.findViewById(R.id.SwChanger);
        progress = root.findViewById(R.id.progressBar);


        //allocations bundle
        Bundle bundle = counter;
        //checking if null
        if (bundle != null) {
            //getting bundle data
             dis = bundle.getString("steps");
             goaldis = bundle.getString("goal");
            step.setText(dis);
            goal.setText(goaldis);
            float dists = Float.valueOf(dis);
            float kilo = dists/1000;
            String meter = String.valueOf(kilo);
            km.setText(meter);
            progress.setMax(Integer.valueOf(goaldis));
            progress.setProgress(Integer.valueOf(dis));
            //creating a listening to chekck if the ticked the stwitch
            swi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    //true
                    if(isChecked == true){
                        //setting data
                        swi.setText("Mile");
                        float dists = Float.valueOf(dis);
                        float milo = dists/1600;
                        String mile = String.valueOf(milo);
                        km.setText(mile);
                        //false
                    }else {
                        //setting data
                        swi.setText("Km");
                        float dists = Float.valueOf(dis);
                        float kilo = dists/1000;
                        String meter = String.valueOf(kilo);
                        km.setText(meter);
                    }
                }
            });


        }
        return root;
    }

}