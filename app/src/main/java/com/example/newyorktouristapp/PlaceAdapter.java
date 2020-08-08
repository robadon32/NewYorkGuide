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
    protected static String PLACE_EXTRA_KEY = "place";

    protected static ArrayList<Place> placeData;
    private Context context;

    public PlaceAdapter(Context context, ArrayList<Place> placeData) {
        this.context = context;
        this.placeData = placeData;
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
        holder.bindTo(currPlace);
    }

    @Override
    public int getItemCount() {
        return placeData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView placeView;
        TextView titleView, descriptionView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            placeView = itemView.findViewById(R.id.place_image);
            titleView = itemView.findViewById(R.id.place_title);
            descriptionView = itemView.findViewById(R.id.place_description);
            itemView.setOnClickListener(this);
        }

        public void bindTo(Place currPlace) {
            Glide.with(context).load(currPlace.getLocImage()).into(placeView);
            titleView.setText(currPlace.getLocTitle());
            descriptionView.setText(currPlace.getLocDescription());
        }

        @Override
        public void onClick(View view) {
            Place currPlace = placeData.get(getAdapterPosition());
            int currPlaceIndex = placeData.indexOf(currPlace);

            boolean isInTheList = false;
            if(MainActivity.favoritesData.contains(currPlace)){ isInTheList = true; }

            Intent adapterIntent = new Intent(context, PlacesDescription.class);
            adapterIntent.putExtra(IMAGE_EXTRA_KEY, currPlace.getLocImage());
            adapterIntent.putExtra(TITLE_EXTRA_KEY, currPlace.getLocTitle());
            adapterIntent.putExtra(DESCRIPTION_EXTRA_KEY, currPlace.getLocDescription());
            adapterIntent.putExtra(REVIEW_EXTRA_KEY, currPlace.getLocReview());
            adapterIntent.putExtra(NUMBER_EXTRA_KEY, currPlace.getLocNumber());
            adapterIntent.putExtra(ADDRESS_EXTRA_KEY, currPlace.getLocAddress());
            adapterIntent.putExtra(LINK_EXTRA_KEY, currPlace.getLocUrl());
            adapterIntent.putExtra(PLACE_EXTRA_KEY, isInTheList);
            context.startActivity(adapterIntent);
        }
    }
}
