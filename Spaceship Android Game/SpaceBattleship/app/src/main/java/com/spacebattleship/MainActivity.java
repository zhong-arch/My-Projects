package com.spacebattleship;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {
    //This is the entry point to our game
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Here we set our UI layout as the view
        setContentView(R.layout.activity_main);

        //Prepare to load fastest time
        SharedPreferences prefs;
        SharedPreferences.Editor editor;
        prefs = getSharedPreferences("HiScores",MODE_PRIVATE);
        final Button buttonPlay = (Button) findViewById(R.id.buttonPlay);
        buttonPlay.setOnClickListener(this);
        //Get a reference to the TextView in our layout
        final TextView textFastestTime = (TextView) findViewById(R.id.textHighScore);
        final TextView textRewardPoints = (TextView) findViewById(R.id.textRewardPoints);
        //Load the highest score
        //if not available our high score = 0
        int highestScore = prefs.getInt("highestScore", 0);
        int rewardPoints = prefs.getInt("rewardPoints",0);
        //put the high socre in our TextView
        textFastestTime.setText("Highest Score:"+highestScore);
        textRewardPoints.setText("Reward Points:"+rewardPoints);
    }
    /*
    click "play" button to enter a new interface, GameActivity
     */
    @Override
    public void onClick(View v){
        //must be the play button
        //Create a new Intent object
        Intent i = new Intent(this,GameActivity.class);
        //start our GameActivity class via the Intent
        startActivity(i);
        //Now shut this activity down
        finish();
    }
    /*
    input event
     */
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            finish();
            return true;
        }
        return false;
    }
}