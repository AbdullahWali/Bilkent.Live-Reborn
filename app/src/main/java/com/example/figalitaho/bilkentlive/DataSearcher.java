package com.example.figalitaho.bilkentlive;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * ==========================================================
 * @Author {Erin Avllazagaj}
 * @Version 1.8
 * ==========================================================
 * This will search for the data in the offering URL.
 * And then will return data accordingly in an ArrayList
 * of InfoHolders.
 * ==========================================================
 * Date: 23/3/2015
 * Version: 2.0
 *
 * Date: 2/4/2015
 * Version: 2.4
 *
 * Date 11/5/2015
 * Version 2.5
 * Changes:"fixed bug in parsing lectures using <tr>, now it uses <tbody>"
 * */

/**
 * Created by Figali Taho on 09/05/2015.
 */
public class DataSearcher {

    private String websiteContents, id;
    private int section, grade;
    private int lastIndex = 100;
    private boolean hasMore = true;
    private ArrayList<InfoHolder> allInfo = new ArrayList<InfoHolder>();
    private OfferingsReader scanner;

    private int indexForFullSearch;

    //by default can select all courses
    public DataSearcher(String id) {
        this( id, 0,0);
    }

    public DataSearcher(String id,int g, int sec) {

        id = id.toUpperCase();
        section = sec;
        this.id = id;
        grade = g;

        scanner = new OfferingsReader(id);
        websiteContents = scanner.getExpected();
        lastIndex = websiteContents.indexOf("<tbody>",600);
        indexForFullSearch = lastIndex;

    }

    public ArrayList<InfoHolder> findAll(){
        if( section != 0 && grade != 0){
            allInfo.add( singleSearch(grade,section));
        }

        else if ( grade != 0 && section == 0 ){
            int c = 1;
            InfoHolder d = null;
            do{
                d = singleSearch(grade, c);
                if ( d != null ){
                    allInfo.add(d);
                }
                c++;
            }
            while( c < 150);

        }
        else{
            searchAll(indexForFullSearch);
        }

        return allInfo;

    }


    //    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private InfoHolder singleSearch(int g, int se){
        int occ;

        //used for the next td
        int next;

        //is used in extracting times
        int br;

        //span for teacher name
        int span;

        //the limit <tr>
        int tr;

        String searchString = "<td>"+id;
        searchString += " "+ g;
        searchString += "-"+ se+"</td>";

        String lessonHour;
        String lessons[] = new String[10];
        String teacherName;
        String name;
        //int to take the amount of lesson hours
        int howMany = 0;

        occ = websiteContents.indexOf( searchString , lastIndex);

        //if the searchString is not found return null
        if ( occ < 0 ){
            return null;
        }

        next = websiteContents.indexOf( "</td>", occ + searchString.length() + 1);

        //the next table row is maximum of our search
        tr = websiteContents.indexOf( "<tr>", next);

        //name of subject taken :D
        name = websiteContents.substring(start(next),next);


        //name of teacher taken
        span = websiteContents.indexOf("</span>",next);
        if ( span > tr || span < 0 )
            teacherName = "Staff";
        else
            teacherName = websiteContents.substring(start(span),span);

        //the <br /> found for the first time
        while (websiteContents.indexOf( "<br />", next) < tr){
            br = websiteContents.indexOf( "<br />", next);
            if (br < 0){
                return new InfoHolder( searchString , name ,
                        teacherName, Arrays.copyOfRange( lessons , 0, howMany) );
            }
            else{
                lessonHour = websiteContents.substring(start(br),br);
                next = br+1;
                //this will trim up the lessonHour String to make it only 1 line
                if ( lessonHour.startsWith("\n") )
                    lessonHour = lessonHour.substring(1);
                lessons[howMany] = lessonHour;
                howMany++;
            }
            lastIndex = tr-1;
        }

        tr = websiteContents.indexOf( "<tr>", lastIndex + 200);
        if( tr < 0 )
            hasMore = false;

        //this cuts the array to the parts we want for initialization
        lessons = Arrays.copyOfRange( lessons , 0, howMany);
        searchString = searchString.substring(4, searchString.indexOf("</td>"));
        InfoHolder toReturn = new InfoHolder( searchString , name , teacherName, lessons);

        return toReturn;
    }

    private void searchAll(int tr){
        int occ;
        //used for the next td
        int next;
        //is used in extracting times
        int br;
        //span for teacher name
        int span;
        String lessonHour, searchString;
        String lessons[] = new String[10];
        String teacherName;
        String name;
        //int to take the amount of lesson hours
        int howMany = 0;
        occ = websiteContents.indexOf( "</td>" , tr);
        if ( occ < 0 ){
            return;
        }
        searchString = websiteContents.substring(start(occ),occ);
        next = websiteContents.indexOf( "</td>", occ + 4);
        //the next table row is maximum of our search
        tr = websiteContents.indexOf( "<tr>", next);
        //name of subject taken :D
        name = websiteContents.substring(start(next),next);
        //name of teacher taken
        span = websiteContents.indexOf("</span>",next);
        if ( span > tr || span < 0 )
            teacherName = "Staff";
        else
            teacherName = websiteContents.substring(start(span),span);
        //the <br /> found for the first time
        while (websiteContents.indexOf( "<br />", next) < tr){
            br = websiteContents.indexOf( "<br />", next);
            if (br < 0){
                allInfo.add( new InfoHolder( searchString , name ,
                        teacherName, Arrays.copyOfRange( lessons , 0, howMany) ));
                return;
            }
            else{
                lessonHour = websiteContents.substring(start(br),br);
                next = br+1;
                //this will trim up the lessonHour String to make it only 1 line
                if ( lessonHour.startsWith("\n") )
                    lessonHour = lessonHour.substring(1);
                lessons[howMany] = lessonHour;
                howMany++;
            }
        }
        lessons = Arrays.copyOfRange( lessons , 0, howMany);
        allInfo.add(new InfoHolder( searchString , name , teacherName, lessons));
        if( tr < 0 )
            return;
        else
            searchAll(tr);
    }


    //facility developed to help in finding the beginning of a useful String to extract
    //this is used at the singleSearch method
    private int start(int endIndex){
        return websiteContents.lastIndexOf(">", endIndex)+1;
    }
}