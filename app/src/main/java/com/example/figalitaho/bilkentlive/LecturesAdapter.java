package com.example.figalitaho.bilkentlive;
/**
 * Created by Erin Avllazagaj on 11/05/2015.
 */


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LecturesAdapter extends ArrayAdapter<InfoHolder>{

    private Activity context;
    private InfoHolder[] Infos;
    public LecturesAdapter(Activity context,InfoHolder[] infos) {
        super(context, R.layout.list_single, infos);
        this.context = context;
        this.Infos = infos;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        txtTitle.setText(Infos[position].toString());
        return rowView;
    }
}