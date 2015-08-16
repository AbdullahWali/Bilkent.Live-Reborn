package com.example.figalitaho.bilkentlive;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Figali Taho on 09/05/2015.
 */
public class CustomAdapterForLectures extends ArrayAdapter<ModelForLectures> {

    private ArrayList<String> chosenList = new ArrayList<String>();
    //    ModelForLectures[] modelItems = null;
    ModelForLectures[] modelItems = null;
    Context context;

    public CustomAdapterForLectures( Context context, ModelForLectures[] source){
        super( context, R.layout.row_checkbox_listview, source);
        this.context = context;
        this.modelItems = source;
    }

    //inner class to hold one view
    private class ViewHolder{
        TextView textView;
        CheckBox checkBox;
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent){

        View v = convertView;

        //create one view holder object
        ViewHolder viewHolder = null;

        if( v == null){

            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            v = inflater.inflate(R.layout.row_checkbox_listview, parent, false);

            //initialise viewholder object
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) v.findViewById(R.id.textView);
            viewHolder.checkBox = (CheckBox) v.findViewById(R.id.checkBox);

            viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){

                public void onCheckedChanged( CompoundButton compoundButton, boolean isChecked){

                    int getPosition = (Integer) compoundButton.getTag();

                    if( modelItems[getPosition].getValue()==0 && compoundButton.isChecked()) {
                        modelItems[getPosition].setSelected(compoundButton.isChecked());
                        if( !chosenList.contains(modelItems[getPosition].getValueOfFilter()))
                            chosenList.add(modelItems[getPosition].getValueOfFilter());
                    }
                    if( modelItems[getPosition].getValue()== 1 && !compoundButton.isChecked()) {
                        chosenList.remove(modelItems[getPosition]);
                        modelItems[getPosition].setSelected(false);
                    }
                }
            });

            v.setTag(viewHolder);
            v.setTag(R.id.textView, viewHolder.textView);
            v.setTag(R.id.checkBox, viewHolder.checkBox);


        }else{
            viewHolder = ( ViewHolder)v.getTag();
        }

        viewHolder.checkBox.setTag(position);
        viewHolder.textView.setText(modelItems[position].getValueOfFilter());
        viewHolder.checkBox.setChecked(modelItems[position].isSelected());
        Log.d("AtModelItems", "ta dalloj ------" + chosenList);
        return v;
    }

    public ArrayList<String> getChosenList() {
        return this.chosenList;
    }
}
