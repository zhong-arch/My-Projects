package com.spacebattleship;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;

public class SoundManager {

    private SoundPool soundPool;
    int explode = -1;
    int hit = -1;
    int laser = -1;
    int levelUp = -1;
    int shoot = -1;
    int start = -1;
    int upgrade = -1;
    int won = -1;

    /*
    Load all sounds, called by Constructor of GameView class
    After it is called, all sound files will be load.
     */
    public void loadSound(Context context) {
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        try {
            //Create objects of the 2 required classes
            AssetManager assetManager = context.getAssets();
            AssetFileDescriptor descriptor;
            //create our fx
            //Sound file created by bfxr sound creation software
            descriptor = assetManager.openFd("explode.ogg");
            explode = soundPool.load(descriptor, 0);
            //Sound file created by bfxr sound creation software
            descriptor = assetManager.openFd("hit.ogg");
            hit = soundPool.load(descriptor, 0);
            //Sound file created by bfxr sound creation software
            descriptor = assetManager.openFd("laser.ogg");
            laser = soundPool.load(descriptor, 0);
            //Sound file created by bfxr sound creation software
            descriptor = assetManager.openFd("levelUp.ogg");
            levelUp = soundPool.load(descriptor, 0);
            //Sound file created by bfxr sound creation software
            descriptor = assetManager.openFd("shoot.ogg");
            shoot = soundPool.load(descriptor, 0);
            //Sound file created by bfxr sound creation software
            descriptor = assetManager.openFd("startGame.ogg");
            start = soundPool.load(descriptor, 0);
            //Sound file created by bfxr sound creation software
            descriptor = assetManager.openFd("upgrade.ogg");
            upgrade = soundPool.load(descriptor, 0);
            //Sound file created by bfxr sound creation software
            descriptor = assetManager.openFd("won.ogg");
            won = soundPool.load(descriptor, 0);
        } catch (IOException e) {
            //Print an error message to the console
            Log.e("error", "failed to load sound files");
        }
    }
    /*
    Play specific sound, called by update() and detectHitAndCrush() in GameManager class
    and startGame() and update() in GameView class, the specific sound would be played
     */
    public void playSound(String sound) {
        switch (sound) {
            case "explode":
                //play "explode" sound
                soundPool.play(explode, 1, 1, 0, 0, 1);
                break;
            case "hit":
                //play "hit" sound
                soundPool.play(hit, 1, 1, 0, 0, 1);
                break;
            case "laser":
                //play "laser" sound
                soundPool.play(laser, 1, 1, 0, 0, 1);
                break;
            case "levelUp":
                //play "level up" sound
                soundPool.play(levelUp, 1, 1, 0, 0, 1);
                break;
            case "shoot":
                //play "shoot" sound
                soundPool.play(shoot, 1, 1, 0, 0, 1);
                break;
            case "start":
                //play "start" sound
                soundPool.play(start, 1, 1, 0, 0, 1);
                break;
            case "upgrade":
                //play "upgrade" sound
                soundPool.play(upgrade, 1, 1, 0, 0, 1);
                break;
            case "won":
                //play "won" sound
                soundPool.play(won, 1, 1, 0, 0, 1);
                break;
        }
    }
}
