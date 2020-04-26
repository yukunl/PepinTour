package com.example.pepintourapp;

import java.util.ArrayList;
import java.util.Arrays;

public class destination {

    private String Name, ImageURL, txt;
    private ArrayList<String> addresses = new ArrayList<String> (Arrays.asList("201t", "304s",
            "310s", "112p", "108p", "310f", "314f", "400f", "404f", "408f", "410f", "506f", "612f",
            "714f", "816f", "315e", "806t", "504s", "207l", "410s", "402s", "410t", "404m"));

    public destination(String name){
        Name = name;
    }
    public destination(String name, String imageURL, String description){
        Name = name;
        ImageURL = imageURL;
        txt = description;
    }

    public void setName(String name){
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public String getImageURL(){
        return ImageURL;
    }


//    public int getFileName() {
//        return Integer.parseInt(Name);
//    }

    public String getDescription() { return txt; }

}

