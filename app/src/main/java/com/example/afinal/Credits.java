package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;


public class Credits extends AppCompatActivity {

    private Button finished;
    private LinearLayout names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        //sound.playMenuMusic();


        //Initializes button to the parameters in result.xml
        finished = findViewById(R.id.back2);
    }

    public void goBack(View v) {
finish();
    }

}