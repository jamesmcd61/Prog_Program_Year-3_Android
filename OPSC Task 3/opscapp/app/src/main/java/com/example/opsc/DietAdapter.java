package com.example.opsc;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DietAdapter extends RecyclerView.Adapter<DietAdapter.DietViewHolder> {
    private Context xcontent;
    private List<DietInfo> xuploads;

    // retreiving data and setting it to variables
    public DietAdapter(Context content, List<DietInfo> uploads){
        xcontent = content;
        xuploads = uploads;
    }
    @NonNull
    @Override
    //setting view to show with data
    public DietViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(xcontent).inflate(R.layout.diet_entry,parent,false);
        return new DietViewHolder(v);
    }

    @Override
    //method that setts the retrieved data to the componets
    public void onBindViewHolder(@NonNull final DietViewHolder holder, int position) {
        DietInfo diet = xuploads.get(position);
        holder.txtfat.setText(String.valueOf(diet.getFat()));
        holder.txtCarbs.setText(String.valueOf(diet.getCarbs()));
        holder.txtWeight.setText(String.valueOf(diet.getWeight()));
        holder.txtPortein.setText(String.valueOf(diet.getProtein()));
        //retrieveing and setting of picture
        Picasso.get()
                .load(diet.getImageuri())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imgImage);
    }

    @Override
    public int getItemCount() {
        return xuploads.size();
    }

    //method that sets variables and sets them to components
    public class DietViewHolder extends RecyclerView.ViewHolder{

        public TextView txtfat;
        public TextView txtCarbs;
        public TextView txtWeight;
        public TextView txtPortein;
        public ImageView imgImage;

        public DietViewHolder(@NonNull View itemView) {
            super(itemView);

            txtfat = itemView.findViewById(R.id.textfat);
            txtCarbs = itemView.findViewById(R.id.textcarbs);
            txtWeight = itemView.findViewById(R.id.textweight);
            txtPortein = itemView.findViewById(R.id.textprotein);
            imgImage = itemView.findViewById(R.id.ImgImage);

        }
    }
}
