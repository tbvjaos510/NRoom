package com.nroom.recycler;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nroom.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HouseViewHolder extends RecyclerView.ViewHolder {

    public ImageView image;
    public TextView price;
    public TextView location;

    public HouseViewHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image_house);
        price = itemView.findViewById(R.id.price);
        location = itemView.findViewById(R.id.location);
    }
}
