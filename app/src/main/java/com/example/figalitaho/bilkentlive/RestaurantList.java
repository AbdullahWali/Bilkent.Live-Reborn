package com.example.figalitaho.bilkentlive;
//@author: Abdullah Wali
//@Class: RestaurantList
//@Description: In this Class we'll be managing the list of restaurants
// I will make a constructor that creates a list out of a collection of txt files ( CSV formatted )
// Methods would Include Sort and others.. ( no idea what are others yet :p ) 



import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;


public class RestaurantList {
	
	ArrayList<Restaurant> restos = new ArrayList<Restaurant>();
	
	//The Constructor will take a csv file containing the filenames of all restaurant csv files
	// then it will iterate through each of them and create a restaurant from each restaurant csv file
	public RestaurantList ( BufferedReader csv, AssetManager assets) throws IOException {
        String next;
		String url;
		while (( next = csv.readLine()) != null ) {
			url = "Restos_Files/" + next + ".txt";
            InputStream is = assets.open(url);
			restos.add( new Restaurant ( new BufferedReader(new InputStreamReader( is))));
		}
	}
	
	@Override
	public String toString() { 
		String result = "";
		for ( int i = 0 ; i < restos.size(); i++ ) 
			result += restos.get(i) +"\n-------------------------";
		return result;
	}

	// This Will sort restaurants According to  I. Open/ Close state II. Alphabetic Order of Names
	@SuppressWarnings("unchecked")
	public void sortRestos() { 
		Collections.sort(restos);
	}
}
