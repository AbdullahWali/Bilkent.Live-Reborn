package com.example.figalitaho.bilkentlive;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
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

        if (i==0){
            fragment = new FragmentLectures();
        }
        if (i==1){
            fragment = new FragmentEvents();
        }
        if (i==2){
            fragment = new FragmentDinings();
        }
        if (i==3){
            fragment = new FragmentTransportations();
        }
        if (i==4){
            fragment = new FragmentMaps();
        }


        return fragment;
    }

    @Override
    public int getCount() {
        return 5;
    }


}
