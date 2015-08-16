package com.example.figalitaho.bilkentlive;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Deell on 5/9/2015.
 */

public class HacksGenerator {

    //Properties
    ArrayList<String> hackList;
    ArrayList<String> usedList;
    String            list;
    StringTokenizer   singleHack;
    Scanner           hackScanner;
    int               alreadyViewed, total;

    public HacksGenerator( InputStreamReader file) {
        hackScanner = new Scanner (file);
        hackList    = new ArrayList<String>();
        usedList = new ArrayList<String>();

        list = hackScanner.nextLine();
        singleHack  = new StringTokenizer(list, ":");

        alreadyViewed = 0;
        total = singleHack.countTokens();

        while(singleHack.hasMoreTokens()){
            hackList.add(singleHack.nextToken());
        }


    }

    public String getHack(){
        String result;
        int    randomNumber;

        result = "Nothing";

        if (alreadyViewed >= total) {
            return resetViewed();
        } else {
            randomNumber = (int) (Math.random() * hackList.size());
            if (hackList.size()>0) {
                result = hackList.remove(randomNumber);
                usedList.add( result);
            }
            alreadyViewed++;
        }
        return result;
    }

    public String resetViewed(){
        hackList = usedList;
        usedList = new ArrayList<String>();
        alreadyViewed = 0;
        String result = null;

        int  randomNumber = (int) (Math.random() * hackList.size());
        if (hackList.size() > 0) {
            result = hackList.remove(randomNumber);
            usedList.add(result);
            alreadyViewed++;
        }
        return result;
    }

}
