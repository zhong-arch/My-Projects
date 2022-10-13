package com.spacebattleship;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.util.ArrayList;

public class GameView extends SurfaceView implements Runnable{
    private Context context;
    volatile boolean running;//Whether the user is using the program
    Thread gameThread = null;
    private PlayerShip player;
    private GameManager gameManager;
    private Paint paint;
    private Canvas canvas;
    private int screenX;
    private int screenY;
    private SurfaceHolder ourHolder;
    public ArrayList<Stars> starsList;
    //private long timeTaken;
    //private long timeStarted;
    private int highestScore;//The highest score records
    private int rewardPoints;//rewarded upgrade points from previous playing
    private final int ENDGAMERESPAWN = 50;//maximum time of enemy respawning
    //player will get 1 upgrade point for every 100 scores
    private final int REWARDSCORE = 200;//reward scores if player won the game
    private final int STARNUMBER = 40;//number of stars
    private boolean gameEnded;//Whether the game is ended
    private boolean won;//Whether the player won the game
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private InputController inputControl;
    private SoundManager soundManager;

    /*
    Constructor, called by MainActivity.java
    load necessary resources such as records of the highest scores,
    rewarded upgrade points and soundManager. Initialize game plays.
     */
    public GameView(Context context, int x, int y) {

        super(context);
        this.context = context;

        //Get a reference to a file called HiScores.
        //If id doesn't exist one is created
        prefs = context.getSharedPreferences("HiScores",context.MODE_PRIVATE);
        //Initialize the editor ready
        editor = prefs.edit();
        //labeled "fastestTime"
        highestScore = prefs.getInt("highestScore",0);//load the highest score in record
        rewardPoints = prefs.getInt("rewardPoints",0);//load the reward upgrade points in record
        soundManager = new SoundManager();//make a new soundManager
        soundManager.loadSound(context);//Initialize and load sound files
        screenX = x;//load width of screen
        screenY = y;//load height of screen
        ourHolder = getHolder();
        paint = new Paint();
        inputControl = new InputController(screenX,screenY);
        startGame();//start a new game
    }
    /*
    Initialize game play, called by constructor and update()
    initialize multiple objects, load reward upgrade points
    and add them into new game play. After it is called, a new
    game will be started.
     */
    private void startGame(){

        player = new PlayerShip(context, screenX, screenY);//initialize Playership
        rewardPoints = prefs.getInt("rewardPoints",0);//load reward upgrade points
        player.setUpgradePoints(player.getUpgradePoint()+rewardPoints);//add upgrade points
        gameManager = new GameManager(context,screenX,screenY,player, soundManager);//initialize gameManager
        soundManager.playSound("start");//play sound "start"
        starsList = new ArrayList<Stars>();

        int numSpecs = STARNUMBER;
        for(int i = 0;i<numSpecs;i++){
            //generate stars on the screen
            Stars spec = new Stars(screenX,screenY);
            starsList.add(spec);
        }
        //Reset time
        //timeTaken = 0;
        //Get start time
        //timeStarted = System.currentTimeMillis();
        gameEnded = false;
        won = false;
    }
    /*
    Update and status of the game, called by run()
    Detect whether the game is ended. After it is called,
    all objects in the game will be updated.
     */
    private void update(){

        if(!gameEnded) {//if the game is not ended
            //Detect if the player's health point is lower than 0
            if(player.getHealth() < 0){
                gameEnded = true;//The game is ended
                updateScores();
            }
            //Check the respawn time of enemies, if it surpass the value,
            //the game is ended
            if(gameManager.getRespawn() >= ENDGAMERESPAWN){
                soundManager.playSound("won");//play the sound "won"
                //The player will be reward 200 scores for winning the game
                player.setScore(player.getScore()+REWARDSCORE);
                gameEnded = true;//Now end the game
                won = true;
                updateScores();
            }
            if (gameManager.isPlaying()) {

                //update stars to fly in space
                for (Stars sd : starsList) {
                    sd.update();
                }
                //update the player
                player.update();
                //update the all status in gameManager object
                gameManager.update();
            }
        }
    }
    /*
    Draw all components on the screen, called by run()
    After it is called, all elements include player's ship, enemies' ship
    buttons, stars, HUD will be drawn on the screen.
     */
    private void draw(){
        if(ourHolder.getSurface().isValid()){
            //First we lock the area of memory we will be drawing to
            canvas = ourHolder.lockCanvas();
            //Rub out the last frame
            canvas.drawColor(Color.argb(255,0,0,0));

            //white stars in the space
            paint.setColor(Color.argb(255,255,255,255));
            //Draw the dust from our arrayList
            for(Stars sd: starsList){
                canvas.drawCircle(sd.getX(),sd.getY(),sd.getRadius(),paint);
            }

            //Draw enemies
            drawEnemies();
            //Draw the player
            drawPlayer();

            //Draw buttons
            drawButtonsAndHUD();

            ourHolder.unlockCanvasAndPost(canvas);
        }
    }
//    private String formatTime(long time){
//        long seconds = (time)/1000;
//        long thousandths = (time)-(seconds*1000);
//        String strThousanths = ""+thousandths;
//        if(thousandths<100){
//            strThousanths = "0"+thousandths;
//        }
//        if(thousandths<10){
//            strThousanths = "0"+strThousanths;
//        }
//        String stringTime = ""+seconds + "." + strThousanths;
//        return stringTime;
//    }
    /*
    Lock the FPS(frame per second) to 60
     */
    private void control(){
        try {
            gameThread.sleep(17);//60fps
        }catch (InterruptedException e) {
        }
    }
    /*
    The default running function of the game, automatically be called
    update all status, maintain fixed FPS, draw all elements
     */
    @Override
    public void run(){
        while(running){
            update();
            control();
            draw();
        }
    }
    /*
    Clean up our thread if the game is interrupted or the player quites
    automatically be called when user switch to other program such as
    answering a phone call
     */
    public void pause(){
        running = false;
        try{
            gameThread.join();
        }catch (InterruptedException e){
            Log.e("error","failed to pause thread");
        }
    }
    /*
    //Make a new thread and start it, automatically be called when
    user switch back to the program.
     */
    public void resume(){
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
    /*
    SurfaceView allows us to handle the onTouchEvent
    When the game is started, tap different buttons on the screen
    to perform different functions.
    When the game is ended, tap the screen could start
    a new game.
     */
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        //There are many different evens in MotionEvent

        if (!gameEnded) {//When the game not ended
            //press different buttons on the screen
            //to perform different functions.
            inputControl.handleInput(motionEvent, player, gameManager, soundManager);

        } else {//When the game is ended
            //tap the screen could start a new game.
            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    startGame();
                    break;
            }
        }
        return true;
    }
    /*
    Draw the player, player's canons and player's laser, called by draw()
    After it is called, the player's ship, player's canons and player's laser
    will be displayed on the screen.
     */
    public void drawPlayer(){

        //Draw the player
        paint.setColor(Color.argb(255, 255, 255, 255));
        //check if the player in the blocking(taking damage and has shield points) status
        if(player.getBlockTime()>0){
            //Draw the blue version of player's image
            canvas.drawBitmap(player.getBitmapShield(), player.getX(), player.getY(), paint);
        }else {
            //Draw regular version of the player's image
            canvas.drawBitmap(player.getBitmap(), player.getX(), player.getY(), paint);
        }

        //Draw laser of player
        paint.setColor(Color.argb(255, 255, player.getWeaponGreen(), 0));
        //Check if the player is firing a laser
        if (player.getLaser().isFiring()) {
            Rect laserRect = new Rect();//create a new rectangular
            //set coordinate of position of the rectangular
            laserRect.set((int) (player.getX()) + player.getBitmap().getWidth(),//left
                    (int) (player.getY() + player.getBitmap().getHeight() / 2 - player.getLaser().getLaserHeight() / 2),//up
                    screenX,//right
                    (int) (player.getY() + player.getBitmap().getHeight() / 2 + player.getLaser().getLaserHeight() / 2));//down
            //draw the rectangular
            canvas.drawRect(laserRect, paint);
        }

        //Draw canons of player
        //load the arrayList of the canons for the player
        ArrayList<Canon> canonsToDraw = player.getCanons();
        if(canonsToDraw.size()>0) {//if the list has at least one element
            Rect canonRect = new Rect();//create a new rectangular
            for (Canon canon : canonsToDraw) {//iterate the list
                    //set coordinate of position of the rectangular
                    canonRect.set((int) (canon.getX()), //up
                            (int) (canon.getY()),//left
                            (int) (canon.getX()) + canon.getCanonWidth(),//right
                            (int) (canon.getY()) + canon.getCanonHeight());//down
                    //draw the rectangular
                    canvas.drawRect(canonRect, paint);
            }
        }
        //load the arrayList of the flames for the player
        ArrayList<Flame> flames = player.getFlameList();
        if( flames.size() > 0 ){//if the list has at least one element
            //get the color of the flame and set it
            paint.setColor(Color.argb(255,255,player.getFlameGreen(),0));
            //Draw the dust from our arrayList
            for(Flame flame: flames){//iterate the list
                //draw each flame on the player's ship
                canvas.drawCircle(flame.getX(),flame.getY(),flame.getRadius(),paint);
            }
        }
        //draw health and shield bars on the screen for the player
        drawBars(player);
    }
    /*
    Draw the enemies' ships, enemies' canons and enemies' laser, called by draw()
    After it is called, the enemies' ships, enemies' canons and enemies' laser
    will be displayed on the screen.
     */
    public void drawEnemies(){

        //Draw enemies
        ArrayList<Canon> canonsToDraw;
        Rect canonRect = new Rect();
        Rect laserRect = new Rect();
        //For each of enemyship
        for(Enemyship enemy:gameManager.getEnemyships()){
            paint.setColor(Color.argb(255, 255, 255, 255));
            //check if the player in the blocking(taking damage and has shield points) status
            if(enemy.getBlockTime()>0){
                //Draw the blue version of enemy's image
                canvas.drawBitmap(enemy.getBitmapShield(), enemy.getX(), enemy.getY(), paint);
            }else {
                //Draw the regular version of enemy's image
                canvas.drawBitmap(enemy.getBitmap(), enemy.getX(), enemy.getY(), paint);
            }
            //Draw canons of enemies
            //if the enemy fired at least one canon
            if (enemy.getEnemyType() == 1 && enemy.getCanons().size() > 0) {
                canonsToDraw = enemy.getCanons();//load the list of canons
                for (Canon canon : canonsToDraw) {//for each of canon
                    canonRect.set((int) (canon.getX()),//left
                            (int) (canon.getY()),//up
                            (int) (canon.getX()) + canon.getCanonWidth(),//right
                            (int) (canon.getY()) + canon.getCanonHeight());//down
                    //draw the canon with prosper color
                    paint.setColor(Color.argb(255, 255, enemy.getWeaponGreen(), 0));
                    canvas.drawRect(canonRect, paint);//draw the canon
                }
            }
            //Draw laser of enemies
            if (enemy.getEnemyType() == 2 && enemy.getLaser().isFiring()) {//check if the enemy is firing laser
                laserRect.set(0,
                        (int) (enemy.getY() + enemy.getBitmap().getHeight() / 2 + enemy.getLaser().getLaserHeight()),//up
                        (int) (enemy.getX()),//right
                        (int) (enemy.getY() + enemy.getBitmap().getHeight() / 2 + enemy.getLaser().getLaserHeight() * 2));//down
                //draw the laser with prosper color
                paint.setColor(Color.argb(255, 255, enemy.getWeaponGreen(), 0));
                canvas.drawRect(laserRect, paint);//draw the laser
            }
            drawBars(enemy);//draw health bar and shield bars of the enemy
            //Draw flames on enemies
            //load the flames on the enemy
            ArrayList<Flame> flames = enemy.getFlameList();
            if( flames.size() > 0 ){
                //same color with enemies' weapons
                paint.setColor(Color.argb(255,255,enemy.getFlameGreen(),0));
                for(Flame flame: flames){//Draw the flame from our arrayList
                    canvas.drawCircle(flame.getX(),flame.getY(),flame.getRadius(),paint);
                }
            }
        }
    }
    /*
    Draw health bar and shield bars, called by drawPlayer()
    and drawEnemies(), after it is called, under the image of each
    spaceship, there are a green color bar and a blue color bar under
    the each spaceship representing the their remaining health and
    shield points.
     */
    public void drawBars(Ship ship){
        int currentHealth;
        int currentShield;
        float health = ship.getHealth();//get current health value
        float healthMax = ship.getHealthMax();//get maximum health value
        float shield = ship.getShield();//get current shield value
        float shieldMax = ship.getShieldMax();//get maximum shield value
        double healthPercent = health/healthMax;//calculate percentage
        double shieldPercent = shield/shieldMax;//calculate percentage
        int shipWidth = ship.getBitmap().getWidth();//get ship's width
        int shipHeight = ship.getBitmap().getHeight();//get ship's height
        if(health > 0) {
            currentHealth = (int) (healthPercent * shipWidth);//length of the green bar
        }else{
            currentHealth = 0;//preventing negative value
        }
        if(shield > 0) {
            currentShield = (int) (shieldPercent * shipWidth);//length of the blue bar
        }else{
            currentShield = 0;//preventing negative value
        }
        Rect rect = new Rect();
        //Draw current health bar
        rect.set((int)(ship.getX()),(int)(ship.getY()+shipHeight+screenY/100),
                (int)(ship.getX()+currentHealth),(int)(ship.getY()+shipHeight+screenY/100*2));
        paint.setColor(Color.argb(255, 0, 255, 0));
        canvas.drawRect(rect, paint);
        //Draw the bar of the gap between current health and max health
        rect.set((int)(ship.getX())+currentHealth,(int)(ship.getY()+shipHeight+screenY/100),
                (int)(ship.getX()+shipWidth),(int)(ship.getY()+shipHeight+screenY/100*2));
        paint.setColor(Color.argb(255, 255, 0, 0));
        canvas.drawRect(rect, paint);
        //Draw current shield bar
        rect.set((int)(ship.getX()),(int)(ship.getY()+shipHeight+screenY/100*2),
                (int)(ship.getX()+currentShield),(int)(ship.getY()+shipHeight+screenY/100*3));
        paint.setColor(Color.argb(255, 0, 0, 255));
        canvas.drawRect(rect, paint);
        //Draw the bar of the gap between current shield and max shield
        rect.set((int)(ship.getX()+currentShield),(int)(ship.getY()+shipHeight+screenY/100*2),
                (int)(ship.getX()+shipWidth),(int)(ship.getY()+shipHeight+screenY/100*3));
        paint.setColor(Color.argb(255, 255, 255, 255));
        canvas.drawRect(rect, paint);
    }
    /*
    draw all buttons of the game, called by draw()
    after it is called, all buttons will be drawn on the screen
     */
    public void drawButtonsAndHUD(){

        if(!gameEnded) {//if the game is not ended

            //draw up button
            drawMovementButton(inputControl.getUpButton(),true);
            //draw down button
            drawMovementButton(inputControl.getDownButton(),false);
            //draw pause buttons
            drawSingleButton(inputControl.getPauseButton(),true);
            //draw shoot button
            //if the canon is not ready, the button will be drawn in a lower
            //transparency level
            drawSingleButton(inputControl.getCanonButton(),player.isCanonReady());

            //draw laser button
            //if the canon is not ready, the button will be drawn in a lower
            //transparency level
            drawSingleButton(inputControl.getLaserButton(),player.isLaserReady());

            //draw upgrade buttons
            ArrayList<Rect> buttonsToDraw = inputControl.getUpgradeButtons();
            for (Rect rect : buttonsToDraw) {
                drawSingleButton(rect,player.getUpgradePoint()>0);
            }

            //Draw the hud information
            paint.setTextAlign(Paint.Align.LEFT);//set align to the left
            paint.setColor(Color.argb(255, 255, 255, 255));//white color
            paint.setTextSize(30);//font size
            //showing current player's score
            canvas.drawText("Score:" + player.getScore(), 10, 30, paint);
            //label of canon
            canvas.drawText("Canon",inputControl.getCanonButton().left+screenX/120,
                    inputControl.getCanonButton().bottom+screenY/20,paint);
            //label of laser
            canvas.drawText("Laser",inputControl.getLaserButton().left+screenX/120,
                    inputControl.getLaserButton().bottom+screenY/20,paint);
            //canvas.drawText("Time:" + formatTime(timeTaken) + "s", screenX / 2, 30, paint);
            //showing current health point value of the player
            canvas.drawText("Health:" + (int)(player.getHealth()), 10, screenY - 20, paint);
            //showing current shield point value of the player
            canvas.drawText("Shield:" + (int)(player.getShield()), screenX/10, screenY - 20, paint);
            paint.setTextAlign(Paint.Align.CENTER);//set align to center
            //label of body
            canvas.drawText("Body", screenX/2-screenX/25*3, screenY-50, paint);
            //label of shield
            canvas.drawText("Shield", screenX/2-screenX/25, screenY-50, paint);
            //label of canon
            canvas.drawText("Canon", screenX/2+screenX/25, screenY-50, paint);
            //label of laser
            canvas.drawText("Laser", screenX/2+screenX/25*3, screenY-50, paint);
            //showing the current level of the ship's body
            canvas.drawText("Level: " + player.getBodyLevel(), screenX/2-screenX/25*3, screenY-20, paint);
            //showing the current level of the shield
            canvas.drawText("Level: " + player.getShieldLevel(), screenX/2-screenX/25, screenY-20, paint);
            //showing the current level of the canon
            canvas.drawText("Level: " + player.getCanonLevel(), screenX/2+screenX/25, screenY-20, paint);
            //showing the current level of the laser
            canvas.drawText("Level: " + player.getLaserLevel(), screenX/2+screenX/25*3, screenY-20, paint);
        }else{//Game is ended

            paint.setColor(Color.argb(255, 255, 255, 255));//white color

            paint.setTextAlign(Paint.Align.CENTER);//set to align to center
            //showing the "Game Over" in the center
            //showing different messages when the game is ended
            if(won){
                paint.setTextSize(60);//font size
                //it shows that player has won the game and 2 upgrade points are rewarded
                canvas.drawText("You won, 2 upgrade points are rewarded", screenX / 2, screenY / 3, paint);
            }else {
                paint.setTextSize(80);//font size
                //Just showing the game is over
                canvas.drawText("Game Over", screenX / 2, screenY / 3, paint);
            }
            paint.setTextSize(30);//font size
            //showing the result of the score
            canvas.drawText("Your score: "+player.getScore()+" The highest score: "+highestScore,screenX/2,screenY/2-50,paint);
            //canvas.drawText("Time:"+formatTime(timeTaken)+"s",screenX/2,screenY/2,paint);
            //showing the result of level of the player
            canvas.drawText("Your level: "+player.getLevel(),screenX/2,screenY/2+50,paint);
            paint.setTextSize(80);//font size
            //remind the player tapping the screen could start a new game
            canvas.drawText("Tap to replay!",screenX/2,screenY/3*2,paint);
        }
    }
    /*
    Drawing movement buttons(up and down), called by drawButtonsAndHUD()
    after it is called, up and down button would be drawn on the screen
     */
    public void drawMovementButton(Rect rect, boolean up){

        int oneX,oneY,twoX,twoY,threeX,threeY;
        //x axis of the first point
        oneX = rect.left+(rect.right-rect.left)/2;
        //x axis of the second point
        twoX = rect.left;
        //x axis of the third point
        threeX = rect.right;

        if(up){//move up button
            //y axis of the first point
            oneY = rect.top;
            //y axis of the second point
            twoY = rect.bottom;
            //y axis of the third point
            threeY = rect.bottom;

        }else{//move down button
            //y axis of the first point
            oneY = rect.bottom;
            //y axis of the second point
            twoY = rect.top;
            //y axis of the third point
            threeY = rect.top;
        }
        Path path = new Path();//create a new path
        path.setFillType(Path.FillType.EVEN_ODD);//set fill type
        //set proper color
        paint.setColor(Color.argb(100, 255, 255, 255));
        path.lineTo(oneX,oneY);//first line
        path.lineTo(twoX,twoY);//second line
        path.lineTo(threeX,threeY);//third line
        path.lineTo(oneX,oneY);//go back to the first point
        path.close();//close
        canvas.drawPath(path,paint);//draw triangle
    }
    /*
    Drawing button in different transparency depends on the status
    called by drawButtonsAndHUD()
    after it is called, a button with a prosper transparency would be drawn
     */
    public void drawSingleButton(Rect rect, boolean available){
        int active = 100;//transparency of active button
        int nonActive = 35;//transparency of non-active button
        int transparency;
        if(available){//if the button is active
            transparency = active;
        }else{//if the button is not active
            transparency = nonActive;
        }
        //set prosper transparency and color of button
        paint.setColor(Color.argb(transparency, 255, 255, 255));
        //reload the positions of the button
        RectF rf = new RectF(rect.left, rect.top,
                rect.right, rect.bottom);
        //draw the button
        canvas.drawRoundRect(rf, 15f, 15f, paint);
    }
    /*
    Update score and upgrade points whhen the game is ended
    Called by update(), after it is called, the highest score
    and rewarded upgrade points may be updated.
     */
    public void updateScores(){
        //check if the score is higher than the record
        if (player.getScore() > highestScore) {
            //Save high score
            editor.putInt("highestScore", player.getScore());
            editor.commit();
            //renew the highest score
            highestScore = player.getScore();
        }
        //calculate the reward upgrade points depends on the score
        rewardPoints = player.getScore() / REWARDSCORE;
        if (rewardPoints > 0) {//if the reward upgrade points greater than 0
            //Add reward upgrade points into the record
            editor.putInt("rewardPoints", rewardPoints + rewardPoints);
            editor.commit();
        }
    }
}


