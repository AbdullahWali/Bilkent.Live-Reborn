package com.example.figalitaho.bilkentlive;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by Figali Taho on 26/04/2015.
 * <p/>
 * This class will be used to parse the XML of Events page
 */
public class XMLInfoParser {


    //getTheStringToParse
    String eventsXMLText = Events.getStringBuilder();
    //create  new event list
    private ArrayList<Event> listOfEvents;

    //constructor
    // initialise the event list here
    public XMLInfoParser() {
        listOfEvents = new ArrayList<Event>();
    }

    //return the listview
    public ArrayList<String> getListOfEvents() {

        sortEvents();
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < listOfEvents.size(); i++) {
            result.add(listOfEvents.get(i).toString());
        }
        return result;
    }

    //creates the events
    public void createEventAndAdd() {
        if (eventsXMLText != null) {
            //parse it

            String title = "";
            String day = "";
            String month = "";
            String hour = "";
            String mins = "";
            String place = "";
            String s = eventsXMLText;

            while (s.indexOf("<event>") != -1) {

                if (s.indexOf("<title>") != -1)
                    title = s.substring(s.indexOf("<title>") + 7, s.indexOf("</title>"));
                if (s.indexOf("<day>") != -1)
                    day = s.substring(s.indexOf("<day>") + 5, s.indexOf("</day>"));
                if (s.indexOf("<month>") != -1)
                    month = s.substring(s.indexOf("<month>") + 7, s.indexOf("</month>"));
                if (s.indexOf("<hour>") != -1)
                    hour = s.substring(s.indexOf("<hour>") + 6, s.indexOf("</hour>"));
                if (s.indexOf("<mins>") != -1)
                    mins = s.substring(s.indexOf("<mins>") + 6, s.indexOf("</mins>"));
                if (s.indexOf("<place>") != -1)
                    place = s.substring(s.indexOf("<place>") + 7, s.indexOf("</place>"));
                if (s.indexOf("</event>") != -1)
                    s = s.substring(s.indexOf("</event>") + 8);
                if (title != "" && day != "" && month != "" && hour != "" && mins != "" && place != "") {
                    Event event;
                    event = new Event(title, Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt(hour), Integer.parseInt(mins), place);
                    listOfEvents.add(event);

                }
            }


            int i = 0;
            while (i < listOfEvents.size()) {
                Log.d("Progress", listOfEvents.get(i).toString());
                i++;
            }

        }
    }

    public void sortEvents() {
        ArrayList<Event> list = listOfEvents;

        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String month = sdf.format(new Date());

        Iterator<Event> i = list.iterator();
        while (i.hasNext()) {
            Event s = i.next();
            if (Integer.parseInt(month) > s.getMonth())
                i.remove();
        }


        sdf = new SimpleDateFormat("d");
        String today = sdf.format(new Date());


        Iterator<Event> dayIterate = list.iterator();
        while (dayIterate.hasNext()) {
            Event s = dayIterate.next();
            if (s.getMonth() == Integer.parseInt(month))
                if (Integer.parseInt(today) > s.getDay())
                dayIterate.remove();
        }

        Collections.sort(listOfEvents);

    }
}


