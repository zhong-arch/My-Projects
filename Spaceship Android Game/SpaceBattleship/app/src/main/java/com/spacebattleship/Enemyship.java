package com.spacebattleship;

import static java.lang.Math.abs;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Random;

public class Enemyship extends Ship{

    //Detect enemies leaving the screen

    private int enemyType;//enemy type, there are three types of enemies
    private final int ENEMYSPEED = 2;//default speed of the enemies
    //default maximum health point value for the first type of enemies
    private final float HEALTHMAXONE = 50;
    //default maximum shield point value for the first type of enemies
    private final float SHIELDMAXONE = 50;
    //default maximum health point value for the second type of enemies
    private final float HEALTHMAXTWO = 100;
    //default maximum shield point value for the second type of enemies
    private final float SHIELDMAXTWO = 50;
    //default maximum health point value for the third type of enemies
    private final float HEALTHMAXTHREE = 150;
    //default maximum shield point value for the third type of enemies
    private final float SHIELDMAXTHREE = 100;
    //increment value for health and shield point when enemies get upgraded
    private final int SURVIVALINCREMENT = 20;
    //decrement value for cool down time of firing lasers
    //when enemies get upgraded
    private final int COOLDOWNDECREMENT = 10;
    //increment value for moving speed when enemies get upgraded
    private final int SPEEDINCREMENT = 2;
    //the value of experience when the enemy is killed by the player
    private int exp;
    //the value of experience when the first type enemy is killed by the player
    private final int EXPONE = 2;
    //the value of experience when the second type enemy is killed by the player
    private final int EXPTWO = 3;
    //the value of experience when the third type enemy is killed by the player
    private final int EXPTHREE = 6;
    private boolean crushed;//whether it is crashed
    private int explodeTime;
    //value of the time limit of explosion
    private final int EXPLODETIMELIMIT = 20;
    //random value generator
    Random generator = new Random();

    /*
    Constructor, called by GameManager class
    Create a new enemy object. After it is called, a new
    enemy object will be created.
     */
    public Enemyship(Context context, int screenX, int screenY){

        super(context,screenX,screenY);//call super class Ship
        setType('e');//'e' for enemy
        setWeaponGreen(125);//set green color value
        setFlameGreen(255);//set green color value
        initializeEnemy();//initialize all status of the enemy
    }

    /*
    Initialize all status of the enemy, called by constructor and update()
    after it is called, almost all values of the enemy will be initialized
     */
    public void initializeEnemy(){
        enemyType = generator.nextInt(3);//randomly determine enemy type
        //randomly determine if the ship initially go up or go down
        int enemyDirection = generator.nextInt(2);
        crushed = false;//it is valid
        explodeTime = 0;//counter of the explosion time
        int randomSpeedX = 0;//random speed on x axis
        int randomSpeedY = 0;//random speed on y axis

        switch ((enemyType)){//depends on the type of enemy
            case 0://the first type of enemy
                //image file citation:
                //Horton. (2015). Android Game Programming by Example (1st edition). Packt Publishing.
                setBitmap(BitmapFactory.decodeResource(getContext().getResources(),R.drawable.enemy));
                //make it smaller
                setBitmap(Bitmap.createScaledBitmap(getBitmap(),getBitmap().getWidth()/2,getBitmap().getHeight()/2,false));
                //edited image of enemy ship with blue filter
                setBitmapShield(BitmapFactory.decodeResource(getContext().getResources(),R.drawable.enemy_shield));
                //make it smaller
                setBitmapShield(Bitmap.createScaledBitmap(getBitmapShield(),getBitmapShield().getWidth()/2,getBitmapShield().getHeight()/2,false));
                //generate a random speed on x axis
                randomSpeedX = generator.nextInt(4*ENEMYSPEED)+4*ENEMYSPEED+getLevel()*SPEEDINCREMENT;
                setSpeedX(randomSpeedX);//set the speed
                setHealthMax(HEALTHMAXONE);//set the health point
                setShieldMax(SHIELDMAXONE);//set the shield point
                exp = EXPONE;//set the experience value when it is killed by player
                break;
            case 1://the second type of enemy
                //image file citation:
                //Horton. (2015). Android Game Programming by Example (1st edition). Packt Publishing.
                setBitmap(BitmapFactory.decodeResource(getContext().getResources(),R.drawable.enemy2));
                //make it smaller
                setBitmap(Bitmap.createScaledBitmap(getBitmap(),getBitmap().getWidth()/2,getBitmap().getHeight()/2,false));
                //edited image of enemy ship with blue filter
                setBitmapShield(BitmapFactory.decodeResource(getContext().getResources(),R.drawable.enemy2_shield));
                //make it smaller
                setBitmapShield(Bitmap.createScaledBitmap(getBitmapShield(),getBitmapShield().getWidth()/2,getBitmapShield().getHeight()/2,false));
                //generate a random speed on x axis
                randomSpeedX = generator.nextInt((2*ENEMYSPEED))+2*ENEMYSPEED;
                //if the ship moves faster in x axis, then it moves slower on y axis, vice versa
                randomSpeedY = 4*ENEMYSPEED-randomSpeedX;
                setSpeedX(randomSpeedX);
                if(enemyDirection == 0) {
                    setSpeedY(randomSpeedY);//set the speed of y axis and going down
                }else{
                    setSpeedY(-randomSpeedY);//set the speed of y axis and going up
                }
                //setCanons(new ArrayList<Canon>());//create an empty list of canons
                setHealthMax(HEALTHMAXTWO);//set health points
                setShieldMax(SHIELDMAXTWO);//set shield points
                setCanonTime(0);//canon cool down time counter set to 0
                setCanonReady(false);//canon is not ready at start

                exp = EXPTWO;//set the experience value when it is killed by player
                break;
            case 2://the third type of enemy
                //image file citation:
                //Horton. (2015). Android Game Programming by Example (1st edition). Packt Publishing.
                setBitmap(BitmapFactory.decodeResource(getContext().getResources(),R.drawable.enemy3));
                //make it smaller
                setBitmap(Bitmap.createScaledBitmap(getBitmap(),getBitmap().getWidth()*2/3,getBitmap().getHeight()*2/3,false));
                //edited image of enemy ship with blue filter
                setBitmapShield(BitmapFactory.decodeResource(getContext().getResources(),R.drawable.enemy3_shield));
                //make it smaller
                setBitmapShield(Bitmap.createScaledBitmap(getBitmapShield(),getBitmapShield().getWidth()*2/3,getBitmapShield().getHeight()*2/3,false));
                //generate a random speed on x axis
                randomSpeedX = generator.nextInt((2*ENEMYSPEED))+1;
                //if the ship moves faster in x axis, then it moves slower on y axis, vice versa
                randomSpeedY = 4*ENEMYSPEED-randomSpeedX;//set the speed of x axis
                setSpeedX(randomSpeedX);//set the speed of x axis

                if(enemyDirection == 0) {
                    setSpeedY(randomSpeedY);//set the speed of y axis and going down
                }else{
                    setSpeedY(-randomSpeedY);//set the speed of y axis and going up
                }
                setLaserTime(0);//laser cool down time counter set to 0
                setLaserReady(false);//laser is not ready at start
                //set health points plus additional upgraded values
                setHealthMax(HEALTHMAXTHREE+getLevel()*SURVIVALINCREMENT);
                //set shield points plus additional upgraded values
                setShieldMax(SHIELDMAXTHREE+getLevel()*SURVIVALINCREMENT);
                exp = EXPTHREE;//set the experience value when it is killed by player
                break;
        }
        setHealth(getHealthMax());//set health points
        setShield(getShieldMax());//set shield points
        setX(getMaxX()-1);//put the ship at the right edge of the screen
        //put the ship in the range of height of the screen
        setY(generator.nextInt(getMaxY() - getBitmap().getHeight()-20)+10);
        //Initialize the hit box
        setHitBox(new Rect((int)(getX()),(int)(getY()),(int)(getX()+getBitmap().getWidth()),(int)(getY()+getBitmap().getHeight())));
        coolDownWeapon();//Cool down the weapons

    }
    /*
    Updating all status of enemy, called by update() in GameManager class
    after it is called, all status of enemy will be updated
     */
    public void update(float playerY){
        if(!isCrushed()) {//if it is not crushed(still alive)
            enemyMoveAndWeapons(playerY);//update its position and shoot
            recovery();//restore some health and shield points
        }else{//if it is crushed
            //every 3 frame create 1 flame on the ship, otherwise the frame are too many
            int explode = generator.nextInt(3);
            if(explode == 1){
                addExplodeFlame();
            }
            explodeTime++;
            //the explosion animation will only remain 20 frames
            if(explodeTime>=EXPLODETIMELIMIT){
                initializeEnemy();//initialize all status of the enemy
            }
        }
        updateFlames();//update flames
        updateBlockTime();//update the time of the "blue" status
    }
    /*
    Update the position of the ship and shoot canon or lasers
    Called by update(), after it is called, the position of the ship
    will be changed and the ship may shoot canons or laser
     */
    public void enemyMoveAndWeapons(float playerY) {

        switch ((enemyType)) {//determine the type of enemy
            case 0://The first type of enemy
                if (getY() < playerY+getBitmap().getHeight()/3) {//if the position is lower than player's ship
                    //change the speed on y axis plus the upgrade speed increment
                    setSpeedY(ENEMYSPEED);
                } else if(getY() > playerY+getBitmap().getHeight()/3){//if the position is higher than player's ship
                    //change the speed on y axis plus the upgrade speed increment
                    setSpeedY(-ENEMYSPEED);
                }else{//if there is not such big gaps
                    setSpeedY(0);//not moving on y axis
                }
                break;
            case 1://The second type of enemy
                //if the ship about to fly out the screen
                if (getY() <= getMinY() || getY() >= getMaxY() - getBitmap().getHeight()) {
                    setSpeedY(-getSpeedY());//make sure it flies back
                }
                setCanonTime(getCanonTime()+1);//update the counter of cool down time of canon
                //if the counter time is more than cool down time
                if (getCanonTime() >= getCanonCoolDown() ){
                    setCanonReady(true);//set the canon ready
                }
                updateCanons();//update all canons in the list
                break;
            case 2://The third type of enemy
                //if the ship about to fly out the screen
                if (getY() <= getMinY() || getY() >= getMaxY() - getBitmap().getHeight()) {
                    setSpeedY(-getSpeedY());//make sure it flies back
                }
                setLaserTime(getLaserTime()+1);//update the counter of cool down time of laser
                if (getLaserTime() >= getLaserCoolDown() ){
                    setLaserReady(true);//set the laser ready
                }
                if(getLaser().isFiring()){//if the ship is firing laser
                    getLaser().update();//update the laser status
                }
                break;
        }
        setX(getX() - getSpeedX());//update the position on x axis
        setY(getY() + getSpeedY());//update the position on y axis
        //update the hit box for collision detection
        getHitBox().left = (int) (getX());
        getHitBox().top = (int) (getY());
        getHitBox().right = (int) (getX() + getBitmap().getWidth());
        getHitBox().bottom = (int) (getY() + getBitmap().getHeight());
    }
    /*
    Set weapon to cool down status, called by update() in GameManager class
    after it is called, the status of weapon ready will be set to false and
    generate a random cool down time, reset the cool down time counter to 0
     */
    public void coolDownWeapon(){

        switch (enemyType){
            case 1://the second type of enemy
                setCanonReady(false);//set ready status to false
                setCanonTime(0);//reset the counter
                //generate a random cool down time
                setCanonCoolDown(generator.nextInt(150)+150-getLevel()*COOLDOWNDECREMENT);
                break;
            case 2://the third type of enemy
                setLaserReady(false);//set ready status to false
                setLaserTime(0);//reset the counter
                //generate a random cool down time
                setLaserCoolDown (generator.nextInt(300)+200);
                break;
        }
    }
    /*
    Shoot a new canon, called by update() in GameManager class
    after it is called, a new canon will be created and added
    into the canon list
     */
    public void shootCanon(){
        //create a canon depends on the enemy's level
        getCanons().add(new Canon((int)(getX()-getBitmap().getWidth()),
                (int)(getY()+getBitmap().getHeight()/2),
                getMaxX(),getMaxY(), getType(),getLevel()));
    }
    /*
    Shoot a laser, called by update() in GameManager class
    after it is called, the ship will start shooting the laser
     */
    public void shootLaser(){
        //ship will start shooting the laser
        getLaser().setFiring(true);
    }
    /*
    Calculate the hit point of weapons and the ship body,
    called by addWeaponFlame() in Ship class, after it is called,
    a value of x coordinate of the ship will be returned
     */
    public float getHitPointX(float hitPointY){
        float hitPiontX = 0;
        float ratio;
        //center point of the ship on y axis
        float centerY = getY()+getBitmap().getHeight()/2;
        //calculate the ratio of the weapon's y coordinate and the center point of ship's y coordinate
        ratio = abs(centerY - hitPointY)/(getBitmap().getHeight()/2);
        //calculate the prosper point of flames
        hitPiontX = getX() + getBitmap().getWidth() / 2 * ratio;

        return hitPiontX;
    }
    /*
    Randomly generate a flame when the ship is exploded
    called by update(), when it is called, one flame will be
    added into the list of flames
     */
    public void addExplodeFlame(){

        float flameX = getX() + generator.nextInt(getBitmap().getWidth());
        float flameY = getY() + generator.nextInt(getBitmap().getHeight());
        getFlameList().add(new Flame(flameX,flameY,'e'));
    }
    /*
    getter and setters
     */
    public int getEnemyType() {
        return enemyType;
    }

    public int getExp() {
        return exp;
    }

    public boolean isCrushed() {
        return crushed;
    }

    public void setCrushed(boolean crushed) {
        this.crushed = crushed;
    }
}

