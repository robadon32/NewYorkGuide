package com.example.newyorktouristapp;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

public class PlaceViewModel extends AndroidViewModel {

    private PlaceRepository placeRepository;
    private LiveData<List<Place>> allPlaces;

    public PlaceViewModel(Application application) {
        super(application);
        placeRepository = new PlaceRepository(application);
        allPlaces = placeRepository.getAllPlaces();
    }

    LiveData<List<Place>> getAllPlaces() {
        return allPlaces;
    }

    public void insert(Place place) {
        placeRepository.insert(place);
    }
}
