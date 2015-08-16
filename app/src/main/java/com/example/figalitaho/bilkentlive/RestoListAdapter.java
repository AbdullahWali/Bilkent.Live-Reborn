package com.example.figalitaho.bilkentlive;
/**
 * Created by Abdullah Wali on 26/04/2015.
 */


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RestoListAdapter extends ArrayAdapter<Restaurant>{

    private final Activity context;
    private final Restaurant[] Restaurants;
    private final Integer[] imageId;
    public RestoListAdapter(Activity context,
                            Restaurant[] restos, Integer[] imageId) {

        super(context, R.layout.list_single, restos);
        this.context = context;
        this.Restaurants = restos;
        this.imageId = imageId;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        TextView timeText = ( TextView) rowView.findViewById(R.id.timeText);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);

        txtTitle.setText(Restaurants[position].getName());
        timeText.setText( ( Restaurants[position].getTimes()[Restaurant.getDayInstance()]).toString());
        imageView.setImageResource(imageId[position]);

        return rowView;
    }
}