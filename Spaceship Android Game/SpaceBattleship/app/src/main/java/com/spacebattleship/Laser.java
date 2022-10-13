package com.spacebattleship;

import android.graphics.Rect;

public class Laser {

    private int timeCount;//time counter how much time the ship shoots laser
    private boolean firing;//whether or not ship is shooting laser
    private final int FIRETIMELIMIT = 50;//firing time limit
    private int laserHeight;//height of laser itself
    private final int DAMAGE = 10;//damage per frame
    private int damage;//damage variable

    /*
    Constructor, called by constructor of Ship class,
    after it is called, laser will be set up
     */
    Laser(int screenY){

        timeCount = 0;//initialize time counter
        firing = false;//it is not firing yet
        //get a prosper height of laser depends on the height of screen
        laserHeight = screenY/50;
        this.damage = DAMAGE;//set up damage

    }
    /*
    update the status of the laser, called by update() in Ship class/
    After it is called, the status of the laser will be updated
     */
    public void update(){

        if(firing){//if the ship is firing the laser
            timeCount++;//counter +1
            if(timeCount >= FIRETIMELIMIT){//if the counter is greater than limit
                firing = false;//set it not firing
                timeCount = 0;//reset the counter
            }
        }
    }
    /*
    Increase the damage of laser, called by upgradeLaser() playerShip class.
    After it is called, the damage of laser will be increased
     */
    public void upgrade(){
        damage++;//increase the damage
    }
    //getter and setters
    public boolean isFiring() {
        return firing;
    }

    public void setFiring(boolean firing) {
        this.firing = firing;
    }

    public int getLaserHeight() {
        return laserHeight;
    }

    public float getDamage() {
        return damage;
    }
}
