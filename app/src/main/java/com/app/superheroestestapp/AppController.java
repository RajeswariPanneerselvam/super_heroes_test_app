package com.app.superheroestestapp;

import android.app.Application;

import com.app.superheroestestapp.model.HeroesModel;

import java.util.List;

public class AppController extends Application {
    static AppController mInstance;

    public static AppController getInstance() {
        if (mInstance == null) {
            mInstance = new AppController();
        }
        return mInstance;
    }
    public List<HeroesModel> heroes;
    public List<HeroesModel> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<HeroesModel> heroes) {
        this.heroes = heroes;
    }




}
