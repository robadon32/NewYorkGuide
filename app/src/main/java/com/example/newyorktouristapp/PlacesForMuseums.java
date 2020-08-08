package com.example.newyorktouristapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Bundle;

import java.util.ArrayList;

public class PlacesForMuseums extends AppCompatActivity {

    private ArrayList<Place> placeData;
    private RecyclerView recyclerView;
    private PlaceAdapter placeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        placeData = new ArrayList<Place>();

        recyclerView  = findViewById(R.id.list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        placeAdapter = new PlaceAdapter(this, placeData);
        recyclerView.setAdapter(placeAdapter);

        loadPlaceData();
    }

    private void loadPlaceData(){
        String[] arrTitles = getResources().getStringArray(R.array.museums);
        String[] arrDescriptions = getResources().getStringArray(R.array.museums_description);
        String[] arrNumbers = getResources().getStringArray(R.array.museums_number);
        String[] arrAddresses = getResources().getStringArray(R.array.museums_address);
        String[] arrLinks = getResources().getStringArray(R.array.museums_link);
        TypedArray arrImages = getResources().obtainTypedArray(R.array.museums_images);

        placeData.clear();

        for(int i = 0; i < arrTitles.length; i++) {
            placeData.add(new Place(arrImages.getResourceId(i, 0), arrTitles[i],
                            arrDescriptions[i], arrAddresses[i],arrNumbers[i], arrLinks[i]));

        }
        arrImages.recycle();
        placeAdapter.notifyDataSetChanged();
    }

}

