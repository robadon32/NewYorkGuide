package com.example.newyorktouristapp;

import android.app.Application;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;

public class PlaceRepository {
    private PlaceDao placeDao;
    private LiveData<List<Place>> allPlaces;

    PlaceRepository(Application application) {
        PlaceRoomDataBase db = PlaceRoomDataBase.getDatabase(application);
        placeDao = db.placeDao();
        allPlaces = placeDao.getAllPlaces();
    }

    LiveData<List<Place>> getAllPlaces() {
        return allPlaces;
    }

    public void insert(Place place) {
        new insertAsyncTask(placeDao).execute(place);
    }

    private static class insertAsyncTask extends AsyncTask<Place, Void, Void> {
        private PlaceDao asyncTaskDao;

        public insertAsyncTask(PlaceDao placeDao) {
            asyncTaskDao = placeDao;
        }

        @Override
        protected Void doInBackground(Place... places) {
            asyncTaskDao.insert(places[0]);
            return null;
        }
    }
}
