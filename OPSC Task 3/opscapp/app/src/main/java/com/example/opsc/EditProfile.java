package com.example.opsc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.opsc.ui.profile.ProfileFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class EditProfile extends AppCompatActivity {
    //creating variables
    private Button btnupdate;
    private TextView na,su,he,we,ag,back,weg,cag;
    private DatabaseReference reference;
    String name,surename,height,weight,age,WeightGoal,CalorieGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        //link to dB
        reference = FirebaseDatabase.getInstance().getReference().child("Users");
        //retrieveing data into variables
         name  = getIntent().getStringExtra("NA");
         surename = getIntent().getStringExtra("SU");
         height = getIntent().getStringExtra("HE");
         weight = getIntent().getStringExtra("WE");
         age = getIntent().getStringExtra("AG");
         WeightGoal = getIntent().getStringExtra("WEGO");
         CalorieGoal = getIntent().getStringExtra("CAGO");
         //linking the components
         na = (TextView ) findViewById(R.id.Txtname);
         su  = (TextView ) findViewById(R.id.Txtsurname);
         he = (TextView ) findViewById(R.id.Txtheight);
         we = (TextView ) findViewById(R.id.Txtweight);
         ag = (TextView ) findViewById(R.id.Txtage);
         weg = (TextView) findViewById(R.id.TextWeightgoal);
         cag = (TextView) findViewById(R.id.TextCalorieGoal);
        //setting output display
        na.setText(name);
        su.setText(surename);
        he.setText(height);
        we.setText(weight);
        ag.setText(age);
        weg.setText(WeightGoal);
        cag.setText(CalorieGoal);

        btnupdate = (Button) findViewById(R.id.BtnUpdate);
        back = (TextView) findViewById(R.id.Back);
        //method that gets entered values and compares tot eh database
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String n = (na.getText().toString());
                String s = (su.getText().toString());
                String h = (he.getText().toString());
                String w = (we.getText().toString());
                String a = (ag.getText().toString());
                String wg = (weg.getText().toString());
                String cg = (cag.getText().toString());
                //array of variables with name
                HashMap hashmap =  new HashMap();
                hashmap.put("name",n);
                hashmap.put("surname",s);
                hashmap.put("height",h);
                hashmap.put("weight",w);
                hashmap.put("age",a);
                hashmap.put("weightgoal",wg);
                hashmap.put("caloriegoal",cg);
                //Db write to update the changed values
                reference.child(name).updateChildren(hashmap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(EditProfile.this, "Updated", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        //method to next screen
        back.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
