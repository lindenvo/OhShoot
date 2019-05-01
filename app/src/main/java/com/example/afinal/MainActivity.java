package com.example.afinal;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonPLAY;
    private Button buttonSETTINGS;
    private Button buttonCREDITS;
    MediaPlayer menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menu = MediaPlayer.create(getApplicationContext(), R.raw.menu);

        menu.setVolume((float)0.75,(float)0.75 );

        menu.start();
        menu.setLooping(true);


        buttonPLAY = findViewById(R.id.buttonPLAY);
        buttonSETTINGS = findViewById(R.id.buttonSETTINGS);
        buttonCREDITS = findViewById(R.id.buttonCREDITS);

        buttonCREDITS.setOnClickListener(this);
        buttonSETTINGS.setOnClickListener(this);
        buttonPLAY.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonPLAY: {
                menu.stop();
                launchplayActivity();
                break;
            }

            case R.id.buttonSETTINGS: {
                launchsettingsActivity();
                break;
            }

            case R.id.buttonCREDITS: {
                launchcreditsActivity();
                break;
            }

        }
    }


    private void launchplayActivity() {

        Intent playActivity = new Intent(MainActivity.this, Play.class);

        //Launches the new activity
        startActivity(playActivity);
    }


    private void launchsettingsActivity() {

        Intent settingsActivity = new Intent(MainActivity.this, Settings.class);

        //Launches the new activity
        startActivity(settingsActivity);
    }


    private void launchcreditsActivity() {

        Intent creditsActivity = new Intent(MainActivity.this, Credits.class);

        //Launches the new activity
        startActivity(creditsActivity);
    }

    // Disable Return Button
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_BACK:
                    return true;
            }
        }

        return super.dispatchKeyEvent(event);
    }
}
