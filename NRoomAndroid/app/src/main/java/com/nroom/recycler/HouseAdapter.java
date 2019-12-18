package com.nroom.recycler;

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
        holder.price.setText(StaticResources.getPrice(houseItem, context));
        holder.location.setText(String.format(Locale.KOREA, context.getString(R.string.format_address), houseItem.get시군구명(), houseItem.get법정동(), houseItem.get건물명(), houseItem.get층()));

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("images", StaticResources.getImages((position + 1) % 5));
            intent.putExtra("summary", "요약");
            intent.putExtra("name", houseItem.get건물명());
            intent.putExtra("price", holder.price.getText());
            intent.putExtra("sale_id", houseItem.getId());
            intent.putExtra("area", houseItem.get전용면적());
            intent.putExtra("admin_exp", "5만");
            intent.putExtra("structure", houseItem.get집종류());
            intent.putExtra("detail_summary", "☆★회사 다니시는 분들 주목★☆\n이 위치, 이 금액 실화냐?!\n\n■널찍한 공간, 수납공간도 다수입니다!\n\n■빌트인 냉장고부터 건조기까지 완전 풀옵션~★\n\n■최고급 소재 사용으로 환경 호르몬 걱정은 안녕~~\n\n■관리비 걱정 없이 생활을 만끽하세요~");
            intent.putExtra("lat", houseItem.get위도());
            intent.putExtra("lng", houseItem.get경도());


            context.startActivity(intent);
        });
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
