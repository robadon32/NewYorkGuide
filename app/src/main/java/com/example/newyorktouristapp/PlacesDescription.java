package com.example.newyorktouristapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ShareCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
//import com.google.android.youtube.player.YouTubeBaseActivity;
//import com.google.android.youtube.player.YouTubeInitializationResult;
//import com.google.android.youtube.player.YouTubePlayer;
//import com.google.android.youtube.player.YouTubePlayerFragment;
//import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

public class PlacesDescription extends AppCompatActivity {

    protected static final String ADD_TO_FAVORITES =
            BuildConfig.APPLICATION_ID + ".ADD_TO_FAVORITES";

    private TextView descriptionView, reviewView;
    private ImageView imageView;
    protected static String placeItemTitle;
    private Menu menu;
    private FavoritesReceiver favoritesReceiver;
    public static final int REQUEST_CALL_PHONE = 2;
    protected Boolean isFavorite;
   //YouTubePlayerView mYouTubePlayerView;
   //Button btnPlay;
   //YouTubePlayer.OnInitializedListener mOnOnInitializedListener;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_places_description);

        favoritesReceiver = new FavoritesReceiver();

        setReferences();

        placeItemTitle = getIntent().getStringExtra(PlaceAdapter.TITLE_EXTRA_KEY);
        this.setTitle(placeItemTitle);

        descriptionView.setText(getIntent().getStringExtra(PlaceAdapter.DESCRIPTION_EXTRA_KEY));
//        reviewView.setText((getIntent().getStringExtra("review")));
        Glide.with(this).load(getIntent().getIntExtra("image", 0)).into(imageView);



        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ADD_TO_FAVORITES);
        this.registerReceiver(favoritesReceiver, intentFilter);
        LocalBroadcastManager.getInstance(this).registerReceiver
                (favoritesReceiver, new IntentFilter(ADD_TO_FAVORITES));
    }

    private void setReferences() {
        descriptionView = findViewById(R.id.detail_description);
        imageView = findViewById(R.id.detail_image);
        reviewView = findViewById(R.id.detail_review);
    }

    public void callPlace(View view) {
        String phoneNumber = getIntent().getStringExtra("number");
        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:" + phoneNumber));
        if(phoneIntent.resolveActivity(getPackageManager())  != null){
            //dialer app is available for usage
            //need to check permissions
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE);
                //need to override onRequestPermissionsResult method for when the REQUEST_CALL_PHONE triggers the callback method
            }
            else{//have permission to CALL_PHONE so we can start the activity by passing in the intent
                startActivity(phoneIntent);
            }
        }else{
            Toast.makeText(this, "Cannot place phone call", Toast.LENGTH_LONG).show();
        }
    }

    public void getDirection(View view) {
        String direction = getIntent().getStringExtra("address");
        Intent addressIntent = new Intent(Intent.ACTION_VIEW);
        addressIntent.setData(Uri.parse("geo:0,0?q=" + direction));
        startActivity(addressIntent);
    }

    public void goToWebsite(View view) {
        String website = getIntent().getStringExtra("link");
        Intent webIntent = new Intent(Intent.ACTION_VIEW);
        webIntent.setData(Uri.parse(website));
        startActivity(webIntent);
    }

    public void getWeather(View view){
        Intent weatherIntent = new Intent(PlacesDescription.this, Weather.class);
        startActivity(weatherIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.description_toolbar_options, menu);
        this.menu = menu;
        isFavorite = getIntent().getBooleanExtra(PlaceAdapter.PLACE_EXTRA_KEY, true );
        if(isFavorite) {
            menu.getItem(1).setIcon(R.drawable.favorite_icon_red);
        }
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                sendText();
                break;
            case R.id.favorite:
                if (!isFavorite) {
                    item.setIcon(R.drawable.favorite_icon_red);
                    addToFavoritesList();
                    sendFavoritesBroadcast();
                    isFavorite = true;
                } else {
                    item.setIcon(R.drawable.favorite_icon);
                    isFavorite = false;
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void sendFavoritesBroadcast() {
        Intent broadcastIntent = new Intent(ADD_TO_FAVORITES);
        LocalBroadcastManager.getInstance(this)
                .sendBroadcast(broadcastIntent);
    }

    private void deleteFromFavoritesList() {
        MainActivity.favoritesData.remove(getIntent().getIntExtra(PlaceAdapter.PLACE_EXTRA_KEY, 0));
    }

    private void addToFavoritesList() {

        MainActivity.favoritesData.add(new Place(placeItemTitle,
                    getIntent().getStringExtra("number")));
        Log.d("PlacesDescription", MainActivity.favoritesData.get(0) + "");
        Log.d("PlacesDescription", " "+ PlaceAdapter.placeData.get(0) );

    }

    private void sendText() {
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Pick an app")
                .setText("Hey lets go check out " + getIntent().getStringExtra("title") + " It's one of the " +
                        "top places to visit in New York City. Check out their website " +
                        getIntent().getStringExtra("link"))
                .startChooser();
    }

    @Override
    protected void onDestroy() {
        this.unregisterReceiver(favoritesReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(favoritesReceiver);
        super.onDestroy();
    }

}