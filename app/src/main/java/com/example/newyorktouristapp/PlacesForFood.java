package com.example.newyorktouristapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Bundle;

import java.util.ArrayList;

public class PlacesForFood extends AppCompatActivity {

    RecyclerView recyclerView;
    PlaceAdapter placeAdapter;
    ArrayList<Place> placeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        placeData = new ArrayList<Place>();

        recyclerView = findViewById(R.id.list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        placeAdapter = new PlaceAdapter(this, placeData);
        recyclerView.setAdapter(placeAdapter);

        loadPlaceData();
    }

    private void loadPlaceData() {
        TypedArray arrImages = getResources().obtainTypedArray(R.array.food_images);
        String[] arrTitles = getResources().getStringArray(R.array.food_titles);
        String[] arrDescriptions = getResources().getStringArray(R.array.food_description);
        String[] arrReviews = getResources().getStringArray(R.array.food_review);
        String[] arrNumbers = getResources().getStringArray(R.array.food_phoneNumber);
        String[] arrAddresses = getResources().getStringArray(R.array.food_address);
        String[] arrLinks = getResources().getStringArray(R.array.food_links);

        placeData.clear();

        for(int i = 0; i < arrTitles.length; i++) {
            placeData.add(new Place(arrImages.getResourceId(i, 0), arrTitles[i],
                    arrDescriptions[i], arrReviews[i], arrNumbers[i], arrAddresses[i], arrLinks[i]));
        }

        arrImages.recycle();
        placeAdapter.notifyDataSetChanged();
    }
}