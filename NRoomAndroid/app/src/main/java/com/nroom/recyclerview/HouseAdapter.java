package com.nroom.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nroom.R;
import com.nroom.data.HouseItem;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HouseAdapter extends RecyclerView.Adapter<HouseViewHolder> {

    private final ArrayList<HouseItem> houseItems;

    public HouseAdapter(ArrayList<HouseItem> houseItems) {
        this.houseItems = houseItems;
    }

    @NonNull
    @Override
    public HouseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HouseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HouseViewHolder holder, int position) {
        HouseItem houseItem = houseItems.get(position);

        holder.image.setImageDrawable(houseItem.getImage());
        holder.price.setText(houseItem.get거래금액());
        holder.location.setText(houseItem.get경도() + "");
        holder.notice.setText(houseItem.get법정동());
    }

    @Override
    public int getItemCount() {
        return houseItems.size();
    }
}
