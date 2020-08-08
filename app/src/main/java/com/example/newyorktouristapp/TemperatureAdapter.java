package com.example.newyorktouristapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TemperatureAdapter extends ArrayAdapter<Temperature> {
    public TemperatureAdapter(@NonNull Context context, ArrayList<Temperature> weatherArrayList) {
        super(context, 0,weatherArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
          Temperature temp = getItem(position);

          if(convertView == null){
              convertView = LayoutInflater.from(getContext()).inflate(R.layout.weather_list_item,parent,false);
          }
        TextView dateTextView = convertView.findViewById(R.id.tvDate);
        TextView minTextView = convertView.findViewById(R.id.tvLowTemperature);
        TextView maxTextView = convertView.findViewById(R.id.tvHighTemperature);
        //TextView linkTextView = convertView.findViewById(R.id.tvLink);

         dateTextView.setText(temp.getDate());
         minTextView.setText(temp.getMinTemp());
         maxTextView.setText(temp.getMaxTemp());
         //linkTextView.setText(temp.getLink());

        return convertView;
    }

}
