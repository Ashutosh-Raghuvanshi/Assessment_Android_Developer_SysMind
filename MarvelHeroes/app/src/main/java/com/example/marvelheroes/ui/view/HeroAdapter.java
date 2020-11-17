package com.example.marvelheroes.ui.view;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.marvelheroes.R;
import com.example.marvelheroes.data.Hero;

import java.util.List;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.HeroHolder> {

    List<Hero> heroList;

    HeroAdapter(List<Hero> heroes){
        this.heroList = heroes;
    }

    public void updateList(List<Hero> heroes){
        heroList.clear();
        heroList.addAll(heroes);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HeroHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hero_item, parent, false);
        return new HeroHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroHolder holder, int position) {
        Hero hero = heroList.get(position);
        holder.heroName.setText(hero.getName());
        holder.heroDesc.setText(hero.getDescription());
        loadImage(holder.heroImage, hero.getImage_uri());
    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }

    public Hero getNodeAt(int position){
        return heroList.get(position);
    }

    public class HeroHolder extends RecyclerView.ViewHolder {
        ImageView heroImage;
        TextView heroName, heroDesc;
        public HeroHolder(@NonNull View itemView) {
            super(itemView);
            heroImage = itemView.findViewById(R.id.hero_image);
            heroName = itemView.findViewById(R.id.hero_name);
            heroDesc = itemView.findViewById(R.id.hero_description);
        }
    }

    private void loadImage(ImageView imageView, String uriString){
        Uri uri = Uri.parse(uriString);
        Glide.with(imageView)
                .load(uri)
                .centerCrop()
                .placeholder(R.drawable.avatar)
                .into(imageView);
    }
}
