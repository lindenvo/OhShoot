package com.example.afinal;

import android.content.Context;
import android.media.MediaPlayer;

//import android.media.SoundPool;

public class SoundPlay {
    /*
    private static SoundPool soundPool;
    //private static int menuMusic;
    private static int gameMusic;
    private static int loseMusic;
    */
    // private static int overSound;
    MediaPlayer menuMusic;
    public SoundPlay(Context context) {
/* soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);

        menuMusic = soundPool.load(context, R.raw.menu, 2);
        gameMusic = soundPool.load(context, R.raw.game, 1);
        loseMusic = soundPool.load(context, R.raw.lose, 0);
    */
        menuMusic = MediaPlayer.create(getApplicationContext(), R.raw.menu);

    }

    private Context getApplicationContext() {
        return null;
    }
/*
    public void playMenuMusic() {
        soundPool.play(menuMusic, 1.0f, 1.0f, 2, 0, 1.0f);
        soundPool.pause(gameMusic);
        soundPool.pause(loseMusic);
    }


    public void playGameMusic() {
        soundPool.play(gameMusic, 1.0f, 1.0f, 2, 0, 1.0f);
        soundPool.pause(loseMusic);
        soundPool.pause(menuMusic);
    }

    public void playLoseMusic() {
        soundPool.play(loseMusic, 1.0f, 1.0f, 2, 0, 1.0f);
        soundPool.pause(gameMusic);
        soundPool.pause(menuMusic);
    }
    */
}
