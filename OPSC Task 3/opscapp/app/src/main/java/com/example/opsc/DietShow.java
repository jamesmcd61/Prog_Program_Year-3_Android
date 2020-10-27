package com.example.opsc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DietShow extends AppCompatActivity {
    //creating of recycle variables
     RecyclerView recycle;
     DietAdapter dadapter;
     //creating of data variables
     DatabaseReference data;
     List<DietInfo> arr;
     String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_show);
        //linking recycle to the component
        recycle = findViewById(R.id.recyleView);
        recycle.setHasFixedSize(true);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        //getting data
        name = getIntent().getStringExtra("N");

        arr = new ArrayList<>();
        //Linking data base table
        data = FirebaseDatabase.getInstance().getReference("uploads");
        //DB method to sift data
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //for each loop to check the data
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){

                    DietInfo pie = postSnapshot.getValue(DietInfo.class);
                    //checking user data
                    if(name.equals(pie.getUsername())){
                        arr.add(pie);
                    }

                }
                //sending data to adapter
                dadapter = new DietAdapter(DietShow.this,arr);
                recycle.setAdapter(dadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(DietShow.this, databaseError.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

    }
}
