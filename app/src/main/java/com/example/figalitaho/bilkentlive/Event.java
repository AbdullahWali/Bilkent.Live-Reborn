package com.example.figalitaho.bilkentlive;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Figali Taho on 26/04/2015.
 *
 * Hour and minute display with NumberFormat: Erin Avllazagaj
 *
 * This class represents an individual event
 */
public class Event implements Comparable{

    private String title;
    private int day;
    private int month;
    private int hour;
    private int mins;
    private String place;

    /**
     * constructor
     */
    public Event( String title, int day, int month, int hour, int mins, String place){
        this.title = title;
        this.day = day;
        this.month = month;
        this.hour = hour;
        this.mins = mins;
        this.place = place;
    }
    /**
     * getters and setters for events' things
     */
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMins() {
        return mins;
    }

    public void setMins(int mins) {
        this.mins = mins;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        DecimalFormat f = new DecimalFormat("00");
        f.format(hour);
        String yearnow = sdf.format(new Date());
        if( hour > 1)
            return title + "\n" + day+"/"+month+"/"+yearnow + "\nTime: " + f.format(hour) +":"+ f.format(mins) +"\nLocation: " + place;
        else return title + "\n" + day+"/"+month+"/"+yearnow + "\nDuration: " + hour +" hours & "+ mins+" minutes\nLocation: " + place;
    }

    @Override
    public int compareTo(Object another) {
        if( this.getDay() > ((Event)another).getDay())
            return -1;
        if (this.getDay() < ((Event)another).getDay())
            return 1;
        else return 0;
    }
}