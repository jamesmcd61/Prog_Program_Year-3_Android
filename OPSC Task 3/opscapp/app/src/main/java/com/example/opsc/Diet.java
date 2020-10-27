package com.example.opsc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.opsc.ui.profile.ProfileFragment;
import com.example.opsc.ui.workouts.WorkoutsFragment;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Diet extends AppCompatActivity {
    //creating variables
    private  Button btnChoose,btnUpload,btnShow,btnback;
    private ImageView imageView;
    private Uri filepath;
    int PICK_IMAGE_REQUEST = 1;
    private StorageTask upla;
    String user;

    TextView fat, carbs,weight,protein;
    //creating database variables
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);
        //linking to database
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference("uploads");
        //linking to database
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("uploads");
        //linking variable to the components
        btnChoose = (Button) findViewById(R.id.BtnAddPic);
        btnUpload = (Button) findViewById(R.id.BtnAddDiet);
        btnShow = (Button) findViewById(R.id.BtnShowDiet);
        fat = (TextView) findViewById(R.id.TxtFat);
        carbs = (TextView) findViewById(R.id.TxtCarbs);
        weight = (TextView) findViewById(R.id.Txtweight);
        protein = (TextView) findViewById(R.id.TxtProtein);

        user = getIntent().getStringExtra("NA");

        imageView = (ImageView) findViewById(R.id.Pic);
        btnback = (Button) findViewById(R.id.BtnBack);

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //button click method checking if the process of uploading is done
        btnUpload .setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(upla != null && upla.isInProgress()){
                    Toast.makeText(Diet.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                }else {
                    uploadImage();
                }
            }
        });
        //button click method taking you to next page
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdiet();
            }
        });

    }
    //method that allows you to save and send data to the database
    private void uploadImage(){
        //checking id there is a picture
        if(filepath != null){
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            //creating storage url
            final StorageReference ref = storageReference.child(System.currentTimeMillis() + "." + GetFileExtension(filepath));
            upla = ref.putFile(filepath);
            //creating od task to do
            Task<Uri> urlTask = upla.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    // Continue with the task to get the download URL
                    return ref.getDownloadUrl();
                }
                //completed method
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        //getting data entered and changing type
                        Uri downloadUri = task.getResult();
                        int f= Integer.parseInt(fat.getText().toString().trim());
                        int c= Integer.parseInt(carbs.getText().toString().trim());
                        int w= Integer.parseInt(weight.getText().toString().trim());
                        int p= Integer.parseInt(protein.getText().toString().trim());

                        //progress visial
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();
                        //setting data to the database
                        DietInfo diets = new DietInfo(f,c,w,p,downloadUri.toString(),user);
                        String diet = databaseReference.push().getKey();
                        databaseReference.child(diet).setValue(diets);

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(Diet.this,"Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    //methoed to choose image
    private void chooseImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }
    //methods to get permission for the camera
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            filepath = data.getData();
            Picasso.get().load(filepath).into(imageView);

        }
    }
    //making the uri variable
    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }
    //method to tak you to next screen
    public void showdiet(){
        Intent intent = new Intent(this,DietShow.class);
        intent.putExtra("N",getIntent().getStringExtra("NA"));
        startActivity(intent);
    }

}
