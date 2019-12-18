package com.nroom.data;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.nroom.R;

import java.util.List;
import java.util.Locale;

public class StaticResources {
    public static final String HOST = "http://35.221.82.171";

    public static final int[] aptImages =
            {R.drawable.apt5_1,
                    R.drawable.apt5_2,
                    R.drawable.apt5_3,
                    R.drawable.apt5_4};

    public static final int[] oneImages1 =
            {R.drawable.one1_1,
                    R.drawable.one1_2,
                    R.drawable.one1_3,
                    R.drawable.one1_4,
                    R.drawable.one1_5,
                    R.drawable.one1_6};

    public static final int[] oneImages2 =
            {R.drawable.one2_1,
                    R.drawable.one2_2,
                    R.drawable.one2_3,
                    R.drawable.one2_4};

    public static final int[] oneImages3 =
            {R.drawable.one3_1,
                    R.drawable.one3_2,
                    R.drawable.one3_3,
                    R.drawable.one3_4,
                    R.drawable.one3_5,
                    R.drawable.one3_6,
                    R.drawable.one3_7};

    public static final int[] oneImages4 =
            {R.drawable.one4_1,
                    R.drawable.one4_2,
                    R.drawable.one4_3,
                    R.drawable.one4_4};


    public static int[] getImages(int i) {
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

    public static String getPrice(HouseItem houseItem, Context context) {
        if (houseItem.get보증금() > 0 || houseItem.get월세금액() > 0) {
            if (houseItem.get월세금액() > 0) {
                return String.format(Locale.KOREA, context.getString(R.string.format_month), houseItem.get보증금(), houseItem.get월세금액());
            } else {
                return String.format(Locale.KOREA, context.getString(R.string.format_year), houseItem.get보증금());
            }
        } else {
            return String.format(Locale.KOREA, context.getString(R.string.format_trade), houseItem.get거래금액());
        }
    }

    public static HouseItem findHouseItemByLatLng(List<HouseItem> houseItems, double lat, double lng) {
        for(HouseItem houseItem : houseItems) {
            if (houseItem.get위도() == lat && houseItem.get경도() == lng)
                return houseItem;
        }

        return null;
    }
}
