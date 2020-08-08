package com.example.newyorktouristapp;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetWorkUtils {
    private final static String WEATHER_BASE_URL = "https://dataservice.accuweather.com/forecasts/v1/daily/5day/2227084";

    private final static String API_KEY = "bhlMfhS3fdWSETG8bl31dWM4EOqGBelw";

    private final static String PARAM_API_KEY = "apikey";

    private static final String TAG = "NetworkUtils";

    private static final String PARAM_DATE= "metric";

    private static final String DATE = "######0";

    //URL used to search for weather JSON
    public static URL buildUrlForWeather(){
        Uri builtUri = Uri.parse(WEATHER_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_API_KEY, API_KEY)
                .appendQueryParameter(PARAM_DATE,DATE)
                .build();

        URL url = null;

        try{
           url = new URL(builtUri.toString());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }

        Log.i(TAG, "buildUrlForWeather: url: " + url);
        return url;
    }

    //This will take user input and search for weather based on city input
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in  = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if(hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
