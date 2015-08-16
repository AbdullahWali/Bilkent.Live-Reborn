package com.example.figalitaho.bilkentlive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Dinings extends ActionBarActivity implements NavigationDrawerListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinings);

        SplitAnimation.prepareAnimation(this);
        SplitAnimation.animate(this, 900);


        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.bringToFront();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);


        RestaurantList restoList = null;
        final ListView restoView = (ListView) findViewById(R.id.restoListView);
        Restaurant[] restaurants ;
        Integer[] restoStatesArray;
        RestoListAdapter adapter;

        //Initializing the restoList and Sorting it
        try {

            InputStream is = getAssets().open("RestoList.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            restoList = new RestaurantList(reader, getAssets());
            restoList.sortRestos();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Initialize restoNames and states
        restaurants = new Restaurant[ restoList.restos.size()];
        for (int i = 0; i < restoList.restos.size(); i++)
            restaurants [i] = restoList.restos.get(i);

        restoStatesArray = new Integer[restoList.restos.size()];
        for (int i = 0; i < restoStatesArray.length; i++) {
            if (restoList.restos.get(i).isOpen())
                restoStatesArray[i] = R.drawable.open;
            else if (!restoList.restos.get(i).isOpen())
                restoStatesArray[i] = R.drawable.closed;
        }


        //Initializing the Adapter and adding Data to the ListView
        adapter = new RestoListAdapter(this, restaurants,
                restoStatesArray);
        restoView.setAdapter(adapter);

        //Add Buttons
        restoView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String location = ((Restaurant)restoView.getItemAtPosition(position)).getAddress();
                Intent intent = new Intent(Dinings.this , Maps.class);
                Bundle bundle = new Bundle();
                bundle.putString( "location", location );
                bundle.putBoolean("fromDinings" , true );
                intent.putExtras( bundle );


//                SplitAnimation.startActivity(Dinings.this, intent);
                startActivity( intent );

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void lecturesClicked(int yCoordinate) {
        SplitAnimation.startActivity(Dinings.this, new Intent(Dinings.this, Lectures.class), 20);
    }

    @Override
    public void eventsClicked(int yCoordinate) {
        SplitAnimation.startActivity(Dinings.this, new Intent(Dinings.this, Events.class), 20);
    }

    @Override
    public void diningsClicked(int yCoordinate) {
        SplitAnimation.startActivity(Dinings.this, new Intent(Dinings.this, Dinings.class), 20);
    }

    @Override
    public void transportationsClicked(int yCoordinate) {
        SplitAnimation.startActivity(Dinings.this, new Intent(Dinings.this, Transportations.class), 20);
    }

    @Override
    public void mapsClicked(int yCoordinate) {
        Intent intent = new Intent(Dinings.this , Maps.class);
        Bundle bundle = new Bundle();
        bundle.putString( "location", "DEFAULT" );
        intent.putExtras( bundle );

        SplitAnimation.startActivity(Dinings.this, intent, 0);
    }

}