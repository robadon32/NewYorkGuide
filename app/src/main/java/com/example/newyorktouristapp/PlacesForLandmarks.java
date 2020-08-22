package com.example.newyorktouristapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class PlacesForLandmarks extends AppCompatActivity {

    RecyclerView recyclerView;
    PlaceAdapter placeAdapter;
    PlaceViewModel placeViewModel;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        floatingActionButton = findViewById(R.id.floatingActionButton2);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addNewPlaceIntent = new Intent(getApplicationContext(), AddNewPlace.class);
                startActivity(addNewPlaceIntent);
            }
        });
        recyclerView = findViewById(R.id.list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        placeAdapter = new PlaceAdapter(this);
        recyclerView.setAdapter(placeAdapter);

        placeViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(PlaceViewModel.class);
        placeViewModel.getAllLandmarks().observe(this, new Observer<List<Place>>() {
            @Override
            public void onChanged(List<Place> places) {
                placeAdapter.setPlaces(places);
            }
        });
    }
}