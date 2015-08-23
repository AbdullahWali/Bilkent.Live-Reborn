package com.example.figalitaho.bilkentlive;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/*
 * ==========================================s================
 * @Author {Erin Avllazagaj}
 * @Version 1.2
 * ==========================================================
 * This will search for the data in the offering URL.
 * And then will return data accordingly in an ArrayList
 * of InfoHolders.
 * ==========================================================
 * Date: 29/3/2015
 * Version 1.2
 *
 * Date: 2/4/2015
 * Version 1.3
 * */

public class Lectures extends ActionBarActivity implements AdapterView.OnItemClickListener, NavigationDrawerListener  {

    //listview properties
    private ListView listView;
    private CustomAdapterForLectures adapterForLectures;

    //manually have a string of departments
    final String[] departmentNamesList = new String[]{
            "ACC","ADA","AMER","ARCH","BF","BIM","BTE","CAA","CAD",
            "CHEM","CI","CINT","CITE","COMD","CS","CTE","CTIS","CTP",
            "DIR","ECON","EDEB","EE","EEE","ELIT","ELS","EM","EMBA",
            "ENG","ETE","ETS","FA","FRE","FRL","FRP","GE","GER","GIA",
            "GRA","HART","HCIV","HIST","HISTR","HUM","IAED","IE","IR",
            "ITA","JAP","LAUD","LAW","MAN","MATH","MBA","MBG","ME","MIAPP",
            "MSC","MSG","MSN","MTE","MUS","MUSS","NSC","PE","PHIL","PHYS",
            "PNT","POLS","PREP","PSYC","RUS","SFL","SOC","SPA",
            "TE","TEFL","THEA","THM","THR","THS","TRIN","TTP","TURK"};

    //have an array of rows(models for this lecture feature)
    private ModelForLectures[] modelForDepartments = new ModelForLectures[departmentNamesList.length];

    //in departments there is a display and next button
    private Button goButton ;
    private TextView findText;
    private ArrayList<String> listChosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectures);

        SplitAnimation.prepareAnimation(this);
        SplitAnimation.animate(this, 900);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.bringToFront();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


//        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
//                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
//        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);


//        DataSearcher y = new DataSearcher("psyc",0,0);

//        ArrayList<InfoHolder> myStuff = y.findAll();
//        y = new DataSearcher("cs", 0, 0);
//        myStuff.addAll(y.findAll());
//        y = new DataSearcher("math", 0, 0);
//        myStuff.addAll(y.findAll());
//        y = new DataSearcher("econ", 0, 0);
//        myStuff.addAll(y.findAll());
//        y = new DataSearcher("eng", 0, 0);
//        myStuff.addAll(y.findAll());
//        Filter myFilter = new Filter(myStuff);

        ///figo
        findText = (TextView)findViewById(R.id.find_lectures);

        //initialising the list to parse
        listChosen = new ArrayList<String>();

        //create the items of the model using the string array departmentNameList
        createModelItems();

        listView = (ListView) findViewById(R.id.list_View_For_Initial);
        adapterForLectures = new CustomAdapterForLectures(this, modelForDepartments);
        listView.setAdapter(adapterForLectures);

        listView.setOnItemClickListener( this);

        goButton = (Button) findViewById(R.id.go_button);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextIntent = new Intent(Lectures.this, CurrentLectures.class);
                //pass the next arraylist
                listChosen = adapterForLectures.getChosenList();
                nextIntent.putStringArrayListExtra("listChosen", listChosen);
                Lectures.this.startActivity(nextIntent);
            }
        });

//        final ListView list = (ListView) findViewById(R.id.lectures_list);
//        LecturesAdapter adapt = new LecturesAdapter(this, myFilter.makeArray());
//        list.setAdapter(adapt);
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                String location = ((InfoHolder)list.getItemAtPosition(position)).getBuilding();
//                Intent intent = new Intent(Lectures.this , Maps.class);
//                Bundle bundle = new Bundle();
//                bundle.putString( "location", location );
//                //bundle.putBoolean("fromLectures" , true );
//                intent.putExtras( bundle );
//                //SplitAnimation.startActivity(Dinings.this, intent);
//                startActivity( intent );
//
//            }
//        });




    }

    private void createModelItems(){
        int i = 0;
        while( i < modelForDepartments.length){
            //initialise the listview items as unchecked and unselected
            modelForDepartments[i] = new ModelForLectures(departmentNamesList[i], 0, false);
            i++;
        }
    }
    public ArrayList<String> getListChosen() {
        return this.listChosen;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lectures, menu);
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
//            SplitAnimation.startActivity(Lectures.this, new Intent(Lectures.this, About.class), 0);
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void lecturesClicked(int yCoordinate) {
        SplitAnimation.startActivity(Lectures.this, new Intent(Lectures.this, Lectures.class), 20);
    }

    @Override
    public void eventsClicked(int yCoordinate) {
        SplitAnimation.startActivity(Lectures.this, new Intent(Lectures.this, Events.class), 20);
    }

    @Override
    public void diningsClicked(int yCoordinate) {
        SplitAnimation.startActivity(Lectures.this, new Intent(Lectures.this, Dinings.class), 20);
    }

    @Override
    public void transportationsClicked(int yCoordinate) {
        SplitAnimation.startActivity(Lectures.this, new Intent(Lectures.this, Transportations.class), 20);
    }

    @Override
    public void mapsClicked(int yCoordinate) {
//        Intent intent = new Intent(Transportations.this , Maps.class);
//        Bundle bundle = new Bundle();
//        bundle.putString( "location", "DEFAULT" );
//        intent.putExtras( bundle );

        SplitAnimation.startActivity(Lectures.this, new Intent(Lectures.this , LocationPick.class));
    }

}