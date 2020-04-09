package com.example.pepintourapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class destinationAdaptor extends BaseAdapter {
    private List<destination> destList;
    private Context dContext;

    public destinationAdaptor(List<destination> destList, Context dContext) {
        this.destList = destList;
        this.dContext = dContext;
    }

    @Override
    public int getCount() {
        return destList.size();
    }

    @Override
    public Object getItem(int position) {
        return destList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View rowView = view;
        if(rowView == null){
            rowView = LayoutInflater.from(dContext).inflate(R.layout.layout_item,null);
            TextView name = (TextView) rowView.findViewById(R.id.label);
            ImageView image = (ImageView) rowView.findViewById(R.id.image);

            //Set Data
            Picasso.with(dContext).load(destList.get(position).getImageURL()).into(image);
            name.setText(destList.get(position).getName());
        }
        return rowView;
    }
}
