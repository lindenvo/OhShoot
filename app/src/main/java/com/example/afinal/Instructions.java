package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Instructions extends AppCompatActivity {

    private Button finished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        //sound.playMenuMusic();


        //Initializes button to the parameters in result.xml
        finished = findViewById(R.id.back3);
    }

    public void goBack(View v) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

}