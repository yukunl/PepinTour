package com.example.pepintourapp;

public class destination {
    private String Name, ImageURL;
    public destination(String name, String imageURL){
        Name = name;
        ImageURL = imageURL;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }
}
