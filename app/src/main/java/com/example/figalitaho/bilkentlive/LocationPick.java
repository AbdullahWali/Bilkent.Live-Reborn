package com.example.figalitaho.bilkentlive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class LocationPick extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_pick);

        SplitAnimation.prepareAnimation(this);
        SplitAnimation.animate(this, 900);


        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.bringToFront();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        String[] locations = null ;

        try {

            // Read the Location List from the File
            InputStream is = getAssets().open("locNames.txt");
            BufferedReader reader = new BufferedReader( new InputStreamReader(is));

            //Add Locations to a list and sort them
            String str;
            ArrayList<String> list = new ArrayList<String>();
            while((str = reader.readLine()) != null){
                list.add(str);
            }
            Collections.sort(list);
            // Add the 2 main Entries at the Beginning
            list.add(0, "Bilkent University Main Campus");
//            list.add(1, "Bilkent University East Campus");

            //Convert the ArrayList to an Array for use with Adapter
            locations = new String[list.size()];
            locations =list.toArray( new String[list.size()]);

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Create the List View
        final ListView locationList= (ListView) findViewById(R.id.locations_listview);
        ArrayAdapter<String>  adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 ,locations );
        locationList.setAdapter(adapter);

        locationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String location = locationList.getItemAtPosition(i).toString();
                Intent intent = new Intent(LocationPick.this , Maps.class);
                Bundle bundle = new Bundle();
                bundle.putString( "location", location );
                bundle.putBoolean("fromDinings", true);
                intent.putExtras(bundle);
                startActivity(intent);
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
}
