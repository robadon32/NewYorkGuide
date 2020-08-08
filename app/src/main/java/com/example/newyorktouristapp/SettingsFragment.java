package com.example.newyorktouristapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.prefrences, rootKey);

    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if(s.equals(SettingsActivity.KEY_NIGHT_MODE_SWITCH)) {
            Preference switchPref = findPreference(s);
            assert switchPref != null;
            switchPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if(newValue.equals(true)) {
                        Toast.makeText(getContext(), "Dark Mode has been activated " + newValue, Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(getContext(), "Dark mode has been deactivated " + newValue, Toast.LENGTH_LONG).show();
                    setNightMode();
                    return true;
                }
            });
        }
    }

    private void setNightMode() {

    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }
}