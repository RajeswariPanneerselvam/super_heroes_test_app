package com.app.superheroestestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeroesDetailActivity extends AppCompatActivity {
    @BindView(R.id.image_hero)
    ImageView hero_image;
    @BindView(R.id.text_hero_name)
    TextView hero_name_text;
    @BindView(R.id.hero_btn)
    Button  hero_name_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heroes_detail);
        ButterKnife.bind(this);


        Picasso.with(this).load(AppController.getInstance().heroes.get(0).getImageurl()).into(hero_image);
        hero_name_text.setText(AppController.getInstance().heroes.get(0).getName());
        hero_name_button.setText(AppController.getInstance().heroes.get(0).getName());
    }
}