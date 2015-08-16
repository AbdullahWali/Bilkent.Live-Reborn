package com.example.figalitaho.bilkentlive;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Events extends ActionBarActivity  implements NavigationDrawerListener {

    static StringBuilder sb;
    ListView mainListView;
    ArrayAdapter mArrayAdapter;
    ArrayList<String> mEventsList;
    XMLInfoParser eventsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        SplitAnimation.prepareAnimation(this);
        SplitAnimation.animate(this, 900);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.bringToFront();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

        //-----------------------------------------------------------------------------------------
        //getting the info from the web xml
        //-----------------------------------------------------------------------------------------
        /*
         * This piece of code is needed to make possible the reading from the xml remote file
         */
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        sb = null;
        BufferedInputStream bis = null;
        URL url = null;

        //tries to assign an URL string to url variable
        try {

            url = new URL("http://erin.hostei.com/events.xml");
            bis = new BufferedInputStream(url.openStream());

            //create a new byte array to latere pass to a string that will be appended to StringBilder object
            byte[] buffer = new byte[1024];

            //initialize StringBuilder object
            sb = new StringBuilder();

            int bytesRead = 0;
            bytesRead = bis.read(buffer);
            while (bytesRead > 0) {
                String text = new String(buffer, 0, bytesRead);
                sb.append(text);
                bytesRead = bis.read(buffer);
            }
            bis.close();

            //-----------------------------------------------------------------------------------------
            //getting the info from the web xml
            //-----------------------------------------------------------------------------------------------------------------

            //parse the text, and fill the arraylist with events
            eventsList = new XMLInfoParser();
            eventsList.createEventAndAdd();

            mEventsList = eventsList.getListOfEvents();

            mainListView = (ListView) findViewById(R.id.main_listView);
            //create an array adapter for the listview
            mArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mEventsList);
            mainListView.setAdapter(mArrayAdapter);



        }
         catch( MalformedURLException e ){
            e.printStackTrace();
        }catch( Exception e ){
            e.printStackTrace();
        }

    }

    public static String getStringBuilder(){
        return sb.toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_events, menu);
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
//            SplitAnimation.startActivity(Events.this, new Intent(Events.this, About.class), 0);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void lecturesClicked(int yCoordinate) {
        SplitAnimation.startActivity(Events.this, new Intent(Events.this, Lectures.class), 20);
    }

    @Override
    public void eventsClicked(int yCoordinate) {
        SplitAnimation.startActivity(Events.this, new Intent(Events.this, Events.class), 20);
    }

    @Override
    public void diningsClicked(int yCoordinate) {
        SplitAnimation.startActivity(Events.this, new Intent(Events.this, Dinings.class), 20);
    }

    @Override
    public void transportationsClicked(int yCoordinate) {
        SplitAnimation.startActivity(Events.this, new Intent(Events.this, Transportations.class), 20);
    }

    @Override
    public void mapsClicked(int yCoordinate) {
        Intent intent = new Intent(Events.this , Maps.class);
        Bundle bundle = new Bundle();
        bundle.putString( "location", "DEFAULT" );
        intent.putExtras( bundle );

        SplitAnimation.startActivity(Events.this, intent);
    }
}
