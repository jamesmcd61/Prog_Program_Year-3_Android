package com.example.opsc.ui.graphing;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.opsc.DietAdapter;
import com.example.opsc.DietInfo;
import com.example.opsc.DietShow;
import com.example.opsc.Nav;
import com.example.opsc.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GraphingFragment extends Fragment {

    private GraphingViewModel graphingViewModel;
    Button btncalories,btnweight,btnprotein;
    //creating of the variables
    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList<BarEntry> barEntries;
    ArrayList<Integer> arr;
    DatabaseReference data;
    int counter = 1;
    Bundle info;


    public void GraphingFragment(){
        info = Nav.things();
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        graphingViewModel = ViewModelProviders.of(this).get(GraphingViewModel.class);
        //linking views
        View root = inflater.inflate(R.layout.fragment_graphing, container, false);
        barChart = root.findViewById(R.id.bar1);
        //setting text
        final TextView textView = root.findViewById(R.id.text_gallery);
        graphingViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        //finding/Linking the variable to the component
        btnprotein = (Button) root.findViewById(R.id.BtnProtein);
        //doing onclick method
        btnprotein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View root) {
                barEntries = new ArrayList<>();
                arr = new ArrayList<>();
                final Bundle user = Nav.things();
                final String nam =  user.getString("N");
                //db connection to uploads table
                data = FirebaseDatabase.getInstance().getReference("uploads");
                //sifting data
                data.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //foreach going through data
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                            DietInfo pie = postSnapshot.getValue(DietInfo.class);
                            //checking if its the correct user data
                            if(nam.equals(pie.getUsername())) {
                                barEntries.add(new BarEntry(counter, Float.valueOf(pie.getProtein())));
                            }
                            counter++;
                        }
                        //setting parameters for bargraph
                        barChart.notifyDataSetChanged();
                        barDataSet = new BarDataSet(barEntries, "PROTEIN");
                        barData = new BarData(barDataSet);
                        //setting bar data
                        barChart.setData(barData);
                        barChart.getDescription().setText("PROTEIN CONSUMED");
                        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                        barDataSet.setValueTextColor(Color.BLACK);
                        barDataSet.setValueTextSize(18f);
                        barChart.invalidate();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
        //finding/Linking the variable to the component
        btncalories = (Button) root.findViewById(R.id.BtnCalories);
        //doing onclick method
        btncalories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View root) {
                barEntries = new ArrayList<>();
                arr = new ArrayList<>();
                final Bundle user = Nav.things();
                final String nam =  user.getString("N");

                data = FirebaseDatabase.getInstance().getReference("uploads");
                //sifting data
                data.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //foreach going through data
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                            DietInfo pie = postSnapshot.getValue(DietInfo.class);
                            //checking if its the correct user data
                            if(nam.equals(pie.getUsername())) {
                                barEntries.add(new BarEntry(counter, Float.valueOf(pie.getCarbs())));
                            }
                            counter++;
                        }
                        //setting parameters for bargraph
                        barChart.notifyDataSetChanged();
                        barDataSet = new BarDataSet(barEntries, "CALORIES");
                        barData = new BarData(barDataSet);
                        //setting bar data
                        barChart.setData(barData);
                        barChart.getDescription().setText("CALORIES CONSUMED");
                        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                        barDataSet.setValueTextColor(Color.BLACK);
                        barDataSet.setValueTextSize(18f);
                        barChart.invalidate();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        //finding/Linking the variable to the component
        btnweight = (Button) root.findViewById(R.id.BtnWieght);
        //doing onclick method
        btnweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View root) {
                barEntries = new ArrayList<>();
                arr = new ArrayList<>();
                final Bundle user = Nav.things();
                final String nam =  user.getString("N");

                data = FirebaseDatabase.getInstance().getReference("uploads");
                //sifting data
                data.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //foreach going through data
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                            DietInfo pie = postSnapshot.getValue(DietInfo.class);
                            //checking if its the correct user data
                            if(nam.equals(pie.getUsername())) {
                                barEntries.add(new BarEntry(counter, Float.valueOf(pie.getWeight())));
                            }

                            counter++;
                        }
                        //setting parameters for bargraph
                        barChart.notifyDataSetChanged();
                        barDataSet = new BarDataSet(barEntries, "WEIGHT");
                        barData = new BarData(barDataSet);
                        //setting bar data
                        barChart.setData(barData);
                        barChart.getDescription().setText("WEIGHT LOSS");
                        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                        barDataSet.setValueTextColor(Color.BLACK);
                        barDataSet.setValueTextSize(18f);
                        barChart.invalidate();
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        drawChart(barChart);
        return root;

    }
    public void drawChart(final BarChart bar) {
        barChart = bar;
        barEntries = new ArrayList<>();
        arr = new ArrayList<>();
        final Bundle user = Nav.things();
        final String nam =  user.getString("N");


        data = FirebaseDatabase.getInstance().getReference("uploads");
        //sifting data
        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //foreach going through data
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    DietInfo pie = postSnapshot.getValue(DietInfo.class);
                    //checking if its the correct user data
                    if(nam.equals(pie.getUsername())) {

                        barEntries.add(new BarEntry(counter, Float.valueOf(pie.getFat())));
                    }
                    counter++;
                }
                //setting parameters for bargraph
                barChart.notifyDataSetChanged();
                barDataSet = new BarDataSet(barEntries, "FAT");
                barData = new BarData(barDataSet);
                //setting bar data
                barChart.setData(barData);
                barChart.getDescription().setText("FAT CONSUMED");
                barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                barDataSet.setValueTextColor(Color.BLACK);
                barDataSet.setValueTextSize(18f);
                barChart.invalidate();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}