package com.example.newyorktouristapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Place> placeData;

    public FavoritesAdapter(Context context, List<Place> placeData) {
        this.context = context;
        this.placeData = placeData;
    }

    @NonNull
    @Override
    public FavoritesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item2, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesAdapter.ViewHolder holder, int position) {
        Place currPlace = placeData.get(position);
        holder.bindTo(currPlace);
    }

    public void setPlaces(List<Place> places) {
        placeData = places;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return placeData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleView, numberView;

        public ViewHolder(View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.place_title_two);
            numberView = itemView.findViewById(R.id.place_number);
        }

        public void bindTo(Place currPlace) {
            titleView.setText(currPlace.getLocTitle());
            numberView.setText(String.format("(%s) %s-%s", currPlace.getLocNumber().substring(0, 3),
                    currPlace.getLocNumber().substring(3, 6), currPlace.getLocNumber().substring(6, 10)));
        }
    }
}
