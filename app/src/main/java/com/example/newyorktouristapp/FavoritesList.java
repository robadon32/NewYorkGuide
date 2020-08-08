package com.example.newyorktouristapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class FavoritesList extends AppCompatActivity {

    List<Place> favoritesData;
    public PlaceViewModel placeViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        favoritesData = MainActivity.favoritesData;

        RecyclerView recyclerView = findViewById(R.id.list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final FavoritesAdapter favoritesAdapter = new FavoritesAdapter(this, favoritesData);
        recyclerView.setAdapter(favoritesAdapter);

        favoritesAdapter.notifyDataSetChanged();

//      placeViewModel = ViewModelProviders.of(this,
//                new PlaceViewModelFactory(getApplication()))
//                .get(PlaceViewModel.class);
//        placeViewModel.getAllPlaces().observe(this, new Observer<List<Place>>() {
//            @Override
//            public void onChanged(List<Place> places) {
//                favoritesAdapter.setPlaces(places);
//            }
//        });

//    placeViewModel = ViewModelProviders.of(this).get(PlaceViewModel.class);
//    placeViewModel.getAllPlaces().observe(this, new Observer<List<Place>>() {
//        @Override
//        public void onChanged(List<Place> places) {
//            favoritesAdapter.setPlaces(places);
//        }
//    });

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper
                .SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                favoritesData.remove(viewHolder.getAdapterPosition());
                favoritesAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });

        helper.attachToRecyclerView(recyclerView);
    }
}