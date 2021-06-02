package com.app.superheroestestapp;

import android.app.ProgressDialog;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.superheroestestapp.adapter.HeroesAdapter;
import com.app.superheroestestapp.apiservice.ApiClient;
import com.app.superheroestestapp.apiservice.ApiInterface;
import com.app.superheroestestapp.model.HeroesModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeroesViewModel extends ViewModel {
    ProgressDialog progressDialog;

    private MutableLiveData<List<HeroesModel>> data;

    //we will call this method to get the data
    public LiveData<List<HeroesModel>> getHeroes() {
        //if the list is null
        if (data == null) {
            data = new MutableLiveData<List<HeroesModel>>();
            //we will load it asynchronously from server in this method
            getHeroesList();
        }

        //finally we will return the list
        return data;
    }

    private void getHeroesList() {

        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<List<HeroesModel>> call = service.getHeroesList();
        call.enqueue(new Callback<List<HeroesModel>>() {
            @Override
            public void onResponse(Call<List<HeroesModel>> call, Response<List<HeroesModel>> response) {


                data.setValue(response.body());

                AppController.getInstance().setHeroes(response.body());

            }

            @Override
            public void onFailure(Call<List<HeroesModel>> call, Throwable t) {

            }
        });
    }

}
