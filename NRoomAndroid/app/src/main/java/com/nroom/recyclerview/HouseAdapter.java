package com.nroom.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nroom.R;
import com.nroom.activity.DetailActivity;
import com.nroom.data.HouseItem;
import com.nroom.data.StaticResources;

import java.util.ArrayList;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HouseAdapter extends RecyclerView.Adapter<HouseViewHolder> {

    private final Context context;
    private ArrayList<HouseItem> houseItems;
    private final ArrayList<HouseItem> selectHouseItem = new ArrayList<>();

    private boolean monthCheck = true;
    private boolean yearCheck = true;
    private boolean tradeCheck = true;

    public HouseAdapter(Context context) {
        this(context, new ArrayList<>());
    }

    public HouseAdapter(Context context, ArrayList<HouseItem> houseItems) {
        this.context = context;
        this.houseItems = houseItems;
        updateList();
    }

    @NonNull
    @Override
    public HouseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HouseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HouseViewHolder holder, int position) {
        HouseItem houseItem = selectHouseItem.get(position);

        holder.image.setImageDrawable(houseItem.getImage());
        if (houseItem.get보증금() > 0 || houseItem.get월세금액() > 0) {
            if (houseItem.get월세금액() > 0) {
                holder.price.setText(String.format(Locale.KOREA, context.getString(R.string.format_month), houseItem.get보증금(), houseItem.get월세금액()));
            } else {
                holder.price.setText(String.format(Locale.KOREA, context.getString(R.string.format_year), houseItem.get보증금()));
            }
        } else {
            holder.price.setText(String.format(Locale.KOREA, context.getString(R.string.format_trade), houseItem.get거래금액()));
        }
        holder.location.setText(String.format(Locale.KOREA, context.getString(R.string.format_address), houseItem.get시군구명(), houseItem.get법정동(), houseItem.get건물명(), houseItem.get층()));

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("images", getImages((position + 1) % 5));
            intent.putExtra("summary", "요약");
            intent.putExtra("price", holder.price.getText());
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
        return selectHouseItem.size();
    }

    public void setHouseItems(ArrayList<HouseItem> houseItems) {
        this.houseItems = houseItems;
        updateList();
    }

    public void addHouseItems(ArrayList<HouseItem> houseItems) {
        this.houseItems.addAll(houseItems);
        updateList();
    }

    public void updateList() {
        selectHouseItem.clear();

        if (monthCheck) {
            for (HouseItem houseItem : houseItems) {
                if (houseItem.get보증금() > 0 || houseItem.get월세금액() > 0) {
                    if (houseItem.get월세금액() > 0) {
                        selectHouseItem.add(houseItem);
                    }
                }
            }
        }

        if (yearCheck) {
            for (HouseItem houseItem : houseItems) {
                if (houseItem.get보증금() > 0 || houseItem.get월세금액() > 0) {
                    if (!(houseItem.get월세금액() > 0)) {
                        selectHouseItem.add(houseItem);
                    }
                }
            }
        }

        if (tradeCheck) {
            for (HouseItem houseItem : houseItems) {
                if (!(houseItem.get보증금() > 0 || houseItem.get월세금액() > 0)) {
                    selectHouseItem.add(houseItem);
                }
            }
        }

        notifyDataSetChanged();
    }

    public void setShowMonth(boolean monthCheck) {
        this.monthCheck = monthCheck;
    }

    public void setShowYear(boolean yearCheck) {
        this.yearCheck = yearCheck;
    }

    public void setShowTrade(boolean tradeCheck) {
        this.tradeCheck = tradeCheck;
    }
}
