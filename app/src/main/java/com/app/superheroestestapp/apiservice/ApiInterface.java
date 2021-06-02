package com.app.superheroestestapp.apiservice;

import com.app.superheroestestapp.model.HeroesModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("marvel/")
    Call<List<HeroesModel>> getHeroesList();
}
