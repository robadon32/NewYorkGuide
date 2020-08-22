package com.example.newyorktouristapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Place> placeData;

    public FavoritesAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public FavoritesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.list_item2, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesAdapter.ViewHolder holder, int position) {
        Place currPlace = placeData.get(position);
        holder.titleView.setText(currPlace.getLocTitle());
        holder.numberView.setText(String.format("(%s) %s-%s", currPlace.getLocNumber().substring(0, 3),
                currPlace.getLocNumber().substring(3, 6), currPlace.getLocNumber().substring(6, 10)));
    }

    public void setPlaces(List<Place> places) {
        placeData = places;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if(placeData != null){
            return placeData.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleView, numberView;

        public ViewHolder(View itemView) {
            super(itemView);
            Log.d("ViewHolder", "inside Constructor");
            titleView = itemView.findViewById(R.id.place_title_two);
            numberView = itemView.findViewById(R.id.place_number);
        }
    }
}
