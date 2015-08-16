package com.example.figalitaho.bilkentlive;

import java.util.ArrayList;

public class Filter {
    private ArrayList<InfoHolder> toFilter;

    public Filter( ArrayList<InfoHolder> param) {
        toFilter = param;
    }

    public void teacherFilter(String teacherFilter){
        for(int x = 0; x< toFilter.size(); x++){
            if( !toFilter.get(x).getCourseTeacher().equals(teacherFilter) ){
                toFilter.get(x).setUse( false );
            }
            else{
                toFilter.get(x).setUse( true );
            }
        }
    }

    public void idFilter(String idFilter){
        for(int x = 0; x< toFilter.size(); x++){
            if( toFilter.get(x).getId().indexOf( idFilter ) < 0 ){
                toFilter.get(x).setUse( false );
            }
            else{
                toFilter.get(x).setUse( true );
            }
        }
    }


    @Override
    public String toString(){
        String toReturn = "";
        for(int c = 0; c < toFilter.size(); c++){
            if(toFilter.get(c).getUse()){
                toReturn += toFilter.get(c).toString();
            }
        }
        return toReturn;
    }

    public InfoHolder[] makeArray() {
        InfoHolder[] x = new InfoHolder[toFilter.size()];
        for(int c = 0; c < toFilter.size(); c++){
            if(toFilter.get(c).getUse())
                x[c] = toFilter.get(c);
        }
        return x;
    }

    public ArrayList<String> makeArrayList(){
        ArrayList<String> toReturn = new ArrayList<String>(toFilter.size());
        for(int c = 0; c < toFilter.size(); c++){
            if(toFilter.get(c).getUse())
                toReturn.add(toFilter.get(c).toString());
        }
        return toReturn;
    }

}