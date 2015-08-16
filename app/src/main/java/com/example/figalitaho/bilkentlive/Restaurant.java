package com.example.figalitaho.bilkentlive;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//@author: Abdullah Wali
//@Class: Restaurant
//@Description: The Class is basically Complete, ( Still need to fix the time validation) 
//Constructing a Restaurant would be done using CSV File
// I'm suspending the rates for now, I see no reason to have them

@SuppressWarnings("rawtypes") // No idea what is this
public class Restaurant  implements Comparable {

	// Properties
	private String name;
	private String address;
	//private byte rate;  // This is currently suspended
	private RestaurantTime[] times;
	// Constructors

	public Restaurant(){ // Default constructor

		name = "DEFAULT";
		address = "DEFAULT";
		//rate = 0;	
		times = new RestaurantTime[] { new RestaurantTime()};
	}


	//This is the CSV File Instructor, and example CSV is in the directory, check that out
	public Restaurant ( BufferedReader csv) throws IOException{
		String name, address ;
		int[] openTime0 , openTime1 , openTime2 , closeTime0 , closeTime1 , closeTime2;
		CSVReader reader = new CSVReader ( csv ,',' , '"');
		RestaurantTime time1 , time2 , time3 ;
		
		name = reader.readNext()[0];
		address = reader.readNext()[0];
		
		openTime0 =  toIntArray ( reader.readNext());
		openTime1 =  toIntArray ( reader.readNext());
		openTime2 =  toIntArray ( reader.readNext());
		closeTime0 = toIntArray ( reader.readNext());
		closeTime1 = toIntArray ( reader.readNext());
		closeTime2 = toIntArray ( reader.readNext());
		reader.close();
		
		time1 = new  RestaurantTime ( openTime0 , closeTime0);
		time2 = new RestaurantTime ( openTime1 , closeTime1);
		time3 = new RestaurantTime ( openTime2, closeTime2 );
		RestaurantTime[] times = new RestaurantTime[] { time1, time2, time3};
		
		this.name = name;
		this.address = address;
		this.times = times;
		
	}

	//Returns 0,1, or 2 depending on what day is it. To be used when checking isOpen()
	public static int getDayInstance() {
		String day;
		SimpleDateFormat sdt = new SimpleDateFormat("EEEE");
		day =  sdt.format( new Date());
        switch (day) {
            case "Saturday": return 1;
            case "Sunday": return 2;
            default: return 0;

        }
	}

	// Methods
	
	
	public boolean isOpen(){
		return times[ getDayInstance()].isDuringTime();
	}

	
	
	private static int[] toIntArray ( String[] str) { 
		int[] result = new int[ str.length ];
		for ( int i = 0; i < str.length; i++ ) {
			result[i] = Integer.parseInt(str[i]);
		}
		return result;
	}


	//Getters/Setters
	/////////////////////////////////////////
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public RestaurantTime[] getTimes() {
		return times;
	}


	public void setTimes(RestaurantTime[] times) {
		this.times = times;
	}
	////////////////////////////////////////////


	@Override
	public int compareTo(Object o ) {
		if ( this.isOpen() && !((Restaurant)o).isOpen() )
			return -1;
		else if ( !this.isOpen() && ((Restaurant)o).isOpen())
			return 1;
		else if ( this.isOpen() && ((Restaurant)o).isOpen())
			return this.getName().compareTo(((Restaurant)o).getName());
		return 0;
	}


	@Override
	public String toString() {
		return "\n\nName: " + getName() + "\nAddress: " + getAddress() + "\nTimes :\n" + "Weekdays: " + getTimes()[0] +
				"\nSaturday: " + getTimes()[1] + "\nSunday: " + getTimes()[2] + "\nIs Open: " + isOpen() ;
	}

}