package com.example.figalitaho.bilkentlive;
/*
 * ==========================================================
 * @Author {Erin Avllazagaj}
 * @Version 2.2
 * ==========================================================
 * This reads the whole offerings page and makes a new file 
 * caller myGrabbedWebsite.html almost the same as original
 * ==========================================================
 * Date: 27/4/2015
 * 
 * */
public class OfferingsReader {
    //properties
    private String courseID;
    private HttpsClient scanner;

    public OfferingsReader( String id ){
        courseID = id;
        courseID = courseID.toUpperCase().trim();
    }

    //Gets the current date and month and predicts what user wants to see...
    private static String timeSpecifier(){
        String toReturn = "";
        String monthGetter, yearGetter;
        int month, year;

        //gets the epoch time and converts it to readable string
        long epoch = System.currentTimeMillis()/1000;
        String date = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
                .format(new java.util.Date (epoch*1000));

        //Getting String for year and month
        yearGetter = date.substring(6, 10);
        monthGetter = date.substring(0, 2);

        //Parsing integers from Strings
        year = Integer.parseInt(yearGetter);
        month = Integer.parseInt(monthGetter);

        //filling toReturn String according to dates
        if (month >= 1 && month <= 8){
            year--;
            toReturn += year;
            toReturn += "2";
        }
        else {
            toReturn += year;
            toReturn += "1";
        }
        return toReturn;
    }

    public String getExpected(){
        courseID = courseID.toUpperCase();
        //makes an HttpsClient for a specific url
        scanner = new HttpsClient("https://stars.bilkent.edu.tr/homepage/print/plainOfferings.php?COURSE_CODE="
                + courseID +"&SEMESTER=" + timeSpecifier());
        return scanner.getOfferings();
    }


    //Getter
    protected String getID(){
        return courseID;
    }

    //Setter
    protected void setID( String id ){
        courseID = id;
    }

}