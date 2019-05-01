package com.example.afinal;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Play extends AppCompatActivity {

    private TextView scoreLabel;
    private TextView startLabel;
    private ImageView stringhini;
    private ImageView bettereric;
    private ImageView gooderic;
    private ImageView baderic;
    private ImageView sleepyeric;

    // Size
    private int frameWidth;
    private int frameHeight;
    private int stringhiniSize;
    private int screenWidth;
    private int screenHeight;

    // Position
    private int stringhiniY;
    private int goodericY;
    private int goodericX;
    private int betterericX;
    private int betterericY;
    private int badericX;
    private int badericY;
    private int sleepyericX;
    private int sleepyericY;

    // Score
    private int score = 0;

    // Initalize Class
    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private SoundPlay sound;

    // Status Check
    private boolean action_flg = false;
    private boolean start_flg = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        sound = new SoundPlay(this);
        sound.playGameMusic();

        scoreLabel = findViewById(R.id.scoreLabel);
        startLabel = findViewById(R.id.startLabel);
        stringhini = findViewById(R.id.stringhini);
        bettereric = findViewById(R.id.bettereric);
        gooderic = findViewById(R.id.gooderic);
        baderic = findViewById(R.id.baderic);
        sleepyeric = findViewById(R.id.sleepyeric);

        // Get screen size
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;

        // Move out of screen
        bettereric.setX(-1000000000);
        bettereric.setY(screenHeight + 80);
        gooderic.setX(-1000000000);
        gooderic.setY(-1000000000);
        baderic.setX(-1000000000);
        baderic.setY(-1000000000);
        sleepyeric.setX(-1000000000);
        sleepyeric.setY(-1000000000);

        scoreLabel.setText("Score: 0");
    }

    public void changePos() {

        hitCheck();

        // gooderic
        goodericX -= 15;
        if (goodericX < 0) {
            goodericX = screenWidth + 20;
            goodericY = (int) Math.floor(Math.random() * (frameHeight - gooderic.getHeight()));
        }
        gooderic.setX(goodericX);
        gooderic.setY(goodericY);


        // baderic
        badericX -= 18;
        if (badericX < 0) {
            badericX = screenWidth + 10;
            badericY = (int) Math.floor(Math.random() * (frameHeight - baderic.getHeight()));
        }
        baderic.setX(badericX);
        baderic.setY(badericY);

        // bettereric
        betterericY += 25;
        if (betterericY > 0) {
            betterericY = screenWidth - 5000;
            betterericX = (int) Math.floor(Math.random() * (frameWidth - bettereric.getWidth()));
        }
        bettereric.setX(betterericX);
        bettereric.setY(betterericY);

        // sleepyeric
        sleepyericY -= 25;
        if (sleepyericY < 0) {
            sleepyericY = screenWidth + 500;
            sleepyericX = (int) Math.floor(Math.random() * (frameHeight - sleepyeric.getHeight()));
        }
        sleepyeric.setX(sleepyericX);
        sleepyeric.setY(sleepyericY);


        // Move stringhini
        if (action_flg == true) {
            // Touching
            stringhiniY -= 20;
        } else {
            // Releasing
            stringhiniY += 20;
        }

        // Check box position
        if (stringhiniY < 0) stringhiniY = 0;

        if (stringhiniY > frameHeight - stringhiniSize) stringhiniY = frameHeight - stringhiniSize;

        stringhini.setY(stringhiniY);

        scoreLabel.setText("Score: " + score);
    }

    public void hitCheck() {


        // gooderic
        int goodericCenterX = goodericX + gooderic.getWidth() / 2;
        int goodericCenterY = goodericY + gooderic.getHeight() / 2;

        // 0 <= goodericCenterX <= stringhiniWidth
        // stringhiniY <= goodericCenterY <= stringhiniY + stringhiniHeight

        if (0 <= goodericCenterX && goodericCenterX <= stringhiniSize && stringhiniY <= goodericCenterY && goodericCenterY <= stringhiniY + stringhiniSize) {
            score += 10;
            goodericX = -10;
        }

        // bettereric
        int betterericCenterX = betterericX + bettereric.getWidth() / 2;
        int betterericCenterY = betterericY + bettereric.getHeight() / 2;

        if (0 <= betterericCenterX && betterericCenterX <= stringhiniSize && stringhiniY <= betterericCenterY && betterericCenterY <= stringhiniY + stringhiniSize) {
            score += 50;
            betterericX = -10;
        }

        // sleepyeric
        int sleepyericCenterX = sleepyericX + sleepyeric.getWidth() / 2;
        int sleepyericCenterY = sleepyericY + sleepyeric.getHeight() / 2;

        if (0 <= sleepyericCenterX && sleepyericCenterX <= stringhiniSize && stringhiniY <= sleepyericCenterY && sleepyericCenterY <= stringhiniY + stringhiniSize) {
            score -= 50;
            betterericX = -10;
        }

        // baderic
        int badericCenterX = badericX + baderic.getWidth() / 2;
        int badericCenterY = badericY + baderic.getHeight() / 2;

        if (0 <= badericCenterX && badericCenterX <= stringhiniSize && stringhiniY <= badericCenterY && badericCenterY <= stringhiniY + stringhiniSize) {
            // Stop Timer!!
            timer.cancel();
            timer = null;

            // Show result
            Intent intent = new Intent(getApplicationContext(), result.class);
            intent.putExtra("SCORE", score);
            startActivity(intent);
            //score -= 30;
            //badericX = -10;
        }
    }

    public boolean onTouchEvent(MotionEvent me) {

        if (start_flg == false) {
            start_flg = true;
            sound.playGameMusic();


            FrameLayout frame = findViewById(R.id.frame);
            frameHeight = frame.getHeight();
            frameWidth = frame.getWidth();

            stringhiniY = (int) stringhini.getY();

            // stringhini is a square
            stringhiniSize = stringhini.getHeight();

            startLabel.setVisibility(View.GONE);

            // Start the timer
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            }, 0, 20);
        } else {
            if (me.getAction() == MotionEvent.ACTION_DOWN) {
                action_flg = true;
            } else if (me.getAction() == MotionEvent.ACTION_UP) {
                action_flg = false;
            }
        }

        return true;
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
