package com.example.newyorktouristapp;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Place.class}, version = 2, exportSchema = false)
public abstract class PlaceRoomDataBase extends RoomDatabase {

    private static PlaceRoomDataBase INSTANCE;
    public abstract PlaceDao placeDao();

    public static PlaceRoomDataBase getDatabase(final Context context) {
        if(INSTANCE == null) {
            Room.databaseBuilder(context.getApplicationContext(),
                    PlaceRoomDataBase.class, "place_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(sRoomDataBaseCallBack)
                    .build();
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDataBaseCallBack = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final PlaceDao myPlaceDao;
        PopulateDbAsync(PlaceRoomDataBase db) {
            myPlaceDao = db.placeDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            myPlaceDao.insert(new Place("title", "87878"));
            return null;
        }
    }
}
