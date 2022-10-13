package com.spacebattleship;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;

public class GameActivity extends Activity {

    //Our object to handle the View
    private GameView gameView;
    //This is where the "Play" button from HomeActivity sends us
    //The game activity of the program, called when user clicked "play" button in the main interface
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get a Display object to access screen details
        Display display = getWindowManager().getDefaultDisplay();
        //load the resolution into a Point object
        Point size = new Point();
        display.getSize(size);
        //Create an instance of our Tappy Defender View(TDView)
        //Also passing in "this" which is the Context of our App
        //Also passing in the screen resolution to the constructor
        gameView = new GameView(this, size.x, size.y);
        //Make our gameView the view of the Activity
        setContentView(gameView);
    }
    //If the Activity is paused make sure to pause our thread
    @Override
    protected void onPause(){
        super.onPause();
        gameView.pause();
    }
    //If the Activity is resumed make sure to resume our thread
    @Override
    protected void onResume(){
        super.onResume();
        gameView.resume();
    }
    //input
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            finish();
            return true;
        }
        return false;
    }
}