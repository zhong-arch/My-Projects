package com.spacebattleship;

public class Flame {

    private float x,y;
    private int counter;
    private int explodeTime;
    private int radius;
    private boolean valid;
    /*
    Constructor, called by updateCanons() in Ship class and addExplodeFlame() in EnemyShip class
    after it is called, a new flame object will be created
     */
    public Flame(float x, float y, char explodeType){
        this.x = x;
        this.y = y;
        radius = 0;//radius of the flame
        counter = 0;//counter the time it last
        valid = true;

        if(explodeType == 'h'){//'h' for "hit", hit by weapons
            explodeTime = 20;//shorter last time
        }else {//exploration
            explodeTime = 30;//long last time
        }
    }
    /*
    update status of the flame
     */
    public void update(){

        radius+=2;//the radius grows
        counter++;//update time counter
        if(counter>explodeTime){//check if it is last longer enough
            valid = false;//set it to false
        }
    }
    //getter and setters
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean isValid() {
        return valid;
    }
    public int getRadius() {
        return radius;
    }
}
