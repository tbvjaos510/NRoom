package com.nroom.listview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nroom.R;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter{

    private ArrayList<ListViewItem> items = new ArrayList<>();

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public ListViewItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        ImageView iv_img = (ImageView) convertView.findViewById(R.id.image_house);
        TextView tv_price = (TextView) convertView.findViewById(R.id.price);
        TextView tv_location = (TextView) convertView.findViewById(R.id.location);
        TextView tv_notice = (TextView) convertView.findViewById(R.id.notice);

        ListViewItem listViewItem = getItem(position);

        iv_img.setImageDrawable(listViewItem.getIcon());
        tv_price.setText(listViewItem.getPrice());
        tv_location.setText(listViewItem.getLocation());
        tv_notice.setText(listViewItem.getNotice());

        return convertView;

    }

    public void addItem(Drawable img, String price, String location, String notice){
        ListViewItem item = new ListViewItem();

        item.setIcon(img);
        item.setPrice(price);
        item.setLocation(location);
        item.setNotice(notice);

        items.add(item);
    }
}
