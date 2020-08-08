package com.example.newyorktouristapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    protected static List<Place> favoritesData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        favoritesData = new ArrayList<Place>();

        androidx.preference.PreferenceManager
                .setDefaultValues(this,R.xml.prefrences, false);


        SharedPreferences sharedPref = androidx.preference.PreferenceManager
                        .getDefaultSharedPreferences(this);
        sharedPref.getBoolean(SettingsActivity.KEY_NIGHT_MODE_SWITCH, false);

    }

    public void startLandmarkActivity(View view) {
        Intent landMarkIntent = new Intent(this, PlacesForLandmarks.class);
        startActivity(landMarkIntent);
    }

    public void startFunActivity(View view) {
        Intent funIntent = new Intent(this, PlacesForFun.class);
        startActivity(funIntent);
    }

    public void startFoodActivity(View view) {
        Intent foodIntent = new Intent(this, PlacesForFood.class);
        startActivity(foodIntent);
    }

    public void startMuseumActivity(View view) {
        Intent museumIntent = new Intent(this, PlacesForMuseums.class);
        startActivity(museumIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_settings:
                openSettings();
                break;

            case R.id.action_favorite:
                openFavorites();
                break;

            case R.id.action_weather:
                openWeatherActivity();
        }
        return super.onOptionsItemSelected(item);
    }

    private void openWeatherActivity() {
        Intent startWeather = new Intent(this, Weather.class);
        startActivity(startWeather);
    }

    private void openFavorites() {
        Intent startFavorites = new Intent(this, FavoritesList.class);
        startActivity(startFavorites);
    }

    private void openSettings() {
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
    }
}