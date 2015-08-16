package com.example.figalitaho.bilkentlive;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Figali Taho on 23/04/2015.
 */
public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private Context context;

    private ClickListener clickListener;

    List<SingleRowInRecycler> data = Collections.emptyList();

    //constructor
    public MyRecycleViewAdapter(Context context, List<SingleRowInRecycler> data){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;

    }

    //going to be called everytime a row is going to be displayed
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SingleRowInRecycler current = data.get(position);
        holder.title.setText(current.getTitle());
        holder.icon.setImageResource(current.getIconId());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //will just represent one single row's items
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            title = (TextView)itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.listIcon);
        }

        @Override
        public void onClick( View v ){
          //  context.startActivity(new Intent( context, Lectures.class));
            if( clickListener != null){
                clickListener.itemClicked(v, getPosition());
            }
        }
    }


    public interface ClickListener {
        public void itemClicked(View view, int position);
    }

    public void setClickListener( ClickListener clickListener){
        this.clickListener = clickListener;
    }

}








