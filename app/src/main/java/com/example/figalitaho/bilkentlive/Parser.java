package com.example.figalitaho.bilkentlive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/*
 * ==========================================================
 * @Author {Erin Avllazagaj}
 * @Version 1.0
 * ==========================================================
 * This will parse information form offerings to feed
 * the ListView for users. This will help users choose the
 * right stuff and not choose something wrong and then
 * pull their hair.
 * ==========================================================
 * Date: 10/5/2015
 * Time: 17:16
 * */

public class Parser {
    //properties
    ArrayList<String> coursesChosen = null;

    //this is for faster parsing
    //please don't change
    int lastRow = 650;

    //this constructor takes as ArrayList of Strings any checked course of the user
    public Parser(ArrayList<String> courses) {
        coursesChosen = courses;
    }

    //this method is used to parse course codes
    //from offerings for certain courses chosen by the user

    public ArrayList<String> parseCourseCode() {

        //This will make sure user has checked any checkbox
        if (coursesChosen != null) {

            //occurrence variable finds the first <td> tag
            int occ;
            //table row location
            int tr;
            //this will be the data parsed
            String courseCode;

            ArrayList<String> codes = new ArrayList<String>();
            for (int c = 0; c < coursesChosen.size(); c++) {
                OfferingsReader myScanner = new OfferingsReader(coursesChosen.get(c));
                String websiteContents = myScanner.getExpected();

                //adds a separator distinguishing which courses have what codes
                codes.add(coursesChosen.get(c) + " codes!");
                int next = 0;
                //if there is no any other table row return the arraylist and finish
                while (next >= 0) {

                    tr = websiteContents.indexOf("<tr>", lastRow + 400);
                    lastRow = tr;
                    occ = websiteContents.indexOf("<td>", tr);
                    //course code taken :D
                    courseCode = websiteContents.substring(websiteContents.indexOf(" ", occ),
                            websiteContents.indexOf("-", occ));

                    //if it exists in the ArrayList don't add it
                    if (!codes.contains(courseCode))
                        codes.add(courseCode);
                    next = websiteContents.indexOf("<tr>", lastRow + 400);
                    if (next >= 0) {
                        tr = next;
                        lastRow = tr - 401;
                    }
                }
                //reset variable
                lastRow = 650;
            }
            return codes;
        } else return null;
    }

    //this method is used to parse Instructors from offerings for certain courses chosen by the user
    public ArrayList<String> parseInstructor() {

        //This will make sure user has checked any checkbox
        if (coursesChosen != null) {
            //occurrence variable finds the first <span> tag
            int occ;
            //table row location
            int tr;
            //this will be the data parsed
            String instructor;

            ArrayList<String> instructors = new ArrayList<String>();
            for (int c = 0; c < coursesChosen.size(); c++) {
                OfferingsReader myScanner = new OfferingsReader(coursesChosen.get(c));
                String websiteContents = myScanner.getExpected();

                //adds a separator distinguishing which courses have what codes
                instructors.add(coursesChosen.get(c) + " instructor(s)!");

                //this will determine if there is another row avaliable
                int next = 0;
                //if there is no any other table row return the arraylist and finish
                while (next >= 0) {
                    tr = websiteContents.indexOf("<tr>", lastRow + 400);
                    lastRow = tr;
                    occ = websiteContents.indexOf("</span>", tr);
                    //course code taken :D
                    instructor = websiteContents.substring(websiteContents.lastIndexOf(">", occ) + 1,
                            occ);
                    //if it exists in the ArrayList don't add it
                    if (!instructors.contains(instructor))
                        instructors.add(instructor);
                    if (next >= 0) {
                        tr = next;
                        lastRow = tr - 401;

                    }
                }
                //reset variable
                lastRow = 650;
            }
            return instructors;
        }
        else return null;
    }

    //this method is used to parse buildings from offerings for certain courses chosen by the user
    public ArrayList<String> parseBuilding() {
        //This will make sure user has checked any checkbox
        if (coursesChosen != null) {
            //occurrence variable finds the first <span> tag
            int occ;
            //table row location
            int tr;
            //this will be the data parsed
            String building;

            ArrayList<String> buildings = new ArrayList<String>();
            for (int c = 0; c < coursesChosen.size(); c++) {
                OfferingsReader myScanner = new OfferingsReader(coursesChosen.get(c));
                String websiteContents = myScanner.getExpected();

                //adds a separator distinguishing which courses have what codes
                buildings.add(coursesChosen.get(c) + " instructor(s)!");

                //this will determine if there is another row avaliable
                int next = 0;
                //if there is no any other table row return the arraylist and finish
                while (next >= 0) {
                    tr = websiteContents.indexOf("<tr>", lastRow + 400);
                    lastRow = tr;
                    occ = websiteContents.lastIndexOf("<br />", tr);
                    //course code taken :D
                    int theMinus = websiteContents.lastIndexOf("-", occ);
                    building = websiteContents.substring(websiteContents.lastIndexOf(" ", theMinus)
                            ,theMinus);
                    //if it exists in the ArrayList don't add it
                    if (!buildings.contains(building))
                        buildings.add(building);
                    if (next >= 0) {
                        tr = next;
                        lastRow = tr - 401;
                    }
                }
                //reset variable
                lastRow = 650;
            }
            return buildings;
        }
        else return null;
    }

}