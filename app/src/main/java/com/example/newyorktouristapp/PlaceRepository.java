package com.example.newyorktouristapp;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.loader.content.AsyncTaskLoader;

public class PlaceRepository {

    private String TAG = "PlaceRepository";
    private PlaceDao placeDao;
    //add LiveData<List<Place>> allPlaces, allLandmarks, allMuseums, allFun, allRestaurants.
    private LiveData<List<Place>> allPlaces;
    private LiveData<List<Place>> allLandmarks;
    private LiveData<List<Place>> allMuseums;
    private LiveData<List<Place>> allFun;
    private LiveData<List<Place>> allRestaurants;
    private Place currPlace;

    PlaceRepository(Application application) {
        Log.d(TAG, "Inside constructor");
        PlaceRoomDataBase db = PlaceRoomDataBase.getDatabase(application);
        placeDao = db.placeDao();
        allLandmarks = placeDao.getAllPlaceTypes("landmark");
        allMuseums = placeDao.getAllPlaceTypes("museum");
        allFun = placeDao.getAllPlaceTypes("fun");
        allRestaurants = placeDao.getAllPlaceTypes("restaurant");
        allPlaces = placeDao.getAllPlaces(true);
        Log.d(TAG, "End of constructor");
    }

    LiveData<List<Place>> getAllPlaces() {
        return allPlaces;
    }

    LiveData<List<Place>> getAllLandmarks() {
        return allLandmarks;
    }

    LiveData<List<Place>> getAllMuseums() {
        return allMuseums;
    }

    LiveData<List<Place>> getAllFun() {
        return allFun;
    }

    LiveData<List<Place>> getAllRestaurants() {
        return allRestaurants;
    }

    public void insert(Place place) {
        new insertAsyncTask(placeDao).execute(place);
    }
    
    public void insertAll(List<Place> places) {
        new insertAllAsyncTask(placeDao).execute(places);
    }

    public void delete(Place place) { new deleteAsyncTask(placeDao).execute(place); }

    public void deleteAll() {
        new deleteAllAsyncTask(placeDao).execute();
    }

    public void update(Place place) { new updateAsyncTask(placeDao).execute(place); }

    private static class insertAsyncTask extends AsyncTask<Place, Void, Void> {
        private PlaceDao asyncTaskDao;

        public insertAsyncTask(PlaceDao placeDao) {
            asyncTaskDao = placeDao;
        }

        @Override
        protected Void doInBackground(Place... place) {
            asyncTaskDao.insert(place[0]);
            return null;
        }
    }

    private static class insertAllAsyncTask extends AsyncTask<List<Place>, Void, Void> {
        private PlaceDao asyncTaskDao;

        public insertAllAsyncTask(PlaceDao placeDao) {
            asyncTaskDao = placeDao;
        }

        @Override
        protected Void doInBackground(List<Place>... lists) {
            asyncTaskDao.insertAll(lists[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Place, Void, Void> {
        private PlaceDao asyncTaskDao;

        public deleteAsyncTask(PlaceDao placeDao){
            asyncTaskDao = placeDao;
        }

        @Override
        protected Void doInBackground(Place... place) {
            asyncTaskDao.delete(place[0]);
            return null;
        }
    }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private PlaceDao asyncTaskDao;

        public deleteAllAsyncTask(PlaceDao placeDao) {
            asyncTaskDao = placeDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            asyncTaskDao.deleteAll();
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Place, Void, Void> {
        private PlaceDao asyncTaskDao;

        public updateAsyncTask(PlaceDao placeDao) {
            asyncTaskDao = placeDao;
        }

        @Override
        protected Void doInBackground(Place... place) {
            asyncTaskDao.update(place[0]);
            return null;
        }
    }
}
