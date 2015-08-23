package com.example.figalitaho.bilkentlive;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class Transportations extends ActionBarActivity implements NavigationDrawerListener {

    TextView fromBilkentTime, toBilkentTime, fromBilkentLocation, toBilkentLocation;
    BusSchedule mBusSchedule;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transportations);

        SplitAnimation.prepareAnimation(this);
        SplitAnimation.animate(this, 900);


        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.bringToFront();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

        mBusSchedule = new BusSchedule();

        fromBilkentTime = (TextView)findViewById(R.id.time_fromBilkent);
        toBilkentTime = (TextView)findViewById(R.id.time_toBilkent);
        fromBilkentLocation = (TextView)findViewById(R.id.location_fromBilkent);
        toBilkentLocation = (TextView) findViewById(R.id.location_toBilkent);

        fromBilkentTime.setText(mBusSchedule.nextFromBilkent());
        toBilkentTime.setText(mBusSchedule.nextToBilkent());
        fromBilkentLocation.setText(mBusSchedule.nextToCityLocation());
        toBilkentLocation.setText(mBusSchedule.nextFromCityLocation());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_transportations, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, About.class));
//            SplitAnimation.startActivity(Transportations.this, new Intent(Transportations.this, About.class), 0);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void lecturesClicked(int yCoordinate) {
        SplitAnimation.startActivity(Transportations.this, new Intent(Transportations.this, Lectures.class), 20);
    }

    @Override
    public void eventsClicked(int yCoordinate) {
        SplitAnimation.startActivity(Transportations.this, new Intent(Transportations.this, Events.class), 2);
    }

    @Override
    public void diningsClicked(int yCoordinate) {
        SplitAnimation.startActivity(Transportations.this, new Intent(Transportations.this, Dinings.class), 20);
    }

    @Override
    public void transportationsClicked(int yCoordinate) {
        SplitAnimation.startActivity(Transportations.this, new Intent(Transportations.this, Transportations.class), 20);
    }

    @Override
    public void mapsClicked(int yCoordinate) {
//        Intent intent = new Intent(Transportations.this , Maps.class);
//        Bundle bundle = new Bundle();
//        bundle.putString( "location", "DEFAULT" );
//        intent.putExtras( bundle );

        SplitAnimation.startActivity(Transportations.this, new Intent(Transportations.this , LocationPick.class));
    }

}
