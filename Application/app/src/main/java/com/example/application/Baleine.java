package com.example.application;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class Baleine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baleine);

        getSupportActionBar().hide();

        VideoView videoView = findViewById(R.id.videoView);
        String videoPath = "android.resource://" +getPackageName() + "/" + R.raw.baleine;
        Log.e(TAG, "onCreate: url : " + videoPath);
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.start();

        // Ajouter le FooterFragment dans le conteneur
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.footerFragment, new FooterFragment())
                    .commit();
        }
    }
}