package com.example.newyorktouristapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {

    protected static String IMAGE_EXTRA_KEY = "image";
    protected static String TITLE_EXTRA_KEY = "title";
    protected static String DESCRIPTION_EXTRA_KEY = "description";
    protected static String REVIEW_EXTRA_KEY = "review";
    protected static String ADDRESS_EXTRA_KEY = "address";
    protected static String NUMBER_EXTRA_KEY = "number";
    protected static String LINK_EXTRA_KEY = "link";
    protected static String FAVORITE_EXTRA_KEY = "place";
    protected static String TYPE_EXTRA_KEY = "placeType";

    protected List<Place> placeData;
    private Context context;

    public PlaceAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public PlaceAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Place currPlace = placeData.get(position);
        Glide.with(context).load(currPlace.getLocImage()).centerCrop().into(holder.placeView);
        holder.titleView.setText(currPlace.getLocTitle());
        holder.descriptionView.setText(currPlace.getLocDescription());
        if(currPlace.getIsFavorite()) {
            holder.favoritesIcon.setImageResource(R.drawable.favorite_icon_red);
        } else {
            holder.favoritesIcon.setImageResource(R.drawable.favorite_icon);
        }
    }

    @Override
    public int getItemCount() {
        if(placeData != null){
            return placeData.size();
        }
        return 0;
    }

    public void setPlaces(List<Place> places) {
        placeData = places;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        ImageView placeView, favoritesIcon;
        TextView titleView, descriptionView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            placeView = itemView.findViewById(R.id.place_image);
            titleView = itemView.findViewById(R.id.place_title);
            descriptionView = itemView.findViewById(R.id.place_description);
            favoritesIcon = itemView.findViewById(R.id.list_item_favorite_icon);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Place currPlace = placeData.get(getAdapterPosition());

            Intent adapterIntent = new Intent(context, PlacesDescription.class);
            adapterIntent.putExtra(IMAGE_EXTRA_KEY, currPlace.getLocImage());
            adapterIntent.putExtra(TITLE_EXTRA_KEY, currPlace.getLocTitle());
            adapterIntent.putExtra(DESCRIPTION_EXTRA_KEY, currPlace.getLocDescription());
            adapterIntent.putExtra(REVIEW_EXTRA_KEY, currPlace.getLocReview());
            adapterIntent.putExtra(NUMBER_EXTRA_KEY, currPlace.getLocNumber());
            adapterIntent.putExtra(ADDRESS_EXTRA_KEY, currPlace.getLocAddress());
            adapterIntent.putExtra(LINK_EXTRA_KEY, currPlace.getLocUrl());
            adapterIntent.putExtra(FAVORITE_EXTRA_KEY, currPlace.getIsFavorite());
            adapterIntent.putExtra(TYPE_EXTRA_KEY, currPlace.getPlaceType());
            context.startActivity(adapterIntent);
        }

        @Override
        public boolean onLongClick(View view) {
            Place currPlace = placeData.get(getAdapterPosition());
            Intent adapterIntent = new Intent(context, FavoritesList.class);
            adapterIntent.putExtra(TITLE_EXTRA_KEY, currPlace.getLocTitle());

            return true;
        }
    }
}
