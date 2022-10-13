package com.spacebattleship;

import android.graphics.Rect;

public class Canon {
    private int x;//x coordinate
    private int y;//y coordinate
    private int xVelocity;//velocity on x axis
    private final int VELOCITY = 20;//velocity of the canon
    private final int PLAYERDAMAGE = 120;//damage of the canon for player
    private final int ENEMYDAMAGE = 60;//damage of the canon for enemy
    private int canonWidth,canonHeight;//canon's width and height
    private Rect hitBox;//bitBox for collision detection
    private boolean exploded;//whether the canon is exploded
    private int damage;//damage variable
    private int DAMAGEINCREMENT = 10;//upgrade damage increment
    /*
    Constructor, called by shootCanon() in PlayerShip and EnemyShip classes
    create a new Canon class that contains variables of x and y coordinates,
    damage and whether or not it is exploded. After it is called, a new
    Canon class would be created.
     */
    Canon(int x, int y, int screenX, int screenY, char type, int level){
        this.x = x;
        this.y = y;
        switch (type){
            case'p'://if it is fired by player
                xVelocity = VELOCITY;//fly from left to right direction
                //basic player's damage plus upgrade damage values
                this.damage = PLAYERDAMAGE + DAMAGEINCREMENT * level * 2;
                break;
            case 'e'://if it is fired by an enemy
                xVelocity = -VELOCITY;//fly from right to left direction
                //basic enemy's damage plus upgrade damage values
                this.damage = ENEMYDAMAGE + DAMAGEINCREMENT * level;
                break;
        }
        canonWidth = screenX/50;//calculate appropriate width of canon
        canonHeight = screenY/100;//calculate appropriate height of canon
        exploded = false;//the canon is valid
        //set hit box for collision detection
        hitBox = new Rect(x,y,x+canonWidth,y+canonHeight);
    }
    /*
    Update all status of the canon, called by updateCanons() in Ship class
    after if is called, the canon moves on the screen, the hit box will be also
    be updated
     */
    public void update(){
        x += xVelocity;//move to a now position in the screen
        //update the hit box later collision detection
        hitBox.left = (int)(x);//left
        hitBox.top = (int)(y);//top
        hitBox.right = x + canonWidth;//right
        hitBox.bottom = y + canonHeight;//bottom
    }
    //getter and setters of variables in the class
    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }

    public int getCanonWidth() {
        return canonWidth;
    }

    public int getCanonHeight() {
        return canonHeight;
    }

    public Rect getHitBox() {
        return hitBox;
    }

    public boolean isExploded() {
        return exploded;
    }

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }

    public float getDamage() {
        return damage;
    }

}
