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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.alan_walker__faded);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        Button playButton = (Button) findViewById(R.id.play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
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
                        ,audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) + (audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)/15)
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
}
