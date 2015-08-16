package com.example.figalitaho.bilkentlive;

/*
 * ==========================================================
 * @Author {Erin Avllazagaj}
 * @Version 1.8
 * ==========================================================
 * This class will hold info for the course like:
 * course ID, course name, course teacher, course hours...
 * ==========================================================
 * Date: 23/3/2015
 *
 * */

public class InfoHolder {
    private String id;
    private String courseName;
    private String courseTeacher;
    private String[] lessonAndBuilding;
    private boolean useful;
    //NVM the hour getter thing

    public InfoHolder(String id, String name, String teacher, String[] info) {
        this.id = id;
        courseName = name;
        courseTeacher = teacher;
        lessonAndBuilding = info;
        useful = true;
    }


    public String toString(){
        String toReturn = "\n";
        toReturn += id+"\n"+courseName+"\n"+courseTeacher+"\n";
        for (String aLessonAndBuilding : lessonAndBuilding) {
            toReturn += aLessonAndBuilding + "\n";
        }
        return toReturn;
    }


    public String getId() {
        return id;
    }
    public String getCourseName() {
        return courseName;
    }
    public String getCourseTeacher() {
        return courseTeacher;
    }
    public String[] getLessonAndBuilding() {
        return lessonAndBuilding;
    }
    public boolean getUse(){
        return useful;
    }
    public void setUse( boolean toSet){
        useful = toSet;
    }
}