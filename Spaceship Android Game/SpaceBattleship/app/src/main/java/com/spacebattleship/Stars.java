package com.spacebattleship;

import java.util.Random;

public class Stars {

    private int x,y;
    private int speed;
    private int radius;

    //Detect dust leaving the screen
    private int maxX,maxY,minX,minY;

    /*
    Constructor, called by startGame() in GameView class
    After it is called, a new object of star will be created
     */
    public Stars(int screenX, int screenY){
        maxX = screenX;//screen width
        maxY = screenY;//screen height
        minX = 0;//left edge of screen
        minY = 0;//top edge of screen

        Random generator = new Random();//random generator
        //Set a radius between 0 and 5
        radius = generator.nextInt(5);
        //Set a speed between 1 and 6
        speed = generator.nextInt(5)+1;
        //Set the starting coordinates
        x = generator.nextInt(maxX);//randomly generate a x coordinate
        y = generator.nextInt(maxY);//randomly generate a y coordinate
    }
    /*
    Update positions of stars and check if it is out of screen
    Called by update() in GameView class, if the star is out the screen
    then initialize it with a new position
     */
    public void update(){
        //Speed up when the play does
        x -= speed;

        //respawn space dust
        if(x<minX){//if it is out of screen
            x = maxX;//put it at the right edge of screen
            Random generator = new Random();
            y = generator.nextInt(maxY);
            //Set a radius between 0 and 5
            radius = generator.nextInt(5);
            //Set a speed between 1 and 6
            speed = generator.nextInt(5)+1;
        }
    }
    //getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }
}
