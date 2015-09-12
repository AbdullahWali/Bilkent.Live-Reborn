package com.example.figalitaho.bilkentlive;

import java.util.ArrayList;

// Each Station is a stop in the main campus ring Bus schedule
// Each station has a set of times
public class Station {
	
	String name; // station name
	ArrayList< Integer> times = new ArrayList<Integer> () ; // Times
	
	public Station ( String name ) {
		this.name = name;
	}
	
	
	public void addTime ( String i ) {
		i = i.replaceAll(":","");
		times.add( Integer.parseInt(i) ) ;
	}
	
	public String toString()  {
		return name + times.toString() + "\n";
	}

}
