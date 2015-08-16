package com.example.figalitaho.bilkentlive;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.widget.ImageView;


public class FragmentLectures extends Fragment {

    ImageView lecturesImage;
    MenuListener activityCommander;

//    public interface MenuListener{
//        public void menuClicked();
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_lectures, container, false);

        lecturesImage = (ImageView) view.findViewById(R.id.lecturesImage);

        lecturesImage.setOnClickListener(new clickHandler());

        return view;
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            activityCommander = (MenuListener) activity;
        } catch (ClassCastException e){
            throw new ClassCastException(activity.toString());
        }
    }

    class clickHandler implements View.OnClickListener {
        public void onClick(View v) {
            if (v.getId() == R.id.lecturesImage) {
//              SplitAnimation.startActivity(FragmentLectures.this, new Intent(Home.this, Lectures.class));
                activityCommander.lecturesClicked();
            }

        }
    }

}
