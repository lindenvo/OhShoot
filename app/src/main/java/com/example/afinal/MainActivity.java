package com.example.afinal;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonPLAY;
    private Button buttonSETTINGS;
    private Button buttonCREDITS;

    private SoundPlay sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sound = new SoundPlay(this);
        sound.playMenuMusic();

        buttonPLAY = findViewById(R.id.buttonPLAY);
        buttonSETTINGS = findViewById(R.id.buttonSETTINGS);
        //instructions_button = (Button) findViewById(R.id.instructions);
        buttonCREDITS = findViewById(R.id.buttonCREDITS);

        buttonCREDITS.setOnClickListener(this);
        buttonSETTINGS.setOnClickListener(this);
        //instructions_button.setOnClickListener(this);
        buttonPLAY.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonPLAY: {
                launchplayActivity();
                break;
            }

            case R.id.buttonSETTINGS: {
                launchsettingsActivity();
                break;
            }
/*
            case R.id.buttonINSTRUCTIONS:
            {
                launchinstructionsActivity();
                break;
            }
*/
            case R.id.buttonCREDITS: {
                launchcreditsActivity();
                break;
            }

        }
    }

    /* The launchxxxxxxActivity method is used to start a new activity from within an onClickListener
     * */
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

    /*    private void launchinstructionsActivity()
        {

            Intent instructionsActivity = new Intent(MainActivity.this, Instructions.class);

            //Launches the new activity
            startActivity(instructionsActivity);
        }
        */
    private void launchcreditsActivity() {

        Intent creditsActivity = new Intent(MainActivity.this, Credits.class);

        //Launches the new activity
        startActivity(creditsActivity);
    }

}
