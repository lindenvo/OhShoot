package com.example.afinal;

import android.content.Intent;
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

    private CheckBox music;
    private CheckBox soundeffects;
    private SeekBar volume;


    private SoundPlay sound;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sound = new SoundPlay(this);
        //sound.playMenuMusic();
        settings = findViewById(R.id.settings);

        //Initializes button to the parameters in result.xml
        //finished = findViewById(R.id.back);
    }

    public void goBack(View v) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

}
