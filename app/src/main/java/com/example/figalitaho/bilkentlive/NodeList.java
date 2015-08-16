package com.example.figalitaho.bilkentlive;

import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Sarjeel on 11-05-2015.
 */
public class NodeList {
    ArrayList<Node> list;
    Scanner read;
    File inputFile;
    String singleLine, loc, tag;
    StringTokenizer str;
    Node p;
    double xAxis, yAxis;

    public NodeList( InputStreamReader file) {
        read = new Scanner(file);
        list = new ArrayList<Node>();
        while( read.hasNextLine()){
            singleLine = read.nextLine();
            System.out.println(singleLine);
            str = new StringTokenizer(singleLine, ",");

            xAxis = Double.parseDouble(str.nextToken());
            yAxis = Double.parseDouble(str.nextToken());
            loc = str.nextToken();
            tag = str.nextToken();
            list.add( new Node(xAxis, yAxis, loc, tag));
        }
        read.close();
    }

    public Node findNode (String search){
        Node result = null;
        if (list != null){
            for(Node p: list){
                if(search.equals(p.getTag())){
                    result = p;
                }
            }
        }
        else
            result = null;

        return result;
    }

    public ArrayList<Node> getList(){
        return list;
    }
}
