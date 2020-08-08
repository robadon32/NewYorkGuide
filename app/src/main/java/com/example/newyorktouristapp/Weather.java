package com.example.newyorktouristapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class Weather extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ArrayList<Temperature> weatherArrayList = new ArrayList<>();
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
    listView = (ListView) findViewById(R.id.WeatherListView);

    URL weatherUrl = NetWorkUtils.buildUrlForWeather();
    new FetchWeatherDetails().execute(weatherUrl);
    Log.i(TAG, "onCreate: weatherUrl: " + weatherUrl);
    }


    private class FetchWeatherDetails extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL weatherUrl = urls[0];
            String weatherSearchResults = null;

            try {
                weatherSearchResults = NetWorkUtils.getResponseFromHttpUrl(weatherUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.i(TAG, "doInBackground: weatherSearchResults: " + weatherSearchResults);
            return weatherSearchResults;
        }

        @Override
        protected void onPostExecute(String weatherSearchResults) {
            if(weatherSearchResults != null && !weatherSearchResults.equals("")) {
                weatherArrayList = parseJSON(weatherSearchResults);

            }
            super.onPostExecute(weatherSearchResults);
        }
    }

    private ArrayList<Temperature> parseJSON(String weatherSearchResults) {
        if(weatherArrayList != null) {
            weatherArrayList.clear();
        }

        if(weatherSearchResults != null) {
            try {
                JSONObject rootObject = new JSONObject(weatherSearchResults);
                JSONArray results = rootObject.getJSONArray("DailyForecasts");

                for (int i = 0; i < results.length(); i++) {
                    Temperature temp = new Temperature();

                    JSONObject resultsObj = results.getJSONObject(i);

                    String date = resultsObj.getString("Date");
                    temp.setDate(date);

                    JSONObject temperatureObj = resultsObj.getJSONObject("Temperature");
                    String minTemperature = temperatureObj.getJSONObject("Minimum").getString("Value");
                    temp.setMinTemp(minTemperature);

                    String maxTemperature = temperatureObj.getJSONObject("Maximum").getString("Value");
                    temp.setMaxTemp(maxTemperature);

                    String link = resultsObj.getString("Link");
                    temp.setLink(link);

                    weatherArrayList.add(temp);
                }

                if(weatherArrayList != null) {
                  TemperatureAdapter tempAdapter = new TemperatureAdapter(this, weatherArrayList);
                    listView.setAdapter(tempAdapter);
                }

                return weatherArrayList;

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    public void weatherClick(View View){
        Intent browse = new Intent( Intent.ACTION_VIEW , Uri.parse(weatherArrayList.get(1).getLink()));

        startActivity( browse );
    }
}