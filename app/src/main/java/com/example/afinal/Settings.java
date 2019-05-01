package com.example.afinal;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;


public class Settings extends AppCompatActivity {
    private Button finished;

    private Button characters;

    private TextView settings;
    private Button back;
    private CheckBox music;
    private CheckBox soundeffects;

    private SeekBar volumeSeekbar = null;
    private AudioManager audioManager = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        initControls();
        settings = findViewById(R.id.settings);

        //Initializes button to the parameters in result.xml
        finished = findViewById(R.id.backbutton);


    }

    public void goBack(View v) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void initControls() {
        volumeSeekbar = findViewById(R.id.volumecontrol);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        volumeSeekbar.setMax(audioManager
                .getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        volumeSeekbar.setProgress(audioManager
                .getStreamVolume(AudioManager.STREAM_MUSIC));


        volumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                        progress, 0);
            }
        });
    }
}
