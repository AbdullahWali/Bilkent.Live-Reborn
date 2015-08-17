package com.example.figalitaho.bilkentlive;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Deell on 5/7/2015.
 */
public class PagerAdapter extends FragmentPagerAdapter{

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;


        //===================================================================================
        //Commented these out as these features are unimplemented yet
        //=================================================================================
//        if (i==3){
//            fragment = new FragmentLectures();
//        }
//        if (i==4){
//            fragment = new FragmentEvents();
//        }
        //================================================================================


        if (i==0){
            fragment = new FragmentTransportations();
        }
        if (i==1){
            fragment = new FragmentDinings();
        }
        if (i==2){
            fragment = new FragmentMaps();
        }


        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }


}
