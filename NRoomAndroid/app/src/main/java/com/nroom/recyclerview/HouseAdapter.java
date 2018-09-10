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
import java.util.Locale;

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
                holder.price.setText(String.format(Locale.KOREA, context.getString(R.string.format_month), houseItem.get보증금(), houseItem.get월세금액()));
            }else{
                holder.price.setText(String.format(Locale.KOREA, context.getString(R.string.format_year), houseItem.get보증금()));
            }
        }
        else if(tradeCheck){
            holder.price.setText(String.format(Locale.KOREA, context.getString(R.string.format_trade),houseItem.get거래금액()));
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
            intent.putExtra("detail_summary", "☆★회사 다니시는 분들 주목★☆\n이 위치, 이 금액 실화냐?!\n\n■널찍한 공간, 수납공간도 다수입니다!\n\n■빌트인 냉장고부터 건조기까지 완전 풀옵션~★\n\n■최고급 소재 사용으로 환경 호르몬 걱정은 안녕~~\n\n■관리비 걱정 없이 생활을 만끽하세요~");

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
