
package com.example.afinal;

import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

//Eric's Stuff
import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.PointF;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;

public class Play extends AppCompatActivity {

    private TextView scoreLabel;
    private TextView startLabel;
    private ImageView stringhini;
    private ImageView bettereric;
    private ImageView gooderic;
    private ImageView goodericLR;
    private ImageView goodericUD;
    private ImageView goodericDU;
    private ImageView baderic;
    private ImageView badericLR;
    private ImageView badericUD;
    private ImageView badericDU;
    private ImageView sleepyeric;

    private ImageView Life1;
    private ImageView Life2;
    private ImageView Life3;

    private ImageView Death1;
    private ImageView Death2;
    private ImageView Death3;

    // Eric's Stuff
    ViewGroup mainLayout;
    MediaPlayer game;
    MediaPlayer ow;
    MediaPlayer impact;
    MediaPlayer hurt;
    MediaPlayer good;
    // Size
    private int frameWidth;
    private int frameHeight;
     int stringhiniSize;
    private int screenWidth;
    private int screenHeight;

    // Position
    private int stringhiniX;
    private int stringhiniY;
    private int goodericY;
    private int goodericX;
    private int goodericLRY;
    private int goodericLRX;
    private int goodericUDY;
    private int goodericUDX;
    private int goodericDUY;
    private int goodericDUX;
    private int betterericX;
    private int betterericY;
    private int badericX;
    private int badericY;
    private int badericLRX;
    private int badericLRY;
    private int badericUDX;
    private int badericUDY;
    private int badericDUX;
    private int badericDUY;

    private int sleepyericX;
    private int sleepyericY;



    private int Redones = 1;

    private double Speed = 1;

    private int Offset = 0;

    private int LIVES = 3;

    private int red2 = 0;
    private int red3 = 0;
    private int red4 = 0;


    // Score
    private int score = 0;

    // Initalize Class
    private Handler handler = new Handler();
    private Timer timer = new Timer();

    // Status Check
    private boolean action_flg = false;
    private boolean start_flg = false;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);


        scoreLabel = findViewById(R.id.scoreLabel);
        startLabel = findViewById(R.id.startLabel);
        stringhini = findViewById(R.id.stringhini);
        bettereric = findViewById(R.id.bettereric);
        gooderic =  findViewById(R.id.gooderic);
        goodericLR = findViewById(R.id.goodericlr);
        goodericUD =  findViewById(R.id.goodericud);
        goodericDU =  findViewById(R.id.goodericdu);
        baderic =  findViewById(R.id.baderic);
        badericLR =  findViewById(R.id.badericlr);
        badericUD =  findViewById(R.id.badericud);
        badericDU =  findViewById(R.id.badericdu);
        sleepyeric =  findViewById(R.id.sleepyeric);

        Life1 =  findViewById(R.id.life1);
        Life2 =  findViewById(R.id.life2);
        Life3 =  findViewById(R.id.life3);

        Death1 =  findViewById(R.id.death1);
        Death2 =  findViewById(R.id.death2);
        Death3 =  findViewById(R.id.death3);



        // Get screen size
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;



        // Move out of screen
        stringhini.setX(screenWidth/3);
        stringhini.setY(screenHeight/99999);
        bettereric.setX(-1000000000);
        bettereric.setY(screenHeight + 80);
        gooderic.setX(-1000000000);
        gooderic.setY(-1000000000);
        goodericLR.setX(-1000000000);
        goodericLR.setY(-1000000000);
        goodericUD.setX(-1000000000);
        goodericUD.setY(-1000000000);
        goodericDU.setX(-1000000000);
        goodericDU.setY(-1000000000);
        baderic.setX(-1000000000);
        baderic.setY(-1000000000);
        badericLR.setX(-1000000000);
        badericLR.setY(-1000000000);
        badericUD.setX(-1000000000);
        badericUD.setY(-1000000000);
        badericDU.setX(-1000000000);
        badericDU.setY(-1000000000);
        sleepyeric.setX(-1000000000);
        sleepyeric.setY(-1000000000);

        scoreLabel.setText("Score: 0");

        Life1.setX(700);
        Life1.setY(0);
        Life2.setX(810);
        Life2.setY(0);
        Life3.setX(920);
        Life3.setY(0);

        Death1.setX(-1000);
        Death1.setY(0);
        Death2.setX(-1000);
        Death2.setY(0);
        Death3.setX(-1000);
        Death3.setY(0);

        //Eric's Stuff

        mainLayout = findViewById(R.id.frame);
        stringhini.setOnTouchListener(onTouchListener());

        game = MediaPlayer.create(getApplicationContext(), R.raw.gamemusic);
        ow = MediaPlayer.create(getApplicationContext(), R.raw.ow);
        impact = MediaPlayer.create(getApplicationContext(), R.raw.impact);
        hurt = MediaPlayer.create(getApplicationContext(), R.raw.hurt);
        good = MediaPlayer.create(getApplicationContext(), R.raw.good);

        game.setVolume((float)0.75,(float)0.75 );

        game.start();
        game.setLooping(true);
    }


    // Eric's Stuff
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }


    private OnTouchListener onTouchListener() {
        return new OnTouchListener() {
            PointF DownPT = new PointF(); // Record Mouse Position When Pressed Down
            PointF StartPT = new PointF(); // Record Start Position of 'img'





            int width = getScreenWidth() - 40;
            int height = getScreenHeight() - 250;

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                switch (event.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:
                        DownPT.set( event.getX(), event.getY() );
                        StartPT.set( stringhini.getX(), stringhini.getY() );

                        break;

                    case MotionEvent.ACTION_UP:
                        break;

                    case MotionEvent.ACTION_MOVE:
                        stringhini.setX((int)(StartPT.x + event.getX() - DownPT.x));
                        stringhini.setY((int)(StartPT.y + event.getY() - DownPT.y));

                        stringhiniY = (int)(StartPT.y + event.getY() - DownPT.y);
                        stringhiniX = (int)(StartPT.x + event.getX() - DownPT.x);

                        StartPT.set( stringhini.getX(), stringhini.getY() );

                        if (stringhini.getX() < 0) {
                            stringhini.setX(0);
                        }
                        else if (stringhini.getX() > width){
                            stringhini.setX(width);
                        }

                        if (stringhini.getY() < 0) {
                            stringhini.setY(0);
                        }
                        else if (stringhini.getY() > height){
                            stringhini.setY(height);
                        }

                        break;
                }
                return true;
            }
        };
    }

    public void changePos() {

        hitCheck();

        // gooderic
        goodericX -= 15 * Speed;
        goodericY += ((-.5 + (int) Math.random()) * Offset);
        if (goodericX < 0) {
            goodericX = screenWidth + 1000 + 2500 * (int) Math.random();
            goodericY = (int) Math.floor(Math.random()*(frameHeight - gooderic.getHeight()));
        }
        gooderic.setX(goodericX);
        gooderic.setY(goodericY);

        // goodericLR
        goodericLRX += 15 * Speed;
        goodericLRY += ((-.5 + (int) Math.random()) * Offset);
        if (goodericLRX > 1500) {
            goodericLRX = -1000 - 2500 * (int) Math.random();
            goodericLRY = (int) Math.floor(Math.random()*(frameHeight - goodericLR.getHeight()));
        }
        goodericLR.setX(goodericLRX);
        goodericLR.setY(goodericLRY);

        // goodericUD
        goodericUDY += 15 * Speed;
        goodericUDX += ((-.5 + (int) Math.random()) * Offset);
        if (goodericUDY > 2500) {
            goodericUDY = -1000 - (int) Math.random();
            goodericUDX = (int) Math.floor(Math.random()*(frameWidth - goodericUD.getWidth()));
        }
        goodericUD.setX(goodericUDX);
        goodericUD.setY(goodericUDY);

        // goodericDU
        goodericDUX -= 15 * Speed;
        goodericDUY += ((-.5 + (int) Math.random()) * Offset);
        if (goodericDUY < 0) {
            goodericDUY = screenHeight + 1000 + (int) Math.random();
            goodericDUX = (int) Math.floor(Math.random()*(frameWidth - goodericDU.getWidth()));
        }
        goodericDU.setX(goodericDUX);
        goodericDU.setY(goodericDUY);


        // baderic
        badericX -= 20 * Speed;
        badericY += ((-.5 + (int) Math.random()) * Offset);
        if(badericX < 0)
        {
            badericX = screenWidth + 3000 + 2500 * (int) Math.random();
            badericY = (int) Math.floor(Math.random()*(frameHeight - baderic.getHeight()));
        }
        baderic.setX(badericX);
        baderic.setY(badericY);

        // badericLR
        badericLRX += 20 * Speed * red2;
        badericLRY += ((-.5 + (int) Math.random()) * Offset * red2);
        if (badericLRX > 1500) {
            badericLRX = -3000 - 2500 * (int) Math.random();
            badericLRY = (int) Math.floor(Math.random()*(frameHeight - badericLR.getHeight()));
        }
        badericLR.setX(badericLRX);
        badericLR.setY(badericLRY);

        // badericUD
        badericUDY += 20 * Speed * red3;
        badericUDX += ((-.5 + (int) Math.random()) * Offset * red3);
        if(badericUDY > 2500)
        {
            badericUDY = -3000 - (int) Math.random();
            badericUDX = (int) Math.floor(Math.random()*(frameWidth - badericUD.getWidth()));
        }
        badericUD.setX(badericUDX);
        badericUD.setY(badericUDY);

        // badericDU
        badericDUY -= 20 * Speed * red4;
        badericDUX += ((-.5 + (int) Math.random()) * Offset * red4);
        if(badericDUY < 0)
        {
            badericDUY = screenHeight + 3000 + (int) Math.random();
            badericDUX = (int) Math.floor(Math.random()*(frameWidth - baderic.getWidth()));
        }
        badericDU.setX(badericDUX);
        badericDU.setY(badericDUY);

        // bettereric
        betterericY += 40 * Speed;
        if (betterericY > 2000)
        {
            betterericY = -10000;
            betterericX = (int) Math.floor(Math.random()*(frameWidth - bettereric.getWidth()));
        }
        bettereric.setX(betterericX);
        bettereric.setY(betterericY);


        // sleepyeric
        sleepyericY -= 25 * Speed;
        if (sleepyericY < 0)
        {
            sleepyericY = screenHeight + 500;
            sleepyericX = (int) Math.floor(Math.random()*(frameWidth - sleepyeric.getWidth()));
        }
        sleepyeric.setX(sleepyericX);
        sleepyeric.setY(sleepyericY);


        switch (LIVES)

        {
            case 3:
                Life1.setX(700);
                Life1.setY(0);
                Life2.setX(810);
                Life2.setY(0);
                Life3.setX(920);
                Life3.setY(0);
                Death1.setX(-1000);
                Death1.setY(0);
                Death2.setX(-1000);
                Death2.setY(0);
                Death3.setX(-1000);
                Death3.setY(0);
                break;

            case 2:
                Life1.setX(-1000);
                Life1.setY(0);
                Life2.setX(810);
                Life2.setY(0);
                Life3.setX(920);
                Life3.setY(0);
                Death1.setX(700);
                Death1.setY(0);
                Death2.setX(-1000);
                Death2.setY(0);
                Death3.setX(-1000);
                Death3.setY(0);
                break;

            case 1:
                Life1.setX(-1000);
                Life1.setY(0);
                Life2.setX(-1000);
                Life2.setY(0);
                Life3.setX(920);
                Life3.setY(0);
                Death1.setX(700);
                Death1.setY(0);
                Death2.setX(810);
                Death2.setY(0);
                Death3.setX(-1000);
                Death3.setY(0);
                break;

            case 0:
                // Stop Timer!!
                timer.cancel();
                game.stop();
                // Show result
                Intent intent = new Intent(getApplicationContext(), result.class);
                intent.putExtra("SCORE", score);
                startActivity(intent);
                break;
        };

        //LEVEL 1
        if (score <= 50)
        {
            Redones = 1;
            Speed = 1;
            Offset = 0;
            red2 = 0;
            red3 = 0;
            red4 = 0;
            badericLR.setX(-1000);
            badericUD.setX(-1000);
            badericDU.setX(-1000);
        }

        //LEVEL 2
        if ((50 < score)&&(score <= 100))
        {
            Redones = 2;
            Speed = 1;
            Offset = 0;
            red2 = 1;
            red3 = 0;
            red4 = 0;
            badericUD.setX(-1000);
            badericDU.setX(-1000);
        }

        //LEVEL 3
        if ((100 < score)&&(score <= 150))
        {
            Redones = 3;
            Speed = 1;
            Offset = 0;
            red2 = 1;
            red3 = 1;
            red4 = 0;
            badericDU.setX(-1000);
        }

        //LEVEL 4
        if ((150 < score)&&(score <= 200))
        {
            Redones = 4;
            Speed = 1;
            Offset = 0;
            red2 = 1;
            red3 = 1;
            red4 = 1;
        }

        //LEVEL 5
        if ((200 < score)&&(score <= 300))
        {
            Redones = 4;
            Speed = 1.25;
            Offset = 2;
            red2 = 1;
            red3 = 1;
            red4 = 1;
        }

        //LEVEL 6
        if ((300 < score)&&(score <= 400))
        {
            Redones = 4;
            Speed = 1.5;
            Offset = 4;
            red2 = 1;
            red3 = 1;
            red4 = 1;
        }

        //LEVEL 7
        if ((400 < score)&&(score <= 500))
        {
            Redones = 4;
            Speed = 2;
            Offset = 8;
            red2 = 1;
            red3 = 1;
            red4 = 1;
        }




        // Move stringhini

        /* //Eric removed.
        if (action_flg == true){
            // Touching
            stringhiniY -= 20;
        } else {
            // Releasing
            stringhiniY += 20;
        }


        // Check box position
        if (stringhiniY < 0) stringhiniY =0;

        if (stringhiniY > frameHeight - stringhiniSize)stringhiniY = frameHeight - stringhiniSize;

        stringhini.setY(stringhiniY);
        */

        scoreLabel.setText("Score: " + score);
    }

    public void hitCheck()
    {
        //stringhini center
        int stringhiniCenterX = stringhiniX + stringhini.getWidth() / 2;
        int stringhiniCenterY = stringhiniY + stringhini.getHeight() /2;

        //gooderic
        int goodericCenterX = goodericX + gooderic.getWidth() / 2;
        int goodericCenterY = goodericY + gooderic.getHeight() /2;

        //goodericLR
        int goodericCenterLRX = goodericLRX + goodericLR.getWidth() / 2;
        int goodericCenterLRY = goodericLRY + goodericLR.getHeight() /2;

        //goodericUD
        int goodericCenterUDX = goodericUDX + goodericUD.getWidth() / 2;
        int goodericCenterUDY = goodericUDY + goodericUD.getHeight() /2;

        //goodericDU
        int goodericCenterDUX = goodericDUX + goodericDU.getWidth() / 2;
        int goodericCenterDUY = goodericDUY + goodericDU.getHeight() /2;

        // bettereric
        int betterericCenterX = betterericX + bettereric.getWidth() /2;
        int betterericCenterY = betterericY + bettereric.getHeight() /2;

        // sleepyeric
        int sleepyericCenterX = sleepyericX + sleepyeric.getWidth() /2;
        int sleepyericCenterY = sleepyericY + sleepyeric.getHeight() /2;

        // baderic
        int badericCenterX = badericX + baderic.getWidth() /2;
        int badericCenterY = badericY + baderic.getHeight() /2;

        // badericLR
        int badericCenterLRX = badericLRX + badericLR.getWidth() /2;
        int badericCenterLRY= badericLRY + badericLR.getHeight() /2;

        // badericUD
        int badericCenterUDX = badericUDX + badericUD.getWidth() /2;
        int badericCenterUDY = badericUDY + badericUD.getHeight() /2;

        // badericDU
        int badericCenterDUX = badericDUX + badericDU.getWidth() /2;
        int badericCenterDUY = badericDUY + badericDU.getHeight() /2;

        //HITBOX SIZE
        int reghitbox =200;
        int smallhitbox = 100;



        if (reghitbox >= (Math.abs(goodericCenterX-stringhiniCenterX) + Math.abs(goodericCenterY-stringhiniCenterY)))
        {
            score += 10;
            goodericX = -10;
            impact.start();

        }

        if (reghitbox >= (Math.abs(goodericCenterLRX-stringhiniCenterX) + Math.abs(goodericCenterLRY-stringhiniCenterY)))
        {
            score += 10;
            goodericLRX = 1501;
            impact.start();

        }

        if (reghitbox >= (Math.abs(goodericCenterUDX-stringhiniCenterX) + Math.abs(goodericCenterUDY-stringhiniCenterY)))
        {
            score += 10;
            goodericUDX = 2501;
            impact.start();

        }

        if (reghitbox >= (Math.abs(goodericCenterDUX-stringhiniCenterX) + Math.abs(goodericCenterDUY-stringhiniCenterY)))
        {
            score += 10;
            goodericDUX = -10;
            impact.start();

        }




        if (smallhitbox >= (Math.abs(betterericCenterX-stringhiniCenterX) + Math.abs(betterericCenterY-stringhiniCenterY)))
        {
            if (LIVES < 3) {
                LIVES += 1;
            }

            score += 50;
            betterericY = 2001;
            good.start();

        }



        if (reghitbox >= (Math.abs(sleepyericCenterX-stringhiniCenterX) + Math.abs(sleepyericCenterY-stringhiniCenterY)))
        {
            score -= 50;
            sleepyericY = -10;
            hurt.start();
        }



        if (reghitbox >= (Math.abs(badericCenterX-stringhiniCenterX) + Math.abs(badericCenterY-stringhiniCenterY)))
        {
            LIVES -= 1;
            badericX = -10;
            ow.start();

        }

        if (reghitbox >= (Math.abs(badericCenterLRX-stringhiniCenterX) + Math.abs(badericCenterLRY-stringhiniCenterY)))
        {
            LIVES -= 1;
            badericLRX = 1501;
            ow.start();
        }

        if (reghitbox >= (Math.abs(badericCenterUDX-stringhiniCenterX) + Math.abs(badericCenterUDY-stringhiniCenterY)))
        {
            LIVES -= 1;
            badericUDX = 2501;
            ow.start();
        }

        if (reghitbox >= (Math.abs(badericCenterDUX-stringhiniCenterX) + Math.abs(badericCenterDUY-stringhiniCenterY)))
        {
            LIVES -= 1;
            badericDUX = -10;
            ow.start();
        }
    }

    public boolean onTouchEvent(MotionEvent me){

        if(start_flg == false)
        {
            start_flg = true;


            FrameLayout frame = findViewById(R.id.frame);
            frameHeight = frame.getHeight();
            frameWidth = frame.getWidth();

            stringhiniY = (int)stringhini.getY();
            stringhiniX = (int)stringhini.getX();

            // stringhini is a square
            stringhiniSize = stringhini.getHeight();

            startLabel.setVisibility(View.GONE);

            // Start the timer
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable(){
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            }, 0, 20);
        } else {
            if (me.getAction() == MotionEvent.ACTION_DOWN){
                action_flg = true;
            }else if (me.getAction() == MotionEvent.ACTION_UP){
                action_flg = false;
            }
        }

        return true;
    }

    // Disable Return Button
    @Override
    public boolean dispatchKeyEvent(KeyEvent event)
    {
        if (event.getAction() == KeyEvent.ACTION_DOWN)
        {
            switch(event.getKeyCode())
            {
                case KeyEvent.KEYCODE_BACK:
                    return true;
            }
        }

        return super.dispatchKeyEvent(event);
    }
}
