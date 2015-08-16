package com.example.figalitaho.bilkentlive;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Home extends FragmentActivity implements MenuListener, Animation.AnimationListener{

    //==============================================================================================
    //  Properties
    //==============================================================================================
    TextView       lifeHacksText;
    ViewPager      viewPager = null;
    Animation      animationFade;
    HacksGenerator hacksGenerator;

    //==============================================================================================
    //  Constuctor / OnCreate Method
    //==============================================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewPager = (ViewPager) findViewById(R.id.swipeViewPager);
        FragmentManager fragmentManager = getSupportFragmentManager();
        PagerAdapter    pagerAdapter    = new PagerAdapter(fragmentManager);
        viewPager.setAdapter(pagerAdapter);

        lifeHacksText  = (TextView) findViewById( R.id.lifeHacksText);
        hacksGenerator = createHacksGenerator();
        animationFade  = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        lifeHacksText.setText(hacksGenerator.getHack());
        lifeHacksText.setOnClickListener(new hacksListener());
    }

    //==============================================================================================
    //  Methods for the functionality and animation of Life Hacks
    //==============================================================================================

    public HacksGenerator createHacksGenerator(){
        AssetManager am;
        HacksGenerator hacks;
        InputStream file;

        try {
            am    = getAssets();
            file  = am.open("hacksFile.txt");
            hacks = new HacksGenerator( new InputStreamReader(file));
        }catch(IOException e) {
            lifeHacksText.setText("Incorrect file");
            hacks = null;
        }
        return hacks;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Toast.makeText(getBaseContext(), "Animation Stopped!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    class hacksListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            lifeHacksText.setVisibility(View.VISIBLE);
            lifeHacksText.setText(hacksGenerator.getHack());
            lifeHacksText.startAnimation(animationFade);
        }
    }

    //==============================================================================================
    //  Giving the functionality to the buttons to change the activity with the splitAnimation
    //==============================================================================================
    @Override
    public void lecturesClicked() {
        SplitAnimation.startActivity(Home.this, new Intent(Home.this, Lectures.class));
    }

    @Override
    public void eventsClicked() {
        SplitAnimation.startActivity(Home.this, new Intent(Home.this, Events.class));
    }

    @Override
    public void diningsClicked() {
        SplitAnimation.startActivity(Home.this, new Intent(Home.this, Dinings.class));
    }

    @Override
    public void transportationsClicked() {
        SplitAnimation.startActivity(Home.this, new Intent(Home.this, Transportations.class));
    }

    @Override
    public void mapsClicked() {
        Intent intent = new Intent(Home.this , Maps.class);
        Bundle bundle = new Bundle();
        bundle.putString( "location", "DEFAULT" );
        intent.putExtras( bundle );
        SplitAnimation.startActivity(Home.this, intent);
    }

}
