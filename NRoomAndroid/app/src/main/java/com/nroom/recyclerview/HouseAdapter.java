package com.nroom.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nroom.R;
import com.nroom.activity.DetailActivity;
import com.nroom.data.HouseItem;
import com.nroom.data.StaticResources;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HouseAdapter extends RecyclerView.Adapter<HouseViewHolder> {

    private Context context;
    private ArrayList<HouseItem> houseItems;

    private boolean monthCheck = true;
    private boolean yearCheck = true;
    private boolean tradeCheck = true;

    public HouseAdapter(boolean monthCheck, boolean yearCheck, boolean tradeCheck){
        this.monthCheck = monthCheck;
        this.yearCheck = yearCheck;
        this.tradeCheck = tradeCheck;
        setCheck();
    }
    public HouseAdapter(Context context) {
        this(context, new ArrayList<>());
    }

    public HouseAdapter(Context context, ArrayList<HouseItem> houseItems) {
        this.context = context;
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
        Log.v("12312", monthCheck + ",");


        holder.image.setImageDrawable(houseItem.getImage());
        if(houseItem.get보증금() > 0){
            if(houseItem.get월세금액() > 0 && monthCheck){
                holder.price.setText("월세   " + houseItem.get보증금() + "/" + houseItem.get월세금액());
            }else{
                holder.price.setText("전세   " +houseItem.get보증금());
            }
        }
        else if(tradeCheck){
            holder.price.setText("매매   " + houseItem.get거래금액());
        }
        holder.location.setText(houseItem.get시군구명() + " " + houseItem.get법정동() + " " + houseItem.get건물명() + ", " + houseItem.get층() + "층");

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("images", getImages((position + 1) % 5));
            intent.putExtra("summary", "요약");
            intent.putExtra("price", houseItem.get거래금액());
            intent.putExtra("sale_id", houseItem.getId());
            intent.putExtra("area", houseItem.get전용면적());
            intent.putExtra("admin_exp", "UNKNOWN");
            intent.putExtra("structure", houseItem.get집종류());
            intent.putExtra("detail_summary", "아주 ㄱ----ㅣ----ㄴ---- 설명");

            context.startActivity(intent);
        });
    }

    private int[] getImages(int i) {
        int[] images = new int[0];
        switch (i) {
            case 0:
                images = StaticResources.aptImages;
                break;
            case 1:
                images = StaticResources.oneImages1;
                break;
            case 2:
                images = StaticResources.oneImages2;
                break;
            case 3:
                images = StaticResources.oneImages3;
                break;
            case 4:
                images = StaticResources.oneImages4;
                break;
        }

        return images;
    }

    @Override
    public int getItemCount() {
        return houseItems.size();
    }

    public void setHouseItems(ArrayList<HouseItem> houseItems) {
        this.houseItems = houseItems;
        notifyDataSetChanged();
    }

    public void addHouseItems(ArrayList<HouseItem> houseItems) {
        this.houseItems.addAll(houseItems);
        notifyDataSetChanged();
    }

    public void setCheck(){
        notifyDataSetChanged();
    }
}
