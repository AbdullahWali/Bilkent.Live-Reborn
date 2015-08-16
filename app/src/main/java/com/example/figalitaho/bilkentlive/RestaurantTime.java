package com.example.figalitaho.bilkentlive;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;


public class RestaurantTime {
	//Time is entered in the String in 24 hour format, eg 3:30 PM is : 1330
	//Number of parameters for open Time[] and Close Time[] should be the same
	int openTime[];
	int closeTime[];


	//Default Constructor, Open 24 hours
	public RestaurantTime (){
		openTime = new int[] {0000};
		closeTime = new int[] {2359};
	}
	public RestaurantTime ( int[] openTime, int[] closeTime) {
		if ( dateValid(openTime))
			this.openTime = openTime;
		if (dateValid(closeTime))
			this.closeTime = closeTime;

	}

	// This method checks the current local time and converts it to HH format, and returns whether
	// that time is in the interval of one of the opening times or not
	public boolean isDuringTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
		int now  = Integer.parseInt( sdf.format(new Date()) );
        for ( int i = 0; i < openTime.length; i++ )
			if ( now >= openTime[i] && now <= closeTime[i])
				return true;
		return false;
	}

	//Checks if the Time is in the right format
	// Problems in Code: This doesn't check if minutes are valid ( you can have 1389 as a time which is illogical)
	private static boolean dateValid(int[] time ) { 
		for ( int i = 0 ; i < time.length; i++) 
			if (time[i] >2400 || time[i] < 0 ) 
				return false;
		return true;		
	}

	//Getters/Setters
	///////////////////////////////
	public int[] getOpenTime() {
		return openTime;
	}
	public void setOpenTime(int[] openTime) {
		this.openTime = openTime;
	}
	public int[] getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(int[] closeTime) {
		this.closeTime = closeTime;
	}
	/////////////////////////////////


	//Returns opening times in String
	public String toString() {
		String result = "";
		for ( int i= 0 ; i < openTime.length; i++ ){
			if ( openTime[i] == 0 && closeTime[i] == 0)
				result += "Closed";
			else result += timeToText(openTime[i]) + " -- " + timeToText(closeTime[i]) + "\n";
		}
		return result;
	}

	private String timeToText( int time ) { 
		String result ="";
		String temp;
		temp = Integer.toString(time/100);
		if (temp.length() == 1 )  // This is just to avoid a time format like 7:0  instead of 07:00
			temp = "" + 0 + temp;
		
		result += temp;
		
		temp = Integer.toString(time%100);
		if (temp.length() == 1 )
			temp = "" + 0 + temp;
		
		result += ":" + temp;
		
		return result;
	}
}
