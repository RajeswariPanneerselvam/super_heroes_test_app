package com.app.superheroestestapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.app.superheroestestapp.adapter.HeroesAdapter;
import com.app.superheroestestapp.apiservice.ApiClient;
import com.app.superheroestestapp.apiservice.ApiInterface;
import com.app.superheroestestapp.model.HeroesModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ItemClick{

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    ProgressDialog progressDialog;


    HeroesAdapter adapter;

    @BindView(R.id.swipeRefresh)
        SwipeRefreshLayout swipeRefreshLayout;
    List<HeroesModel> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(this, R.dimen.item_offset);
        recyclerView.addItemDecoration(itemDecoration);

        HeroesViewModel model = ViewModelProviders.of(this).get(HeroesViewModel.class);

        model.getHeroes().observe(this, new Observer<List<HeroesModel>>() {
            @Override
            public void onChanged(@Nullable List<HeroesModel> heroList) {
                adapter = new HeroesAdapter(heroList, MainActivity.this, MainActivity.this);
                recyclerView.setAdapter(adapter);

            }
        });


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);


                adapter = new HeroesAdapter(AppController.getInstance().heroes, MainActivity.this,MainActivity.this);
                recyclerView.setAdapter(adapter);

                adapter.notifyDataSetChanged();


            }
        });





    }

    private void getHeroesList() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data..");
        progressDialog.setCancelable(false);
        progressDialog.show();


        ApiInterface service = ApiClient.getRetrofitInstance().create(ApiInterface.class);
        Call<List<HeroesModel>> call = service.getHeroesList();
        call.enqueue(new Callback<List<HeroesModel>>() {
            @Override
            public void onResponse(Call<List<HeroesModel>> call, Response<List<HeroesModel>> response) {


                data  =response.body();
                adapter=new HeroesAdapter(data,MainActivity.this,MainActivity.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                AppController.getInstance().setHeroes(response.body());
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<List<HeroesModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
    }

    @Override
    public void onClicked(List<HeroesModel> data) {
        AppController.getInstance().heroes =data;
        startActivity(new Intent(getApplicationContext(),HeroesDetailActivity.class));
    }
}