
package com.example.afinal;

import android.content.Intent;
import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    private ImageView gooderic;
    private ImageView goodericLR;
    private ImageView goodericUD;
    private ImageView goodericDU;

    private ImageView baderic;
    private ImageView baderic2;

    private ImageView bettereric;
    private ImageView bettereric2;

    private ImageView sleepyeric;
    private ImageView sleepyeric2;

    private ImageView Life1;
    private ImageView Life2;
    private ImageView Life3;

    private ImageView Death1;
    private ImageView Death2;

    // Eric's Stuff
    ViewGroup mainLayout;


    // Size
    private int frameWidth;
    private int frameHeight;
    private int stringhiniSize;
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
    private int bettereric2X;
    private int bettereric2Y;

    private int badericX;
    private int badericY;
    private int baderic2X;
    private int baderic2Y;

    private int sleepyericX;
    private int sleepyericY;
    private int sleepyeric2X;
    private int sleepyeric2Y;

    private int lowX = -500;
    private int highX = 2000;
    private int lowY = -500;
    private int highY = 3500;

    // Speed
    private int badericSpeedX;
    private int badericSpeedY;
    private int goodericSpeedX;
    private int goodericSpeedY;
    private int betterericSpeedX;
    private int betterericSpeedY;
    private int sleepyericSpeedX;
    private int sleepyericSpeedY;

    private double Speed = 1;

    private int Offset = 0;

    private int LIVES = 3;

    private double red1 = 0.5;
    private double red2 = 0.5;

    private double badericvert = Math.random();
    private double baderichor = Math.random();

    private double betterericvert = Math.random();
    private double bettererichor = Math.random();

    private double sleepyericvert = Math.random();
    private double sleepyerichor = Math.random();

    private int counter = 0;
    // Score
    private int score = 0;

    // Initalize Class
    private Handler handler = new Handler();
    private Timer timer = new Timer();
    //private SoundPlay sound;

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

        gooderic = findViewById(R.id.gooderic);
        goodericLR = findViewById(R.id.goodericlr);
        goodericUD = findViewById(R.id.goodericud);
        goodericDU = findViewById(R.id.goodericdu);

        baderic = findViewById(R.id.baderic);
        baderic2 = findViewById(R.id.baderic2);

        bettereric = findViewById(R.id.bettereric);
        bettereric2 = findViewById(R.id.bettereric2);

        sleepyeric = findViewById(R.id.sleepyeric);
        sleepyeric2 = findViewById(R.id.sleepyeric2);

        Life1 = findViewById(R.id.life1);
        Life2 = findViewById(R.id.life2);
        Life3 = findViewById(R.id.life3);

        Death1 = findViewById(R.id.death1);
        Death2 = findViewById(R.id.death2);

        // Get screen size
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;

        goodericSpeedX = Math.round(screenWidth/ 60F);
        goodericSpeedY = Math.round(screenHeight/ 60F);
        badericSpeedX = Math.round(screenWidth/ 60F);
        badericSpeedY = Math.round(screenHeight/ 60F);
        betterericSpeedX = Math.round(screenWidth/ 60F);
        betterericSpeedY = Math.round(screenHeight/ 60F);
        sleepyericSpeedX = Math.round(screenWidth/ 60F);
        sleepyericSpeedY = Math.round(screenHeight/ 60F);

        gooderic.setX(-1000);
        gooderic.setY(-1000);
        goodericLR.setX(-1000);
        goodericLR.setY(-1000);
        goodericUD.setX(-1000);
        goodericUD.setY(-1000);
        goodericDU.setX(-1000);
        goodericDU.setY(-1000);

        baderic.setX(-1000);
        baderic.setY(-1000);
        baderic2.setX(-1000);
        baderic2.setY(-1000);

        sleepyeric.setX(-1000);
        sleepyeric.setY(-1000);
        sleepyeric2.setX(-1000);
        sleepyeric2.setY(-1000);

        bettereric.setX(-1000);
        bettereric.setY(-1000);
        bettereric2.setX(-1000);
        bettereric2.setY(-1000);

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

        //center stringhini
        stringhini.setX(frameWidth/4);
        stringhini.setY(frameHeight/4);

        //Eric's Stuff

        mainLayout = findViewById(R.id.frame);
        stringhini.setOnTouchListener(onTouchListener());
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
        goodericX -= goodericSpeedX  * Speed;
        goodericY += ((-.5 + (int) Math.random()) * Offset);
        if (goodericX < lowX) {
            goodericX = screenWidth + 1000;
            goodericY = (int) Math.floor(Math.random()*(frameHeight - gooderic.getHeight()));
        }
        gooderic.setX(goodericX);
        gooderic.setY(goodericY);

        // goodericLR
        goodericLRX += goodericSpeedX  * Speed;
        goodericLRY += ((-.5 + (int) Math.random()) * Offset);
        if (goodericLRX > highX) {
            goodericLRX = -1000;
            goodericLRY = (int) Math.floor(Math.random()*(frameHeight - goodericLR.getHeight()));
        }
        goodericLR.setX(goodericLRX);
        goodericLR.setY(goodericLRY);

        // goodericUD
        goodericUDY += goodericSpeedY * Speed;
        goodericUDX += ((-.5 + (int) Math.random()) * Offset);
        if (goodericUDY > highY) {
            goodericUDY = -1000;
            goodericUDX = (int) Math.floor(Math.random()*(frameWidth - goodericUD.getWidth()));
        }
        goodericUD.setX(goodericUDX);
        goodericUD.setY(goodericUDY);

        // goodericDU
        goodericDUY -= goodericSpeedY * Speed;
        goodericDUX += ((-.5 + (int) Math.random()) * Offset);
        if (goodericDUY < lowY) {
            goodericDUY = screenHeight + 1000;
            goodericDUX = (int) Math.floor(Math.random()*(frameWidth - goodericDU.getWidth()));
        }
        goodericDU.setX(goodericDUX);
        goodericDU.setY(goodericDUY);

        // baderic
        if ((baderichor >= 0) && (baderichor < 0.5))
        {
            badericX -= badericSpeedX * Speed * red1;
            badericY += ((-.5 + (int) Math.random()) * Offset * red1);
            if (badericX < lowX) {
                badericX = screenWidth + 3500;
                badericY = (int) Math.floor(Math.random() * (frameHeight - baderic.getHeight()));
                baderichor = Math.random();
            }
            baderic.setX(badericX);
            baderic.setY(badericY);
        }

        if ((baderichor >= 0.5) && (baderichor <= 1.0))
        {
            badericX += badericSpeedX * Speed * red1;
            badericY += ((-.5 + (int) Math.random()) * Offset * red1);
            if (badericX > highX) {
                badericX = -3500;
                badericY = (int) Math.floor(Math.random()*(frameHeight - baderic.getHeight()));
                baderichor = Math.random();
            }
            baderic.setX(badericX);
            baderic.setY(badericY);
        }


        // badericUD
        if ((badericvert >= 0.5) && (badericvert <= 1.0))
        {
            baderic2Y += badericSpeedY * Speed * red2;
            baderic2X += ((-.5 + (int) Math.random()) * Offset * red2);
            if (baderic2Y > highY) {
                baderic2Y = -3000;
                baderic2X = (int) Math.floor(Math.random() * (frameWidth - baderic2.getWidth()));
                badericvert = Math.random();
            }
            baderic2.setX(baderic2X);
            baderic2.setY(baderic2Y);
        }

        if ((badericvert >= 0) && (badericvert < 0.5))
        {
            baderic2Y -= badericSpeedY * Speed * red2;
            baderic2X += ((-.5 + (int) Math.random()) * Offset * red2);
            if (baderic2Y < lowY) {
                baderic2Y = screenHeight + 3000;
                baderic2X = (int) Math.floor(Math.random() * (frameWidth - baderic2.getWidth()));
                badericvert = Math.random();
            }
            baderic2.setX(baderic2X);
            baderic2.setY(baderic2Y);
        }


        // bettereric
        if ((bettererichor >= 0) && (bettererichor < 0.5))
        {
            betterericX -= betterericSpeedX * Speed;
            betterericY += ((-.5 + (int) Math.random()) * Offset);
            if (betterericX < lowX) {
                betterericX = screenWidth + 7500;
                betterericY = (int) Math.floor(Math.random() * (frameHeight - bettereric.getHeight()));
                bettererichor = Math.random();
            }
            bettereric.setX(betterericX);
            bettereric.setY(betterericY);
        }

        if ((bettererichor >= 0.5) && (bettererichor <= 1.0))
        {
            betterericX += betterericSpeedX * Speed;
            betterericY += ((-.5 + (int) Math.random()) * Offset);
            if (betterericX > highX) {
                betterericX = -7500 ;
                betterericY = (int) Math.floor(Math.random()*(frameHeight - bettereric.getHeight()));
                bettererichor = Math.random();
            }
            bettereric.setX(betterericX);
            bettereric.setY(betterericY);
        }

        // betterericUD
        if ((betterericvert >= 0.5) && (betterericvert <= 1.0))
        {
            bettereric2Y += betterericSpeedY * Speed;
            bettereric2X += ((-.5 + (int) Math.random()) * Offset);
            if (bettereric2Y > highY) {
                bettereric2Y = -7500;
                bettereric2X = (int) Math.floor(Math.random() * (frameWidth - bettereric2.getWidth()));
                betterericvert = Math.random();
            }
            bettereric2.setX(bettereric2X);
            bettereric2.setY(bettereric2Y);
        }

        if ((betterericvert < 0.5) && (betterericvert >= 0))
        {
            bettereric2Y -= betterericSpeedY * Speed;
            bettereric2X += ((-.5 + (int) Math.random()) * Offset);
            if (bettereric2Y < lowY) {
                bettereric2Y = screenHeight + 7500;
                bettereric2X = (int) Math.floor(Math.random() * (frameWidth - bettereric2.getWidth()));
                betterericvert = Math.random();
            }
            bettereric2.setX(bettereric2X);
            bettereric2.setY(bettereric2Y);
        }


        // sleepyeric
        if ((sleepyerichor >= 0) && (sleepyerichor < 0.5))
        {
            sleepyericX -= sleepyericSpeedX * Speed;
            sleepyericY += ((-.5 + (int) Math.random()) * Offset);
            if (sleepyericX < lowX) {
                sleepyericX = screenWidth + 2000;
                sleepyericY = (int) Math.floor(Math.random() * (frameHeight - sleepyeric.getHeight()));
                sleepyerichor = Math.random();
            }
            sleepyeric.setX(sleepyericX);
            sleepyeric.setY(sleepyericY);
        }

        if ((sleepyerichor >= 0.5) && (sleepyerichor <= 1.0))
        {
            sleepyericX += sleepyericSpeedX * Speed;
            sleepyericY += ((-.5 + (int) Math.random()) * Offset);
            if (sleepyericX > highX) {
                sleepyericX = -2000;
                sleepyericY = (int) Math.floor(Math.random()*(frameHeight - sleepyeric.getHeight()));
                sleepyerichor = Math.random();
            }
            sleepyeric.setX(sleepyericX);
            sleepyeric.setY(sleepyericY);
        }


        // sleepyericUD
        if ((sleepyericvert >= 0.5) && (sleepyericvert <= 1.0))
        {
            sleepyeric2Y += sleepyericSpeedY * Speed;
            sleepyeric2X += ((-.5 + (int) Math.random()) * Offset);
            if (sleepyeric2Y > highY) {
                sleepyeric2Y = -7500;
                sleepyeric2X = (int) Math.floor(Math.random() * (frameWidth - sleepyeric2.getWidth()));
                sleepyericvert = Math.random();
            }
            sleepyeric2.setX(sleepyeric2X);
            sleepyeric2.setY(sleepyeric2Y);
        }

        if ((sleepyericvert < 0.5) && (sleepyericvert >= 0))
        {
            sleepyeric2Y -= sleepyericSpeedY * Speed;
            sleepyeric2X += ((-.5 + (int) Math.random()) * Offset);
            if (sleepyeric2Y < lowY) {
                sleepyeric2Y = screenHeight + 7500;
                sleepyeric2X = (int) Math.floor(Math.random() * (frameWidth - sleepyeric2.getWidth()));
                sleepyericvert = Math.random();
            }
            sleepyeric2.setX(sleepyeric2X);
            sleepyeric2.setY(sleepyeric2Y);
        }


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
                break;

            case 0:
                // Stop Timer!!
                timer.cancel();
                timer = null;

                // Show result
                Intent intent = new Intent(getApplicationContext(), result.class);
                intent.putExtra("SCORE", score);
                startActivity(intent);
                break;
        };

        //LEVEL 1
        if (score <= 50)
        {
            Speed = 1;
            Offset = 0;
            red1 = 0.5;
            red2 = 0.5;
        }

        //LEVEL 2
        if ((50 < score)&&(score <= 100))
        {
            Speed = 1;
            Offset = 0;
            red1 = .75;
            red2 = .75;
        }

        //LEVEL 3
        if ((100 < score)&&(score <= 150))
        {
            Speed = 1;
            Offset = 0;
            red1 = 1;
            red2 = 1;
        }

        //LEVEL 4
        if ((150 < score)&&(score <= 200))
        {
            Speed = 1;
            Offset = 0;
            red1 = 1;
            red2 = 1;
        }

        //LEVEL 5
        if ((200 < score)&&(score <= 300))
        {
            Speed = 1.25;
            Offset = 5;
            red1 = 1;
            red2 = 1;
        }

        //LEVEL 6
        if ((300 < score)&&(score <= 400))
        {
            Speed = 1.5;
            Offset = 10;
            red2 = 1;
        }

        //LEVEL 7
        if ((400 < score)&&(score <= 500))
        {
            Speed = 1.75;
            Offset = 15;
            red1 = 1;
            red2 = 1;
        }

        //LEVEL 7
        if ((500 < score)&&(score <= 600))
        {
            Speed = 2;
            Offset = 20;
            red1 = 1;
            red2 = 1;
        }

        scoreLabel.setText("Score: " + score);
    }

    public void hitCheck()

    {
        /*
        System.out.println("reg X is " + goodericX);
        System.out.println("reg Y is " + goodericY);
        System.out.println("LR X is " + goodericLRX);
        System.out.println("LR Y is " + goodericLRY);
        System.out.println("UD X is " + goodericUDX);
        System.out.println("UD Y is " + goodericUDY);
        System.out.println("DU X is " + goodericDUX);
        System.out.println("DU Y is " + goodericDUY);
        */

        System.out.println("bad Y is " + sleepyeric2Y);
        System.out.println("bad X is " + sleepyeric2X);
        System.out.println("hor is " + sleepyericvert);

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



        // baderic
        int badericCenterX = badericX + baderic.getWidth() /2;
        int badericCenterY = badericY + baderic.getHeight() /2;

        // baderic2
        int baderic2CenterX = baderic2X + baderic2.getWidth() /2;
        int baderic2CenterY= baderic2Y + baderic2.getHeight() /2;



        // bettereric
        int betterericCenterX = betterericX + bettereric.getWidth() /2;
        int betterericCenterY = betterericY + bettereric.getHeight() /2;

        // bettereric2
        int bettereric2CenterX = bettereric2X + bettereric2.getWidth() /2;
        int bettereric2CenterY = bettereric2Y + bettereric2.getHeight() /2;



        // sleepyeric
        int sleepyericCenterX = sleepyericX + sleepyeric.getWidth() /2;
        int sleepyericCenterY = sleepyericY + sleepyeric.getHeight() /2;

        // sleepyeric2
        int sleepyeric2CenterX = sleepyeric2X + sleepyeric2.getWidth() /2;
        int sleepyeric2CenterY = sleepyeric2Y + sleepyeric2.getHeight() /2;

        //HITBOX SIZE
        int reghitbox = 175;
        int smallhitbox = 125;


        // 0 <= goodericCenterX <= stringhiniWidth
        // stringhiniY <= goodericCenterY <= stringhiniY + stringhiniHeight

        if (reghitbox >= (Math.abs(goodericCenterX-stringhiniCenterX) + Math.abs(goodericCenterY-stringhiniCenterY)))
        {
            score += 50;
            goodericX = screenWidth + 1000;
            goodericY = (int) Math.floor(Math.random()*(frameHeight - gooderic.getHeight()));
            gooderic.setX(goodericX);
            gooderic.setY(goodericY);
        }

        if (reghitbox >= (Math.abs(goodericCenterLRX-stringhiniCenterX) + Math.abs(goodericCenterLRY-stringhiniCenterY)))
        {
            score += 50;
            goodericLRX = -1000;
            goodericLRY = (int) Math.floor(Math.random()*(frameHeight - goodericLR.getHeight()));
            goodericLR.setX(goodericLRX);
            goodericLR.setY(goodericLRY);
        }

        if (reghitbox >= (Math.abs(goodericCenterUDX-stringhiniCenterX) + Math.abs(goodericCenterUDY-stringhiniCenterY)))
        {
            score += 50;
            goodericUDY = -1000;
            goodericUDX = (int) Math.floor(Math.random()*(frameWidth - goodericUD.getWidth()));
            goodericUD.setX(goodericUDX);
            goodericUD.setY(goodericUDY);
        }

        if (reghitbox >= (Math.abs(goodericCenterDUX-stringhiniCenterX) + Math.abs(goodericCenterDUY-stringhiniCenterY)))
        {
            score += 50;
            goodericDUY = screenHeight + 1000;
            goodericDUX = (int) Math.floor(Math.random()*(frameWidth - goodericDU.getWidth()));
            goodericDU.setX(goodericDUX);
            goodericDU.setY(goodericDUY);
        }




        if (smallhitbox >= (Math.abs(betterericCenterX-stringhiniCenterX) + Math.abs(betterericCenterY-stringhiniCenterY)))
        {
            if (LIVES < 3)
            {
                LIVES +=1;
            }
            score += 50;
            if ((bettererichor >= 0) && (bettererichor < 0.5))
            {
                    betterericX = screenWidth + 7500;
                    betterericY = (int) Math.floor(Math.random() * (frameHeight - bettereric.getHeight()));
                    bettererichor = Math.random();
                bettereric.setX(betterericX);
                bettereric.setY(betterericY);
            }

            if ((bettererichor >= 0.5) && (bettererichor <= 1.0))
            {
                    betterericX = -7500 ;
                    betterericY = (int) Math.floor(Math.random()*(frameHeight - bettereric.getHeight()));
                    bettererichor = Math.random();
                bettereric.setX(betterericX);
                bettereric.setY(betterericY);
            }

        }

        if (smallhitbox >= (Math.abs(bettereric2CenterX-stringhiniCenterX) + Math.abs(bettereric2CenterY-stringhiniCenterY)))
        {
            if (LIVES < 3)
            {
                LIVES +=1;
            }
            score += 50;

            if ((betterericvert >= 0.5) && (betterericvert <= 1.0))
            {

                    bettereric2Y = -7500;
                    bettereric2X = (int) Math.floor(Math.random() * (frameWidth - bettereric2.getWidth()));
                    betterericvert = Math.random();
                bettereric2.setX(bettereric2X);
                bettereric2.setY(bettereric2Y);
            }

            if ((betterericvert < 0.5) && (betterericvert >= 0))
            {
                    bettereric2Y = screenHeight + 7500;
                    bettereric2X = (int) Math.floor(Math.random() * (frameWidth - bettereric2.getWidth()));
                    betterericvert = Math.random();
                bettereric2.setX(bettereric2X);
                bettereric2.setY(bettereric2Y);
            }
        }



        if (reghitbox >= (Math.abs(sleepyericCenterX-stringhiniCenterX) + Math.abs(sleepyericCenterY-stringhiniCenterY)))
        {
            if (score >= 20) {
                score -= 20;
            }

            if ((10 <= score )&&(score < 20)) {
                score -= 10;
            }

            if ((sleepyerichor >= 0) && (sleepyerichor < 0.5))
            {
                    sleepyericX = screenWidth + 1000 + 1000 * (int) Math.random();
                    sleepyericY = (int) Math.floor(Math.random() * (frameHeight - sleepyeric.getHeight()));
                    sleepyerichor = Math.random();
                sleepyeric.setX(sleepyericX);
                sleepyeric.setY(sleepyericY);
            }

            if ((sleepyerichor >= 0.5) && (sleepyerichor <= 1.0))
            {
                    sleepyericX = -1000 - 1000 * (int) Math.random();
                    sleepyericY = (int) Math.floor(Math.random()*(frameHeight - sleepyeric.getHeight()));
                    sleepyerichor = Math.random();
                sleepyeric.setX(sleepyericX);
                sleepyeric.setY(sleepyericY);
            }
        }

        if (reghitbox >= (Math.abs(sleepyeric2CenterX-stringhiniCenterX) + Math.abs(sleepyeric2CenterY-stringhiniCenterY)))
        {
            if (score >= 20) {
                score -= 20;
            }

            if ((10 <= score )&&(score < 20)) {
                score -= 10;
            }

            if ((sleepyericvert >= 0.5) && (sleepyericvert <= 1.0))
            {
                    sleepyeric2Y = -3000;
                    sleepyeric2X = (int) Math.floor(Math.random() * (frameWidth - sleepyeric2.getWidth()));
                    sleepyericvert = Math.random();
                sleepyeric2.setX(sleepyeric2X);
                sleepyeric2.setY(sleepyeric2Y);
            }

            if ((sleepyericvert >= 0) && (sleepyericvert < 0.5));
            {
                    sleepyeric2Y = screenHeight + 3000;
                    sleepyeric2X = (int) Math.floor(Math.random() * (frameWidth - sleepyeric2.getWidth()));
                    sleepyericvert = Math.random();
                sleepyeric2.setX(sleepyeric2X);
                sleepyeric2.setY(sleepyeric2Y);
            }
        }



        if (reghitbox >= (Math.abs(badericCenterX-stringhiniCenterX) + Math.abs(badericCenterY-stringhiniCenterY)))
        {
            LIVES -= 1;
            if ((baderichor >= 0) && (baderichor < 0.5))
            {
                    badericX = screenWidth + 3500;
                    badericY = (int) Math.floor(Math.random() * (frameHeight - baderic.getHeight()));
                    baderichor = Math.random();
                baderic.setX(badericX);
                baderic.setY(badericY);
            }

            if ((baderichor >= 0.5) && (baderichor <= 1.0))
            {
                    badericX = -3500;
                    badericY = (int) Math.floor(Math.random()*(frameHeight - baderic.getHeight()));
                    baderichor = Math.random();
                baderic.setX(badericX);
                baderic.setY(badericY);
            }

        }

        if (reghitbox >= (Math.abs(baderic2CenterX-stringhiniCenterX) + Math.abs(baderic2CenterY-stringhiniCenterY)))
        {
            LIVES -= 1;
            if ((badericvert >= 0.5) && (badericvert <= 1.0))
            {
                    baderic2Y = -3000;
                    baderic2X = (int) Math.floor(Math.random() * (frameWidth - baderic2.getWidth()));
                    badericvert = Math.random();
                baderic2.setX(baderic2X);
                baderic2.setY(baderic2Y);
            }

            if ((badericvert >= 0) && (badericvert < 0.5))
            {
                    baderic2Y = screenHeight + 3000;
                    baderic2X = (int) Math.floor(Math.random() * (frameWidth - baderic2.getWidth()));
                    badericvert = Math.random();
                baderic2.setX(baderic2X);
                baderic2.setY(baderic2Y);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent me){

        if(start_flg == false)
        {
            start_flg = true;
            //sound.playGameMusic();


            FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
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
