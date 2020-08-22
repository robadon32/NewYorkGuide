package com.example.newyorktouristapp;

import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PlaceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Place place);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Place> places);

    @Delete
    void delete(Place place);

    @Update
    void update(Place... place);

    @Query("DELETE FROM place_table")
    void deleteAll();

    @Query("SELECT * FROM place_table WHERE boolean = :isFavorite")
    LiveData<List<Place>> getAllPlaces(boolean isFavorite);

    @Query("SELECT * FROM place_table WHERE category = :placeType")
    LiveData<List<Place>> getAllPlaceTypes(String placeType);
}
