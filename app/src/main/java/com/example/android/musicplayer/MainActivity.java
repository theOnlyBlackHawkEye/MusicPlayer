package com.example.android.musicplayer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("MainActivity", "onCreate");


        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        Button playButton = (Button) findViewById(R.id.play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.number_one);
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(mCompletionListener);
                Log.v("MainActivity", "Resources been Released After Completion");
            }
        });


        Button pauseButton = (Button) findViewById(R.id.pause_button);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });


        Button volumeUpButton = (Button) findViewById(R.id.volume_up__button);
        volumeUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC
                        , audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) +
                                (audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC) / 15)
                        , AudioManager.FLAG_PLAY_SOUND);
                Log.v("MainActivity", "streamVolume = " + audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
            }
        });


        Button volumeDownButton = (Button) findViewById(R.id.volume_down__button);
        volumeDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC
                        ,audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) - (audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)/15)
                        , AudioManager.FLAG_PLAY_SOUND);
                Log.v("MainActivity", "streamVolume = " + audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
            }
        });


    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("MainActivity", "onStart");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.v("MainActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("MainActivity", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("MainActivity", "onDestroy");
    }
}
