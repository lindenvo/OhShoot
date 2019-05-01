package com.example.afinal;

import android.content.Context;
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
    private TextView settings;
    private SeekBar volumeSeekbar = null;
    private AudioManager audioManager = null;
    boolean checked = true;
    private CheckBox soundEnable;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        initControls();
        soundEnable = findViewById(R.id.sound);
        settings = findViewById(R.id.settings);

        //Initializes button to the parameters in result.xml
        finished = findViewById(R.id.backbutton);

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

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sound:
                if (checked) {
                    checked = false;
                    audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                            AudioManager.ADJUST_MUTE, 0);
                    volumeSeekbar.setEnabled(false);

                } else {

                    checked = true;
                    audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                            AudioManager.ADJUST_UNMUTE, 0);
                    volumeSeekbar.setEnabled(true);
                    volumeSeekbar.setMax(audioManager
                            .getStreamMaxVolume(AudioManager.STREAM_MUSIC));
                    volumeSeekbar.setProgress(audioManager
                            .getStreamVolume(AudioManager.STREAM_MUSIC));
                }
                break;
        }
    }
    public void goBack(View v) {
        finish();
    }

   /* public void mutemusic(){
        if (music.isChecked() == true) {
            audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                    AudioManager.ADJUST_UNMUTE, 0);
            volumeSeekbar.setMax(audioManager
                    .getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            volumeSeekbar.setProgress(audioManager
                    .getStreamVolume(AudioManager.STREAM_MUSIC));
        }
        else{
            audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                    AudioManager.ADJUST_MUTE, 0);
            volumeSeekbar.setEnabled(false);
        }
    }*/
}
