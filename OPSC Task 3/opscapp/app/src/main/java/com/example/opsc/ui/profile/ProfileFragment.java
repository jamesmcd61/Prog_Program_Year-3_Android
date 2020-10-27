package com.example.opsc.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.opsc.Diet;
import com.example.opsc.DietShow;
import com.example.opsc.EditProfile;
import com.example.opsc.Nav;
import com.example.opsc.R;

public  class ProfileFragment extends Fragment {

    Bundle user;
    //getting bundle from Nav act
    public ProfileFragment(){
        user = Nav.info();
    }


    private ProfileViewModel profileViewModel;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        //Lionking views
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        final TextView textView = root.findViewById(R.id.text_profile);
        //setting text
         profileViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);

            }
        });
        //setting and finding components
        TextView name = (TextView ) root.findViewById(R.id.TxtN);
        TextView surname  = (TextView ) root.findViewById(R.id.TxtS);
        TextView height = (TextView ) root.findViewById(R.id.TxtH);
        TextView weight = (TextView ) root.findViewById(R.id.TxtW);
        TextView age = (TextView ) root.findViewById(R.id.TxtA);
        TextView weightgoal = (TextView ) root.findViewById(R.id.TxtWG);
        TextView caloriegoal = (TextView ) root.findViewById(R.id.TxtCG);
        //creting bundle
        Bundle bundle = user;
        if(bundle !=null){
            //asigning bundle data to variables
            String n = bundle.getString("N");
            String s = bundle.getString("S");
            String h = bundle.getString("H");
            String w = bundle.getString("W");
            String a = bundle.getString("A");
            String wg = bundle.getString("WG");
            String cg = bundle.getString("CG");
            //setting of data to text views
            name.setText(n);
            surname.setText(s);
            height.setText(h);
            weight.setText(w);
            age.setText(a);
            weightgoal.setText(wg);
            caloriegoal.setText(cg);

        }
        //button click method
        Button edit = (Button) root.findViewById(R.id.BtnEdit);
        edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View root) {
                //parsing data to the next page
                Intent intent = new Intent(getActivity(), EditProfile.class);
                intent.putExtra("NA",user.getString("N"));
                intent.putExtra("SU",user.getString("S"));
                intent.putExtra("HE",user.getString("H"));
                intent.putExtra("WE",user.getString("W"));
                intent.putExtra("AG",user.getString("A"));
                intent.putExtra("WEGO",user.getString("WG"));
                intent.putExtra("CAGO",user.getString("CG"));
                startActivity(intent);
            }
        });
        //button click method
        Button diet = (Button) root.findViewById(R.id.BtnDiet);
        diet.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View root) {
                //parsing data to the next page
                Intent intent = new Intent(getActivity(), Diet.class);
                intent.putExtra("NA",user.getString("N"));
                startActivity(intent);
            }
        });


        return root;
    }

}

