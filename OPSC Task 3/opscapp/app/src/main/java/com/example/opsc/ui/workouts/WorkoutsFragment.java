package com.example.opsc.ui.workouts;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.opsc.LowerBody;
import com.example.opsc.R;
import com.example.opsc.Register;
import com.example.opsc.UpperBody;

public class WorkoutsFragment extends Fragment {



    private WorkoutsViewModel workoutsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        workoutsViewModel = ViewModelProviders.of(this).get(WorkoutsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_workouts, container, false);


        final TextView textView = root.findViewById(R.id.text_tools);
        workoutsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        //image click method taking you to next page
        ImageView image1 = (ImageView) root.findViewById(R.id.imageView4);
        image1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View root) {
                Intent intent = new Intent(getActivity(), LowerBody.class);
                startActivity(intent);
            }
        });
        //image click method taking you to next page
        ImageView image2 = (ImageView) root.findViewById(R.id.imageView5);
        image2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View root) {
                Intent intent = new Intent(getActivity(), UpperBody.class);
                startActivity(intent);
            }
        });
        return root;

    }

}