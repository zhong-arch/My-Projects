package com.spacebattleship;

import static java.lang.Math.abs;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.ArrayList;

public class PlayerShip extends Ship{

    private final int DEFAULTSPEED = 8;//default speed of player's ship

    private boolean pressingUp = false;//whether the player is pressing up button
    private boolean pressingDown = false;//whether the player is pressing down button

    private final int CANONCOOLDOWN = 30;//canon cool down time
    private final int LASERCOOLDOWN = 120;//laser cool down time
    private final float HEALTHMAX = 300;//default maximum health point
    private final float SHIELDMAX = 300;//default maximum shield point
    private int upgradePoints;//upgrade points
    private int score;//scores
    private int currentExp;//experience
    private final int REQUIREDEXP = 20;//require experience for each level
    private int bodyLevel;//body level
    private int shieldLevel;//shield level
    private int canonLevel;//canon level
    private int laserLevel;//laser level
    //increment of health and shield value when upgrading
    private final int SURVIVALINCREMENT = 50;

    //Constructor
    public PlayerShip(Context contest,int screenX, int screenY){
        super(contest,screenX, screenY);
        //image file citation:
        //Horton. (2015). Android Game Programming by Example (1st edition). Packt Publishing.
        setBitmap(BitmapFactory.decodeResource(contest.getResources(),R.drawable.ship));
        setBitmapShield(BitmapFactory.decodeResource(contest.getResources(), R.drawable.ship_shield));
        //initial position of player's ship
        setX(screenX/12);
        setY(screenY/2);
        setMaxY(screenY - getBitmap().getHeight());
        setSpeedY(DEFAULTSPEED);
        //Initialize the hit box
        setHitBox(new Rect((int)(getX()),(int)(getY()),(int)(getX()+getBitmap().getWidth()),(int)(getY()+getBitmap().getHeight())));
        setType('p');
        //Initial canon cool down time
        setCanonCoolDown(CANONCOOLDOWN);
        //Initial laser cool down time
        setLaserCoolDown(LASERCOOLDOWN);
        setCanonReady(true);//set canon ready
        setLaserReady(true);//set laser ready
        setHealthMax(HEALTHMAX);//set maximum health
        setHealth(getHealthMax());//set health
        setShieldMax(SHIELDMAX);//set maximum shield
        setShield(getShieldMax());//set shield
        upgradePoints = 1;//1 upgrade point for the start of game
        bodyLevel = 0;//body level
        shieldLevel = 0;//shield level
        canonLevel = 0;//canon level
        laserLevel = 0;//laser level
        score = 0;//score
        currentExp = 0;//current experience
        setWeaponGreen(255);//set green value of weapon flame
        setFlameGreen(125);//set green value of ship flame
    }
    /*
    Update all status of the player, called by update() in GameView class
    after it is called, the player's ship may move on the screen, it may
    shoots canon and laser, also would have some flame on the body if it is
    hit by enemies' weapons.
     */
    public void update(){

        //Are we moving?
        if (pressingUp) {
            //goes up
            setY(getY() - getSpeedY());
        }
        if (pressingDown) {
            //goes down
            setY(getY() + getSpeedY());
        }
        //But don't let ship stay off screen
        if (getY() < getMinY()) {//top edge
            setY(getMinY());
        }
        if (getY() > getMaxY()) {//bottom edge
            setY(getMaxY());
        }
        //Refresh hit box location
        getHitBox().left = (int) (getX());
        getHitBox().top = (int) (getY());
        getHitBox().right = (int) (getX() + getBitmap().getWidth());
        getHitBox().bottom = (int) (getY() + getBitmap().getHeight());

        setCanonTime(getCanonTime() + 1);//increment canon cool down counter
        if (getCanonTime() >= getCanonCoolDown()) {//check if it is ready to fire
            setCanonReady(true);//set it ready
        }
        setLaserTime(getLaserTime() + 1);//increment laser cool down counter
        if (getLaserTime() >= getLaserCoolDown()) {//check if it is ready to fire
            setLaserReady(true);//set it ready
        }

        if (getLaser().isFiring()) {//check if the player's ship is firing laser
            getLaser().update();//update its time counter
        }
        recovery();//restore some health and shield value
        updateCanons();//updates canons
        updateFlames();//update laser
        updateBlockTime();//update the time of taking damage
    }
    /*
    Shoot a canon, called by handleInput() in InputController class
    after it is called, a new canon object will be created and added
    into the list of canons.
     */
    public void shootCanon(){
            //add a new canon into the list of canons
            getCanons().add(new Canon((int)(getX()+getBitmap().getWidth()),
                    (int)(getY()+getBitmap().getHeight()/2), getMaxX(), getMaxY(), getType(),canonLevel));
            setCanonReady(false);//set the canon is not ready
            setCanonTime(0);//set the cool down counter to 0
    }
    /*
    Start to shoot laser, called by handleInput() in InputController class
    after it is called, the player's ship start to shoot a laser
     */
    public void shootLaser(){

            getLaser().setFiring(true);//start to shoot laser
            setLaserReady(false);//set it not ready
            setLaserTime(0);//set the cool down counter to 0
    }
    /*
    Detect if the player could level up
    Called by update() in GameManager class
    After it is called, if the current experience is enough to
    level up then level up
     */
    public boolean detectLevelUp(int exp){
        boolean up = false;//result
        score += exp;//update the score
        currentExp += exp;//current experience plus newly gained experience
        if(currentExp >= REQUIREDEXP){//if the experience is enough
            currentExp -= REQUIREDEXP;//deduct extra experience
            up = true;//update the result
            levelUp();//level up
            upgradePoints++;//add 1 upgrade point
        }
        return up;//return result
    }
    /*
    Upgrade ship's body
    Called by handleInput() in InputController class
    After it is called, increase the body level
     */
    public void upgradeBody() {

        upgradePoints--;//decrease upgrade point
        bodyLevel++;//increase body level
        setHealthMax(getHealthMax() + SURVIVALINCREMENT);//increase health point
        setSpeedY(getSpeedY() + 2);//increase moving speed
    }
    /*
    Upgrade ship's shield
    Called by handleInput() in InputController class
    After it is called, increase the shield level
     */
    public void upgradeShield() {

        upgradePoints--;//decrease upgrade point
        shieldLevel++;//increase shield level
        setShieldMax(getShieldMax() + SURVIVALINCREMENT);//increase shield point
    }
    /*
    Upgrade ship's canon level
    Called by handleInput() in InputController class
    After it is called, increase the canon level
     */
    public void upgradeCanon() {

        upgradePoints--;//decrease upgrade point
        canonLevel++;//increase canon level
        setCanonReady(true);//set the canon ready to fire
    }
    public void upgradeLaser() {

        upgradePoints--;//decrease upgrade point
        laserLevel++;//increase laser level
        setLaserReady(true);//set the laser ready to fire
        getLaser().upgrade();//increase laser's damage
    }
    /*
    Update the hit box of the player's ship
    Called by addWeaponFlame() in the Ship's class
    After it is called, a value of x coordinate of the ship will be returned
     */
    public float getHitPointX(float hitPointY){
        float hitPiontX = 0;//return x value
        float ratio;
        //center point of the ship on y axis
        float centerY = getY()+getBitmap().getHeight()/2;
        //calculate the ratio of the weapon's y coordinate and the center point of ship's y coordinate
        ratio = abs(centerY - hitPointY)/(getBitmap().getHeight()/2);
        //calculate the prosper point of flames
        hitPiontX = getX() + getBitmap().getWidth() - getBitmap().getWidth() / 2 * ratio;

        return hitPiontX;//return x coordinate value
    }
    //getters and setters
    public void setPressingUp(boolean pressingUp) {
        this.pressingUp = pressingUp;
    }

    public void setPressingDown(boolean pressingDown) {
        this.pressingDown = pressingDown;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getBodyLevel() {
        return bodyLevel;
    }

    public int getShieldLevel() {
        return shieldLevel;
    }

    public int getCanonLevel() {
        return canonLevel;
    }

    public int getLaserLevel() {
        return laserLevel;
    }

    public int getUpgradePoint() {
        return upgradePoints;
    }

    public void setUpgradePoints(int upgradePoints) {
        this.upgradePoints = upgradePoints;
    }
}
