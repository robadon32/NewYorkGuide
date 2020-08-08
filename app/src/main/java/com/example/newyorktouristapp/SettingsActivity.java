package com.example.newyorktouristapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.SwitchPreferenceCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    protected static String KEY_NIGHT_MODE_SWITCH = "night_mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();

    }
}