package com.example.figalitaho.bilkentlive;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RingSchedule {
	
	ArrayList <Station> stations = new ArrayList< Station> () ;
	
	public RingSchedule( File input ) throws IOException  {
		
			// Read the HTML Code from File
	        // The File was retrieved from the page with everything but the Table removed
			//File input = new File("File.htm");
	        Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");
	        Elements rows = doc.select("tr");
	        
	
	        // Go through the First Row and add the stations to the schedule
	        for ( int i = 0 ; i < rows.get(0).children().size(); i++ ) {
	        	addStation(rows.get(0).children().get(i).text());
	        }
	       
	        
	        // Go through Other rows and add times to each station
	        // Starts from 1 so it would skip the Headers row
	        for ( int i = 1 ; i < rows.size() ; i++ ) {
	        	for ( int j = 0 ; j < rows.get(i).children().size(); j++ ) 
	        			getStation(j).addTime(rows.get(i).children().get(j).text());
	        }
	}


	public void addStation ( String str ) { 
		stations.add(new Station ( str ) ) ;
	}




	// Return Station Through index or through name
	public Station getStation ( int i ) {
		return stations.get(i);
	}

	public Station getStation ( String stationName ) {
		for ( int i = 0 ; i < stations.size(); i ++ )
			if  ( stationName == stations.get(i).name)
				return stations.get(i);

		return null;


	}

	public String getNextBus ( String stationName ) {

		SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
		int now = Integer.parseInt(sdf.format(new Date()));

		//If on weekday, Check the Station Times and return the next least time difference
		if ( !BusSchedule.isWeekend()) {
			for (int i = 0 ; i < getStation(stationName).times.size() ; i++ )
				if ( now < getStation(stationName).times.get(i))
					return BusSchedule.returnDifference(getStation(stationName).times.get(i));
		}

		return "No Ring Buses On Weekends";
	}

}