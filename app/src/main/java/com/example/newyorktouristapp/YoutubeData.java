package com.example.newyorktouristapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/*public class YoutubeData extends YouTubeBaseActivity {
    YouTubePlayerView mYouTubePlayerView;
    Button btnPlay;
    YouTubePlayer.OnInitializedListener mOnOnInitializedListener;
    private static final String API_KEY = "AIzaSyBXNiWn2Xm9tOvEl-a-mnV4-bHJs1hV-BU";

    //@Override
    protected void onPlay() {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_places_description);
        btnPlay = (Button) findViewById(R.id.playVideo);
        mYouTubePlayerView = (YouTubePlayerView) findViewById(R.id.videoPlayer);

        mOnOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("N3XlyT4Ums8");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mYouTubePlayerView.initialize(YoutubeData.getApiKey(), mOnOnInitializedListener);
            }
        });
    }

    public YoutubeData(){ }

    public static String getApiKey() {
        return API_KEY;
    }
}*/
