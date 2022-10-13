package com.spacebattleship;

import static java.lang.Math.abs;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Random;

public abstract class Ship {
    private Bitmap bitmap;//bitmap image
    private Bitmap bitmapShield;//bitmap image blue version
    private float x,y;//x and y coordinates on screen
    private Context context;
    //The minimum and the maximum coordinates on screen
    private int maxX, minX,maxY,minY;

    private float speedX,speedY;//speed on x axis and y axis
    //A hit box for collision detection
    private Rect hitBox;
    private ArrayList<Canon> canons;//list of canons
    private int canonCoolDown;//canon cool down time
    //whether the ship is ready to fired a canon
    private boolean canonReady;
    private int canonTime;//time counter for canon's cool down time
    private Laser laser;//points to laser object
    private int laserCoolDown;//laser cool down time
    private boolean laserReady;
    private int laserTime;//time counter for laser's cool down time
    //whether the ship is ready to fired a laser
    private char type;//distinguish between player and enemy's ship
    private float health;//health value
    private float healthMax;//maximum health value
    private float shield;//shield value
    private float shieldMax;//maximum shield value
    private ArrayList<Flame> flameList;//list of flames
    private int weaponGreen;//green value of RGB for the weapon
    private int flameGreen;//green value of RGB for the flame
    private int blockTime;//the time counter for taking damage
    private final int BLOCKTIME = 10;//the time limit of blocking damage
    private int level;//level of the ship
    //Constructor, called by the startGame() in Gameview class
    //and Constructor in GameManager class.
    //After it is called, a new ship object will be created
    public Ship(Context context, int screenX, int screenY){
        this.context = context;
        maxX = screenX;//screen width
        maxY = screenY;//screen height
        minX = 0;//left edge of screen
        minY = 0;//top edge of the screen
        canons = new ArrayList<Canon>();//create a new list of canons
        laser = new Laser(maxY);
        flameList = new ArrayList<Flame>();
        blockTime = 0;//the time counter for taking damage
        level = 0;//level of the ship
    }
    /*
    Recovery some amount of health and shield
    Called by update(), after it is called, the
    health and shield values will be restored.
     */
    public void recovery(){
        double healthRecovery = healthMax/1500;//health recovery rate
        if(health < healthMax){//if it is not maximum
            health += healthRecovery;//recovery
        }
        double shieldRecovery = healthMax/1500;//shield recovery rate
        if(shield < shieldMax){//if it is not maximum
            shield += shieldRecovery;//recovery
        }
    }
    /*
    Take damage from player or enemies
    Called by detectHitAndCrash() in GameManager class
    After it is called, it will determine the damage and decrease
    the health point, if the shi has remaining shield points,
    then it would add some block times and turn the ship into
    blue color.
     */
    public void getHit(float damage){
        shield -= damage;//decrease the shield point first
        if( shield < 0){//if the shield point is not enough
            health += shield;//decrease the health point
            shield = 0;//set it to 0
        }else if (shield > 10){//if the shield has enough shield points
            blockTime = BLOCKTIME;//shows the ship is taking damage
        }
    }
    /*
    Update canon status, called by update() in both Playship and Enemyship class
    After it is called, the canon status will be updated.
     */
    public void updateCanons(){
        if(canons.size()>0){//check if the list has at least one canon

            int index = 0;//index variable
            while (index < canons.size()){//iterate the list
                Canon canon = canons.get(index);
                canon.update();//update the canon
                //check if the canon is already exploded or fly out the screen
                if(canon.isExploded()||canon.getX()<-canon.getCanonWidth()||canon.getX()>maxX){
                    //remove the canon from the list
                    canons.remove(index);
                }else {
                    index++;//continue iterate
                }
            }//while loop
        }//if
    }
    /*
    Adding flame one the body of ship when taking damage
    Called by DetectHitandCrush() in GameManager class
    After it is called, a flame will be added into the list
    of flames and the body of the ship will have a flame on it
     */
    public void addWeaponFlame(float hitPointY){
        //calculate prosper x coordinates by the y coordinate of weapon
        float hitPointX = getHitPointX(hitPointY);
        flameList.add(new Flame(hitPointX,hitPointY,'h'));//add the flame
    }
    /*
    Update the flames on the ship, called by update()
    After it is called, the status of flames will be updated.
     */
    public void updateFlames(){
        if(flameList.size()>0){//if the list has at least one flame

            int index = 0;//index variable
            while (index < flameList.size()){//iterate the list
                Flame flame = flameList.get(index);
                flame.update();//update the flame
                if(!flame.isValid()){//if the flame is not valid
                    flameList.remove(index);//remove it
                }else {
                    index++;
                }
            }
        }
    }
    /*
    Update the blocking status of the ship
    Called by update() in both Playship and Enemyship class,
    decrement the block time, if the ship is still blocking
    damage, then the blue version of ship will be drawn.
     */
    public void updateBlockTime(){
        if(blockTime > 0){
            blockTime--;
        }
    }
    /*
    Increase the level, called by detectHitAndCrash() in GameManager class
    The level of the ship will be increased when it is called.
     */
    public void levelUp(){
        level++;
    }
    /*
    Update the hit box of the ship
    Called by addWeaponFlame() in the Ship's class
    After it is called, a value of x coordinate of the ship will be returned
     */
    public abstract float getHitPointX(float hitPointY);
    /*
    Shoot a canon, called by handleInput() in InputController class
    after it is called, a new canon object will be created and added
    into the list of canons.
     */
    public abstract void shootCanon();
    /*
        Start to shoot laser, called by handleInput() in InputController class
        after it is called, the player's ship start to shoot a laser
    */
    public abstract void shootLaser();
    //getters and setters
    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmapShield() {
        return bitmapShield;
    }

    public void setBitmapShield(Bitmap bitmapShield) {
        this.bitmapShield = bitmapShield;
    }

    public Context getContext() {
        return context;
    }

    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getMinY() {
        return minY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public float getSpeedX() {
        return speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public Rect getHitBox() {
        return hitBox;
    }

    public void setHitBox(Rect hitBox) {
        this.hitBox = hitBox;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getHealthMax() {
        return healthMax;
    }

    public void setHealthMax(float healthMax) {
        this.healthMax = healthMax;
    }
    public float getShield() {
        return shield;
    }

    public void setShield(float shield) {
        this.shield = shield;
    }

    public float getShieldMax() {
        return shieldMax;
    }

    public void setShieldMax(float shieldMax) {
        this.shieldMax = shieldMax;
    }

    public ArrayList<Canon> getCanons() {
        return canons;
    }

    public void setCanons(ArrayList<Canon> canons) {
        this.canons = canons;
    }

    public int getCanonCoolDown() {
        return canonCoolDown;
    }

    public void setCanonCoolDown(int canonCoolDown) {
        this.canonCoolDown = canonCoolDown;
    }

    public int getLaserCoolDown() {
        return laserCoolDown;
    }

    public void setLaserCoolDown(int laserCoolDown) {
        this.laserCoolDown = laserCoolDown;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public Laser getLaser() {
        return laser;
    }

    public void setLaser(Laser laser) {
        this.laser = laser;
    }

    public boolean isCanonReady() {
        return canonReady;
    }

    public void setCanonReady(boolean canonReady) {
        this.canonReady = canonReady;
    }
    public boolean isLaserReady() {
        return laserReady;
    }

    public void setLaserReady(boolean laserReady) {
        this.laserReady = laserReady;
    }
    public int getCanonTime() {
        return canonTime;
    }
    public void setCanonTime(int canonTime) {
        this.canonTime = canonTime;
    }

    public int getLaserTime() {
        return laserTime;
    }

    public void setLaserTime(int laserTime) {
        this.laserTime = laserTime;
    }

    public ArrayList<Flame> getFlameList() {
        return flameList;
    }

    public int getWeaponGreen() {
        return weaponGreen;
    }

    public int getFlameGreen() {
        return flameGreen;
    }

    public void setWeaponGreen(int weaponGreen) {
        this.weaponGreen = weaponGreen;
    }

    public void setFlameGreen(int flameGreen) {
        this.flameGreen = flameGreen;
    }

    public int getBlockTime() {
        return blockTime;
    }

    public int getLevel() {
        return level;
    }
}
