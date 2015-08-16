package com.example.figalitaho.bilkentlive;

/**
 * Created by Sarjeel on 11-05-2015.
 */
public class Node {
    // Properties
    private double xAxis, yAxis;
    private String data, tag;

    // Constructors
    public Node (double x, double y, String loc, String searchTag){
        xAxis = x;
        yAxis = y;
        data = loc;
        tag = searchTag;
    }

    // Methods
    /**
     * @return the xAxis
     */
    public double getxAxis() {
        return xAxis;
    }

    /**
     * @param xAxis the xAxis to set
     */
    public void setxAxis(double xAxis) {
        this.xAxis = xAxis;
    }

    /**
     * @return the yAxis
     */
    public double getyAxis() {
        return yAxis;
    }

    /**
     * @param yAxis the yAxis to set
     */
    public void setyAxis(double yAxis) {
        this.yAxis = yAxis;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    public String getTag() {
        return tag;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
