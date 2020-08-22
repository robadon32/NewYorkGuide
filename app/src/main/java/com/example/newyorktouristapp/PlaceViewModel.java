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
    private LiveData<List<Place>> allLandmarks;

    public PlaceViewModel(Application application) {
        super(application);
        placeRepository = new PlaceRepository(application);
        allPlaces = placeRepository.getAllPlaces();
        allLandmarks = placeRepository.getAllLandmarks();
    }

    LiveData<List<Place>> getAllPlaces() {
        return allPlaces;
    }

    LiveData<List<Place>> getAllLandmarks() {
        return allLandmarks;
    }

    public void insert(Place place) {
        placeRepository.insert(place);
    }

    public void insertAll(List<Place> places) {
        placeRepository.insertAll(places);
    }

    public void delete(Place place) {
        placeRepository.delete(place);
    }

    public void deleteAll(){
        placeRepository.deleteAll();
    }

    public void update(Place place) {
        placeRepository.update(place);
    }

}
