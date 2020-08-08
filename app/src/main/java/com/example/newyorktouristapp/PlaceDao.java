package com.example.newyorktouristapp;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface PlaceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Place place);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Place> place);

    @Query("DELETE FROM place_table")
    void deleteAll();

    @Query("SELECT * FROM place_table")
    LiveData<List<Place>> getAllPlaces();
}
