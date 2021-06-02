package com.app.superheroestestapp.model;

import com.app.superheroestestapp.MainActivity;

import java.util.List;

public class HeroesModel {
    public String name;
    public String realname;
    public String team;
    public String firstappearance;
    public String createdby;
    public String publisher;
    public String imageurl;
    public String bio;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
