package com.example.newyorktouristapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class FavoritesReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();
        if (intentAction != null) {
            switch(intentAction) {
                case PlacesDescription.ADD_TO_FAVORITES:
                    openFavoritesList(context);
            }
        }
    }

    private void openFavoritesList(Context context) {
        Intent favoritesIntent = new Intent(context, FavoritesList.class);
        favoritesIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(favoritesIntent);
        Toast.makeText(context, PlacesDescription.placeItemTitle +
                " Was added to your favorites list.", Toast.LENGTH_LONG).show();
    }
}
