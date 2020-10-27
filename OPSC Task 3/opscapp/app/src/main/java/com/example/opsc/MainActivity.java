package com.example.opsc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    //firebase
    FirebaseDatabase database;
    DatabaseReference users;

    EditText edtEmail, edtPassword;
    //variables
    String name,surname,height,weight,age,goal,weightgoal,caloriegoal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //data connecting
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");
        //linking of components
        edtEmail = (EditText) findViewById(R.id.editText2);
        edtPassword = (EditText) findViewById(R.id.editText);

        Button button =(Button) findViewById(R.id.login);
        //button click method
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting values mentered
                final String email = edtEmail.getText().toString();
                final String password = edtPassword.getText().toString();
                //db chking method
                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //checking if user exits
                        if (dataSnapshot.child(email).exists()) {
                            //checking its not empty
                            if (!email.isEmpty()) {
                                //linking to class
                                Users login = dataSnapshot.child(email).getValue(Users.class);
                                //getting the values for user info
                                name = login.getName();
                                surname = login.getSurname();
                                height = login.getHeight();
                                weight = login.getWeight();
                                age = login.getAge();
                                goal = login.getGoaldis();
                                weightgoal = login.getWeightgoal();
                                caloriegoal = login.getCaloriegoal();
                                //chekcing password
                                if (login.getPassword().equals(password)) {
                                    Toast.makeText(MainActivity.this, "Successful Login", Toast.LENGTH_SHORT).show();
                                    openHomePage();

                                } else {
                                    Toast.makeText(MainActivity.this, "Password Wrong", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "Emial not registered", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

        TextView text =(TextView)findViewById(R.id.register);
        //method to another screen
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });

    }


   public void openHomePage(){
        //sending data to antother page/act
        Intent intent = new Intent(this,Nav.class);
        intent.putExtra("NAME",name);
        intent.putExtra("SURENAME",surname);
        intent.putExtra("HEIGHT",height);
        intent.putExtra("WEIGHT",weight);
        intent.putExtra("AGE",age);
        intent.putExtra("GOAL",goal);
        intent.putExtra("Weightgoal",weightgoal);
        intent.putExtra("Caloriegoal",caloriegoal);
        startActivity(intent);
    }

    //method to send your to another page
    public void openRegister(){
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }

}
