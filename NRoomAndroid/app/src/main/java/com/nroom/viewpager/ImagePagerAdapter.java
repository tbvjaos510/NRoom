package com.nroom.viewpager;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nroom.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ImagePagerAdapter extends PagerAdapter {

    private final int[] drawablesIds;
    private final Context context;


    public ImagePagerAdapter(Context context, int... drawablesIds) {
        this.context = context;
        this.drawablesIds = drawablesIds;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image_pager, container, false);

        ImageView imageView = view.findViewById(R.id.image_view);
        imageView.setImageResource(drawablesIds[position]);
        container.addView(imageView);

        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return drawablesIds.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
