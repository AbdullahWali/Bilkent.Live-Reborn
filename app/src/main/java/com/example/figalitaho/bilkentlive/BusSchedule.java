package com.example.figalitaho.bilkentlive;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Abdullah And Figo on 27/04/2015.
 */
public class BusSchedule {

    String[] fromCityWeekend, fromCityWeekday, toCityWeekend, toCityWeekday;
    int[] fromCityWeekendTime, fromCityWeekdayTime , toCityWeekendTime , toCityWeekdayTime;

    public BusSchedule() {
        //==========================================================
        // Non-Summer Schedule
        //===========================================================

//        fromCityWeekendTime = new int[]{800, 900, 1000, 1100, 1200, 1300, 1400, 1500, 1600, 1700, 1800, 1900, 2000, 2100 , 2200, 2300,0000,130};
//        fromCityWeekdayTime = new int[] {750, 800, 840, 940, 1040, 1140, 1240, 1340, 1440, 1540, 1640, 1800, 1900, 2000, 2100, 2200, 2300, 2400 };
//        toCityWeekendTime = new int[] {850, 950, 1050, 1150, 1250, 1350, 1450, 1550, 1650, 1750, 1850, 1950, 2050, 2150, 2250,2350};
//        toCityWeekdayTime = new int[] { 720, 850, 950, 1050, 1150, 1250, 1350, 1450, 1550,1650, 1750, 1850, 1950, 2050, 2150, 2250,2350 };
//
//
//        fromCityWeekend = new String[] { "Tunus - Sihhiye", "Tunus", "Tunus", "Tunus", "Tunus", "Tunus", "Tunus", "Sihhiye", "Tunus", "Tunus", "Tunus", "Sihhiye", "Tunus", "Tunus", "Tunus", "Tunus", "Tunus","Tunus"};
//        fromCityWeekday = new String[] {"Tunus - Sihhiye", "Bahçeli", "Tunus - Sihhiye- Bahçeli", "Tunus - Sihhiye", "Tunus", "Tunus","Tunus - Sihhiye","Tunus","Tunus - Sihhiye","Tunus","Tunus - Sihhiye", "Tunus", "Tunus", "Tunus", "Tunus - Sihhiye","Tunus","Tunus", "Tunus" };
//        toCityWeekend = new String[] { "Sihhiye", "Tunus", "Tunus", "Tunus", "Tunus", "Tunus", "Tunus", "Sihhiye","Tunus" ,"Tunus", "Tunus", "Tunus", "Tunus", "Tunus", "Tunus", "Tunus"};
//        toCityWeekday = new String[] { "Tunus", "Tunus - Sihhiye", "Tunus", "Tunus - Sihhiye", "Tunus", "Tunus - Sihhiye", "Tunus", "Tunus", "Tunus - Sihhiye", "Tunus - Sihhiye", "Tunus - Sihhiye", "Tunus - Sihhiye", "Tunus - Sihhiye", "Tunus - Sihhiye", "Tunus", "Tunus", "Tunus"};
        //=============================================================


        //=============================================================
        //Summer Schedule
        //=============================================================
        fromCityWeekendTime = new int[]{800, 900, 1000, 1100, 1200, 1300, 1400, 1500, 1600, 1700, 1800, 1900, 2000, 2100 , 2200, 2300,0000,130 };
        fromCityWeekdayTime = new int[] {800, 900, 1000, 1100, 1200, 1300, 1400, 1500, 1600, 1700, 1800, 1900, 2000, 2100, 2200, 2300, 2400 };
        toCityWeekendTime = new int[] {850, 950, 1050, 1150, 1250, 1350, 1450, 1550, 1650, 1750, 1850, 1950, 2050, 2150, 2250,2350 };
        toCityWeekdayTime = new int[] {850, 950, 1050, 1150, 1250, 1350, 1450, 1550, 1650, 1750, 1850, 1950, 2050, 2150, 2250,2350 };


        fromCityWeekend = new String[] { "Tunus - Sihhiye", "Tunus", "Tunus", "Tunus", "Tunus", "Tunus", "Tunus", "Sihhiye", "Tunus", "Tunus", "Tunus", "Sihhiye", "Tunus", "Tunus", "Tunus", "Tunus", "Tunus", "Tunus"};
        fromCityWeekday = new String[] { "Tunus - Sihhiye", "Tunus", "Tunus", "Tunus", "Tunus", "Tunus", "Tunus", "Sihhiye", "Tunus", "Tunus", "Tunus", "Sihhiye", "Tunus", "Tunus", "Tunus", "Tunus", "Tunus", };
        toCityWeekend = new String[] { "Sihhiye", "Tunus", "Tunus", "Tunus", "Tunus", "Tunus", "Tunus", "Tunus" ,"Tunus", "Tunus", "Tunus", "Tunus", "Tunus", "Tunus", "Tunus",  "Tunus"};
        toCityWeekday = new String[] { "Sihhiye", "Tunus", "Tunus", "Tunus", "Tunus", "Tunus", "Tunus", "Tunus" ,"Tunus", "Tunus", "Tunus", "Tunus", "Tunus", "Tunus", "Tunus",  "Tunus" };


    }

    public static boolean isWeekend() {
        String day;
        SimpleDateFormat sdt = new SimpleDateFormat("EEEE");
        day =  sdt.format( new Date());
        switch (day) {
            case "Saturday": return true;
            case "Sunday": return true;
            default: return false;
        }
    }

    public String nextFromBilkent() {
        Log.d ( BusSchedule.class.getSimpleName(), fromCityWeekendTime.length +" " + fromCityWeekdayTime.length + " " + toCityWeekendTime.length + " " + toCityWeekdayTime.length
                + "\n" + fromCityWeekend.length + " " + fromCityWeekday.length + " " + toCityWeekend.length + " " +toCityWeekday.length );

        SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
        int now  = Integer.parseInt( sdf.format(new Date()) );
        if (isWeekend())
            for (int i = 0; i <toCityWeekendTime.length; i++ ) {
                if ( now <toCityWeekendTime[i])
                    return returnDifference(toCityWeekendTime[i]);
            }
        else  for (int i = 0; i <toCityWeekdayTime.length; i++ )
                    if ( now <toCityWeekdayTime[i])
                        return returnDifference(toCityWeekdayTime[i]);
        return "Bus leaving";
    }

    public String nextToBilkent() {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
        int now  = Integer.parseInt( sdf.format(new Date()) );
        if (isWeekend())
            for (int i = 0; i <fromCityWeekendTime.length; i++ ) {
                if (now <fromCityWeekendTime[i])
                    return returnDifference(fromCityWeekendTime[i]);
            }
        else  for (int i = 0; i <fromCityWeekdayTime.length; i++ )
            if ( now <fromCityWeekdayTime[i])
                return returnDifference(fromCityWeekdayTime[i]);
        return "Bus leaving";
    }

    public String nextFromCityLocation() {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
        int now = Integer.parseInt(sdf.format(new Date()));
        if (isWeekend())
            for (int i = 0; i < fromCityWeekendTime.length; i++) {
                if ( now < fromCityWeekendTime[i])
                    return fromCityWeekend[i];
            }
        else for (int i = 0; i < fromCityWeekdayTime.length; i++)
            if ( now < fromCityWeekdayTime[i])
                return fromCityWeekday[i];
    return null;
    }

    public String nextToCityLocation() {

        SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
        int now = Integer.parseInt(sdf.format(new Date()));
        if (isWeekend())
            for (int i = 0; i < toCityWeekendTime.length; i++) {
                if ( now < toCityWeekendTime[i])
                    return toCityWeekend[i];
            }
        else for (int i = 0; i < toCityWeekdayTime.length; i++)
            if ( now < toCityWeekdayTime[i ])
                return toCityWeekday[i ];
        return null;

    }



    public String returnDifference( int time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
        int now  = Integer.parseInt( sdf.format(new Date()) );


        String mins;
        String hours;
        if ( ((time%100) - ( now%100)) > 0 ) {
            mins = timeToText(Math.abs((time % 100) - (now % 100)));
            Log.d("Progress", "created mins" + mins);
            hours = timeToText(Math.abs((time / 100) - (now / 100)));
        }
        else {
            mins = timeToText(Math.abs((60 - now % 100) + time % 100));
            hours = timeToText(Math.abs(((time / 100) - (now / 100)) - 1));
        }
        Log.d("Progress","created hours" + hours);
        String timeLeft = hours + ":" + mins;

        Log.d("Progress","created timeleft" + timeLeft);
        return timeLeft;

    }

    private String timeToText( int time ) {
        String temp;
        temp = Integer.toString(time);
        if (temp.length() == 1 )  // This is just to avoid a time format like 7:0  instead of 07:00
            temp = "" + 0 + temp;

        return temp;
    }
}
