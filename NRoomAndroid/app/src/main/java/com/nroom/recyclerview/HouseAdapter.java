package com.nroom.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nroom.R;
import com.nroom.activity.DetailActivity;
import com.nroom.activity.ListActivity;
import com.nroom.data.HouseItem;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HouseAdapter extends RecyclerView.Adapter<HouseViewHolder> {

    private final Context context;
    private ArrayList<HouseItem> houseItems;

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

        holder.image.setImageDrawable(houseItem.getImage());
        if(houseItem.get보증금() > 0){
            if(houseItem.get월세금액() > 0){
                holder.price.setText("월세   " + houseItem.get월세금액() + "/" + houseItem.get보증금());
            }else{
                holder.price.setText("전세   " +houseItem.get보증금());
            }
        }
        else{
            holder.price.setText("매매   " + houseItem.get거래금액());
        }
        holder.location.setText(houseItem.get시군구명() + " " + houseItem.get법정동() + " " + houseItem.get건물명() + ", " + houseItem.get층() + "층");

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailActivity.class);
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
}
