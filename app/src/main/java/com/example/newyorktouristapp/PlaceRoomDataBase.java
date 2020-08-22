package com.example.newyorktouristapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Place.class}, version = 2, exportSchema = false)
public abstract class PlaceRoomDataBase extends RoomDatabase {

    public static String TAG = "PlaceRoomDatabase";
    private static PlaceRoomDataBase INSTANCE;
    public abstract PlaceDao placeDao();
    protected static Context mContext;

    public static PlaceRoomDataBase getDatabase(final Context context) {
        mContext = context;
        Log.d(TAG, "Inside Constructor");
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    PlaceRoomDataBase.class, "place_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(sRoomDataBaseCallBack)
                    .build();
        }
        Log.d(TAG, "End of Constructor");
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDataBaseCallBack = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            Log.d(TAG, "Inside of onOpen method");
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
            Log.d(TAG, "End of onOpen method");
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final PlaceDao myPlaceDao;

        PopulateDbAsync(@NonNull PlaceRoomDataBase db) {
            myPlaceDao = db.placeDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            loadLandmarks(myPlaceDao, mContext);
            loadFun(myPlaceDao, mContext);
            loadFood(myPlaceDao, mContext);
            loadMuseums(myPlaceDao, mContext);

            myPlaceDao.insert(new Place("good burger", "2125555555", true, "landmark"));
            return null;
        }

        private void loadMuseums(PlaceDao myPlaceDao, Context context) {
            TypedArray arrImages = context.getApplicationContext().getResources().obtainTypedArray(R.array.museums_images);
            String[] arrTitles = context.getApplicationContext().getResources().getStringArray(R.array.museums);
            String[] arrDescriptions = context.getApplicationContext().getResources().getStringArray(R.array.museums_description);
            String[] arrNumbers = context.getApplicationContext().getResources().getStringArray(R.array.museums_number);
            String[] arrAddresses = context.getApplicationContext().getResources().getStringArray(R.array.museums_address);
            String[] arrLinks = context.getApplicationContext().getResources().getStringArray(R.array.museums_link);

            for(int i = 0; i < arrTitles.length; i++) {
                myPlaceDao.insert(new Place(arrImages.getResourceId(i, 0), arrTitles[i],
                        arrDescriptions[i], arrNumbers[i], arrAddresses[i], arrLinks[i], false, "museum"));
            }
            arrImages.recycle();
        }

        private void loadFood(PlaceDao myPlaceDao, Context context) {
            TypedArray arrImages = context.getApplicationContext().getResources().obtainTypedArray(R.array.food_images);
            String[] arrTitles = context.getApplicationContext().getResources().getStringArray(R.array.food_titles);
            String[] arrDescriptions = context.getApplicationContext().getResources().getStringArray(R.array.food_description);
            String[] arrReviews = context.getApplicationContext().getResources().getStringArray(R.array.food_review);
            String[] arrNumbers = context.getApplicationContext().getResources().getStringArray(R.array.food_phoneNumber);
            String[] arrAddresses = context.getApplicationContext().getResources().getStringArray(R.array.food_address);
            String[] arrLinks = context.getApplicationContext().getResources().getStringArray(R.array.food_links);

            for(int i = 0; i < arrTitles.length; i++) {
                myPlaceDao.insert(new Place(arrImages.getResourceId(i, 0), arrTitles[i],
                        arrDescriptions[i], arrReviews[i], arrNumbers[i], arrAddresses[i], arrLinks[i], false, "food"));
            }
            arrImages.recycle();
        }

        private void loadFun(PlaceDao myPlaceDao, Context context) {
            TypedArray arrImages = context.getApplicationContext().getResources().obtainTypedArray(R.array.fun_images);
            String[] arrTitles = context.getApplicationContext().getResources().getStringArray(R.array.fun_titles);
            String[] arrDescriptions = context.getApplicationContext().getResources().getStringArray(R.array.fun_descriptions);
            String[] arrReviews = context.getApplicationContext().getResources().getStringArray(R.array.fun_reviews);
            String[] arrNumbers = context.getApplicationContext().getResources().getStringArray(R.array.fun_numbers);
            String[] arrAddresses = context.getApplicationContext().getResources().getStringArray(R.array.fun_addresses);
            String[] arrLinks = context.getApplicationContext().getResources().getStringArray(R.array.fun_links);

            for(int i = 0; i < arrTitles.length; i++) {
                myPlaceDao.insert(new Place(arrImages.getResourceId(i, 0), arrTitles[i],
                        arrDescriptions[i], arrReviews[i], arrNumbers[i], arrAddresses[i], arrLinks[i], false, "fun"));
            }
            arrImages.recycle();
        }

        private void loadLandmarks(PlaceDao myPlaceDao, Context context) {
            TypedArray arrImages = context.getApplicationContext().getResources().obtainTypedArray(R.array.landmark_images);
            String[] arrTitles = context.getApplicationContext().getResources().getStringArray(R.array.landmark_titles);
            String[] arrDescriptions = context.getApplicationContext().getResources().getStringArray(R.array.landmark_descriptions);
            String[] arrReviews = context.getApplicationContext().getResources().getStringArray(R.array.landmark_reviews);
            String[] arrNumbers = context.getApplicationContext().getResources().getStringArray(R.array.landmark_numbers);
            String[] arrAddresses = context.getApplicationContext().getResources().getStringArray(R.array.landmark_addresses);
            String[] arrLinks = context.getApplicationContext().getResources().getStringArray(R.array.landmark_links);

            for(int i = 0; i < arrTitles.length; i++) {
                myPlaceDao.insert(new Place(arrImages.getResourceId(i, 0), arrTitles[i],
                        arrDescriptions[i], arrReviews[i], arrNumbers[i], arrAddresses[i], arrLinks[i], false, "landmark"));
            }
            arrImages.recycle();
        }
    }
}
