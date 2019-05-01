
package com.example.afinal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

//Eric's Stuff

public class Play extends AppCompatActivity {

    private TextView scoreLabel;
    private TextView startLabel;
    private ImageView stringhini;
    private ImageView bettereric;
    private ImageView gooderic;
    private ImageView baderic;
    private ImageView sleepyeric;

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

    // Eric's Stuff
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        sound = new SoundPlay(this);

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

        //Eric's Stuff

        mainLayout = findViewById(R.id.frame);
        stringhini.setOnTouchListener(onTouchListener());
    }

    private OnTouchListener onTouchListener() {
        return new OnTouchListener() {
            PointF DownPT = new PointF(); // Record Mouse Position When Pressed Down
            PointF StartPT = new PointF(); // Record Start Position of 'img'


            // int stringhiniCenterX = stringhiniX + stringhini.getWidth() / 2;
            // int stringhiniCenterY = stringhiniY + stringhini.getHeight() /2;

            // PointF String = new PointF();


            int width = getScreenWidth() - 40;
            int height = getScreenHeight() - 250;

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                switch (event.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:
                        DownPT.set(event.getX(), event.getY());
                        StartPT.set(stringhini.getX(), stringhini.getY());

                        //String.set(stringhiniCenterX,stringhiniCenterY);
                        break;

                    case MotionEvent.ACTION_UP:
                        break;

                    case MotionEvent.ACTION_MOVE:
                        stringhini.setX((int) (StartPT.x + event.getX() - DownPT.x));
                        stringhini.setY((int) (StartPT.y + event.getY() - DownPT.y));

                        stringhiniY = (int) (StartPT.y + event.getY() - DownPT.y);
                        stringhiniX = (int) (StartPT.x + event.getX() - DownPT.x);

                        StartPT.set(stringhini.getX(), stringhini.getY());

                        if (stringhini.getX() < 0) {
                            stringhini.setX(0);
                        } else if (stringhini.getX() > width) {
                            stringhini.setX(width);
                        }

                        if (stringhini.getY() < 0) {
                            stringhini.setY(0);
                        } else if (stringhini.getY() > height) {
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
        if (betterericY > 2000) {
            betterericY = screenHeight - 2000;
            betterericX = (int) Math.floor(Math.random() * (frameHeight - bettereric.getHeight()));
        }
        bettereric.setX(betterericX);
        bettereric.setY(betterericY);
        System.out.println(betterericX);
        System.out.println(betterericY);



        // sleepyeric
        sleepyericY -= 25;
        if (sleepyericY < 0) {
            sleepyericY = screenHeight + 500;
            sleepyericX = (int) Math.floor(Math.random() * (frameHeight - sleepyeric.getHeight()));
        }
        sleepyeric.setX(sleepyericX);
        sleepyeric.setY(sleepyericY);


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

    public void hitCheck() {
        //stringhini center
        int stringhiniCenterX = stringhiniX + stringhini.getWidth() / 2;
        int stringhiniCenterY = stringhiniY + stringhini.getHeight() / 2;

        int goodericCenterX = goodericX + gooderic.getWidth() / 2;
        int goodericCenterY = goodericY + gooderic.getHeight() / 2;

        // bettereric
        int betterericCenterX = betterericX + bettereric.getWidth() / 2;
        int betterericCenterY = betterericY + bettereric.getHeight() / 2;

        // sleepyeric
        int sleepyericCenterX = sleepyericX + sleepyeric.getWidth() / 2;
        int sleepyericCenterY = sleepyericY + sleepyeric.getHeight() / 2;

        // baderic
        int badericCenterX = badericX + baderic.getWidth() / 2;
        int badericCenterY = badericY + baderic.getHeight() / 2;

        //HITBOX SIZE
        int reghitbox = 100;
        int smallhitbox = 50;


        System.out.println("stringhiniX");
        System.out.println(stringhiniCenterX);
        System.out.println("stringhiniY");
        System.out.println(stringhiniCenterY);

        System.out.println("goodericX");
        System.out.println(goodericCenterX);
        System.out.println("goodericY");
        System.out.println(goodericCenterY);

        System.out.println("Xdifference");
        System.out.println(goodericCenterX - stringhiniCenterX);
        System.out.println("Ydifference");
        System.out.println(goodericCenterY - stringhiniCenterY);

        // gooderic


        // 0 <= goodericCenterX <= stringhiniWidth
        // stringhiniY <= goodericCenterY <= stringhiniY + stringhiniHeight

        if (reghitbox >= (Math.abs(goodericCenterX - stringhiniCenterX) + Math.abs(goodericCenterY - stringhiniCenterY))) {
            score += 10;
            goodericX = -10;
        }


        if (smallhitbox >= (Math.abs(betterericCenterX - stringhiniCenterX) + Math.abs(betterericCenterY - stringhiniCenterY))) {
            score += 50;
            betterericY = -10;
        }


        if (reghitbox >= (Math.abs(sleepyericCenterX - stringhiniCenterX) + Math.abs(sleepyericCenterY - stringhiniCenterY))) {
            score -= 50;
            sleepyericY = -10;
        }


        if (reghitbox >= (Math.abs(badericCenterX - stringhiniCenterX) + Math.abs(badericCenterY - stringhiniCenterY))) {
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
            stringhiniX = (int) stringhini.getX();

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
