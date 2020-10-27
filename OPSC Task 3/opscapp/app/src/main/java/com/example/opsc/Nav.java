package com.example.opsc;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import com.example.opsc.ui.graphing.GraphingFragment;
import com.example.opsc.ui.home.HomeFragment;
import com.example.opsc.ui.home.HomeViewModel;
import com.example.opsc.ui.profile.ProfileFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Nav extends AppCompatActivity implements SensorEventListener  {

    private AppBarConfiguration mAppBarConfiguration ;
    //creating variables
    public static Bundle stuff;
    public static Bundle steps;
    private SensorManager manager;
    private Sensor accelerometer;
    private float xAcceleration,yAcceleration,zAcceleration;
    private int foot = 0;
    private double Magnitudepre = 0;
    String name,surename,height,weight,age,goal,weightgoal,caloriegoal;
    HomeFragment home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //creating the nav drawer
        setContentView(R.layout.activity_nav);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //action bar at bottom
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //creating drawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_graphing, R.id.nav_routing,
                R.id.nav_workouts,R.id.nav_profile)
                .setDrawerLayout(drawer)
                .build();
        //nav controller
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        //retriveing data from the login
         name  = getIntent().getStringExtra("NAME");
         surename = getIntent().getStringExtra("SURENAME");
         height = getIntent().getStringExtra("HEIGHT");
         weight = getIntent().getStringExtra("WEIGHT");
         age = getIntent().getStringExtra("AGE");
         goal = getIntent().getStringExtra("GOAL");
         weightgoal = getIntent().getStringExtra("Weightgoal");
         caloriegoal = getIntent().getStringExtra("Caloriegoal");
        //setting it to a bundle for fragments
        //*************
        stuff = new Bundle();
        stuff.putString("N",name);
        stuff.putString("S",surename);
        stuff.putString("H",height);
        stuff.putString("W",weight);
        stuff.putString("A",age);
        stuff.putString("G",goal);
        stuff.putString("WG",weightgoal);
        stuff.putString("CG",caloriegoal);
        //************
        home = new HomeFragment();
        steps = new Bundle();
        steps.putString("goal",goal);
        //creating and starting sencers
        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);;

        //************

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public static Bundle info(){
        return stuff;
    }
    public static Bundle things(){
        return stuff;
    }

    @Override
    protected void onStart() {
        //starting sencors
        super.onStart();
        manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        //getting type of sencer
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            //getting sencor data and then calculating the steps
            xAcceleration = event.values[0];
            yAcceleration = event.values[1];
            zAcceleration = event.values[2];
            double Magnitude = Math.sqrt( xAcceleration* xAcceleration + yAcceleration*yAcceleration + zAcceleration*zAcceleration);
            double MagnitudeDelta = Magnitude - Magnitudepre;
            Magnitudepre = Magnitude;
            if(MagnitudeDelta>6){
                        foot++;
            }

                steps.putString("steps",String.valueOf(foot));

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    public static Bundle run(){
        return steps;
    }
}
