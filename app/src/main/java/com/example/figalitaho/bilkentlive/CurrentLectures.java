package com.example.figalitaho.bilkentlive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CurrentLectures extends ActionBarActivity implements AdapterView.OnItemClickListener{


    ListView listView ;
    ArrayAdapter adapter;
    ArrayList<String> filteredResultList;
    String[] filterResult;

    TextView coursesNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_lectures);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.bringToFront();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        coursesNow  = (TextView)findViewById(R.id.textView);

        Intent i = getIntent();
        filteredResultList = i.getStringArrayListExtra("listChosen");
        filterResult = new String[filteredResultList.size()];

        //initial stuff added
        Filter myFilter = null;
        ArrayList<String> all = null;
        if( filteredResultList != null){

            DataSearcher y = new DataSearcher(filteredResultList.get(0));
            ArrayList<InfoHolder> myStuff = new ArrayList<InfoHolder>();
            myStuff.addAll(y.findAll());
            //adds more stuff if chosen
            for( int a = 1; a < filteredResultList.size(); a++){
                y = new DataSearcher(filteredResultList.get(a).toString(), 0, 0);
                myStuff.addAll(y.findAll());
            }
            myFilter = new Filter(myStuff);
            all = new ArrayList<String>();
            all = myFilter.makeArrayList();
        }

        String[] array = new String[all.size()];
        for( int s = 0; s < all.size(); s++){
            array[s] = all.get(s);
            Log.d("DebuggerMe", array[s]);
        }



        listView = (ListView)findViewById(R.id.listView);
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, array );
        listView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_current_lectures, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
