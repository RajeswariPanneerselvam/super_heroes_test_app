package com.app.superheroestestapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.superheroestestapp.ItemClick;
import com.app.superheroestestapp.R;
import com.app.superheroestestapp.model.HeroesModel;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class HeroesAdapter extends RecyclerView.Adapter<HeroesAdapter.ViewHolder> {


    List<HeroesModel> heroes;
    Context context;
    ItemClick click;


    public HeroesAdapter(List<HeroesModel>data, ItemClick itemClick,Context context){

        this.heroes=data;
        this.click=itemClick;
        this.context=context;
        notifyDataSetChanged();
    }


    @Override
    public HeroesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.heroes_recycler_adapter_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder( HeroesAdapter.ViewHolder holder, int position) {
        holder.text_name.setText(heroes.get(position).getName());

        Picasso.with(context).load(heroes.get(position).getImageurl()).into(holder.image_hero);






    }

    @Override
    public int getItemCount() {
        return heroes.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{


        TextView text_name;
        ImageView image_hero;
        public ViewHolder( View itemView) {
            super(itemView);
            text_name=itemView.findViewById(R.id.text_hero) ;
            image_hero=itemView.findViewById(R.id.hero_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.onClicked(Collections.singletonList(heroes.get(getAdapterPosition())));
                }
            });
        }
    }


}
