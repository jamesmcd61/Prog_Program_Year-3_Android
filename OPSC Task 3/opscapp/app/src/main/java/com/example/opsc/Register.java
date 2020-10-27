package com.example.opsc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {


    FirebaseDatabase database;
    DatabaseReference users;

    EditText edtName, edtSurname, edtPassword, edtAge, edtWeight, edtHeight, edtGoalDis, edtEmail, edtWeightgoal, edtCaloriegoal;
    Button btnReg;
    TextView back;
    ImageView imageView;
    Button btnPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextView text =(TextView)findViewById(R.id.Back);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });
        //assign picture inputs
        imageView = findViewById(R.id.Image);
        btnPic = findViewById(R.id.BtnPic);

        //getting camera permission
        if (ContextCompat.checkSelfPermission(Register.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(Register.this,
                    new String[]{
                            Manifest.permission.CAMERA
                    },
                   100);

        }

        btnPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);

            }
        });


        //assign the db inputs
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");
        //assigning components
        edtName = (EditText) findViewById(R.id.Txtname);
        edtSurname = (EditText) findViewById(R.id.TxtSurname);
        edtPassword = (EditText) findViewById(R.id.Txtpassword);
        edtAge = (EditText) findViewById(R.id.Txtage);
        edtWeight = (EditText) findViewById(R.id.Txtweight);
        edtHeight = (EditText) findViewById(R.id.Txtheight);
        edtGoalDis = (EditText) findViewById(R.id.Txtgoal);
        edtEmail = (EditText) findViewById(R.id.Textemail);
        edtWeightgoal = (EditText) findViewById(R.id.Textweightgoal);
        edtCaloriegoal = (EditText) findViewById(R.id.Textcaloriegoal);
        btnReg = (Button) findViewById(R.id.BtnUpdate);


        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //linking class
                final Users user = new Users(edtName.getText().toString(),
                        edtSurname.getText().toString(),
                        edtPassword.getText().toString(),
                        edtAge.getText().toString(),
                        edtWeight.getText().toString(),
                        edtHeight.getText().toString(),
                        edtGoalDis.getText().toString(),
                        edtEmail.getText().toString(),
                        edtWeightgoal.getText().toString(),
                        edtCaloriegoal.getText().toString());
                //data base comparison and checking  and writing
                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(user.getPassword()).exists())
                            Toast.makeText(Register.this,"That Password is taken",Toast.LENGTH_SHORT).show();
                        else{
                            users.child(user.getName()).setValue(user);
                            Toast.makeText(Register.this,"Account Created Successfully",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });


    }


    public void openLogin(){
        //going to next page
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


    protected void onActivityResult(int requestCode,int resultCode , @Nullable Intent data){
        //allows to take picture
        if(requestCode == 100){

            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(captureImage);
        }
    }

}

