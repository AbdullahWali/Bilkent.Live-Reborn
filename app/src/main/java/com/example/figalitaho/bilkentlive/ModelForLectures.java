package com.example.figalitaho.bilkentlive;

/**
 * Created by Figali Taho on 09/05/2015.
 */
public class ModelForLectures {

    String valueOfFilter;
    int value; //0 is checkbox disabled, 1 if checkbox enabled
    boolean selected;

    ModelForLectures(String valueOfFilter, int value, boolean selected){
        this.value = value;
        this.valueOfFilter = valueOfFilter;
        this.selected = selected;
    }

    public void setValueOfFilter(String valueOfFilter) {
        this.valueOfFilter = valueOfFilter;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getValueOfFilter(){
        return this.valueOfFilter;
    }
    public int getValue(){
        return this.value;
    }
}
