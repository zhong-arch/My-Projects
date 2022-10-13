package com.spacebattleship;
import android.content.Context;
import android.graphics.Rect;
import java.util.ArrayList;
import java.util.Random;

public class GameManager {

    private int enemyNum;//number of enemies
    private final int ENEMYNUMBER = 4;//number of enemies
    private int screenX,screenY;//the width and the height of the screen
    private boolean playing;//whether the game is on pause status
    private ArrayList<Enemyship> enemyships;//list of enemies
    private PlayerShip player;//player
    private int respawn;//respawn time of enemies
    private final int RESPAWNUPGRADE = 5;//enemies get upgrade for every 5 time of respawn time
    private SoundManager soundManager;
            //= new SoundManager();;//sound manager

    private Random generator = new Random();//random generator
    /*
    Constructor, called by startGame() in the GameManager class,
    after it is called, a new GameManager class will be created.
    it manages enemies and detect all collisions in the game, if
    an enemy is destroyed by the player, the enemy will be respawned.
     */
    GameManager(Context context,int x,int y, PlayerShip player, SoundManager soundManager){

        this.player = player;
        this.soundManager = soundManager;
        screenX = x;//screen width
        screenY = y;//screen height
        playing = true;//the game is active
        enemyNum = ENEMYNUMBER;
        enemyships = new ArrayList<Enemyship>();//create a new list of enemies
        respawn = 0;//reset enemy respawn time to 0
        //soundManager.loadSound(context);//load sounds

        //create enemies
        for(int i = 0; i <enemyNum; i++){
            Enemyship ememy = new Enemyship(context,screenX,screenY);

            respawn++;//add respawn time
            enemyships.add(ememy);//at the enemy into the list
        }
    }
    /*
    Update all enemies, called by update() in the GameView class
    after it is called, all status of the enemies will be updated
     */
    public void update() {

        for (Enemyship enemy : enemyships) {
            enemy.update(player.getY());//update all enemies status

            if (!enemy.isCrushed()) {//if the enemy is still alive
                //if the enemy is ready to shoot canon or lasers
                if (enemy.isCanonReady() || enemy.isLaserReady()) {
                    switch (enemy.getEnemyType()) {//determine the type of enemy
                        case 1://the second type of enemy
                            enemy.shootCanon();//shoot canon
                            soundManager.playSound("shoot");//play sound of shooting
                            break;
                        case 2://the third type of enemy
                            enemy.shootLaser();//shoot laser
                            soundManager.playSound("laser");//play sound of shooting
                            break;
                    }
                    enemy.coolDownWeapon();//start to cool down weapons
                }
                //detect if the enemy already fly out the screen
                boolean flyOut = detectFlyOut(enemy);
                if (flyOut) {//if it already fly out the screen
                    respawn++;//add respawn counter
                    enemy.initializeEnemy();//initialize the enemy
                }
                if (!flyOut) {//if it does not fly out the screen
                    //detect if it is hit by player or crushed with player
                    boolean crash = detectHitAndCrash(enemy);
                    if (crash) {//if it is crushed
                        respawn++;//add respawn counter
                        if(respawn % RESPAWNUPGRADE == 0){//if it reach time of respawn times
                            //upgrade all enemies to increase the difficulty of the game
                            for (Enemyship eachEnemy:enemyships){
                                eachEnemy.levelUp();
                            }
                        }
                        enemy.setCrushed(true);//set it is crushed
                        soundManager.playSound("explode");//player explode sounds
                        //determine if player level up by killing the enemy
                        boolean up = player.detectLevelUp(enemy.getExp());
                        if(up){//if player levelled up
                            //player sound of levelling up
                            soundManager.playSound("levelUp");
                        }
                    }
                }//if !flyOut
            }//if (!enemy.isCrushed())
        }//for loop
    }
    /*
    Detect hits and crush between player's ship and enemy ships
    called by update(), after it is called, some proper status
    of player's ship and enemy's ships will be updated.
    be updated.
     */
    public boolean detectHitAndCrash(Enemyship enemy){

        boolean crash = false;//return boolean
        int randomNum;//a random integer
        //Detect collision of player's canons and the enemy
        for (Canon canon: player.getCanons()) {//for each of canon fired by player
            if (Rect.intersects(canon.getHitBox(), enemy.getHitBox())) {//collision detection
                enemy.getHit(canon.getDamage());//decrease health point
                enemy.addWeaponFlame(canon.getY());//add a flame on ship's body
                canon.setExploded(true);//the canon is no longer valid
                if (enemy.getHealth() < 0) {//if its health is lower than 0
                    crash = true;//set it crushed
                }
            }
        }
        //Detect collision of player's laser and enemy
        if( player.getLaser().isFiring()) {//if the player is firing a laser
            float firingPosition = player.getY()+player.getBitmap().getHeight()/2;//get firing position
            //if the ship is hit by player's laser
            if (enemy.getY() <= firingPosition && firingPosition <= enemy.getY()+enemy.getBitmap().getHeight()) {

                enemy.getHit(player.getLaser().getDamage());//deduct health by damage
                //make sure not every 5 frame create 1 flame and one "hit" sound
                //otherwise it may be a chaos
                randomNum = generator.nextInt(5);
                if(randomNum == 1) {
                    soundManager.playSound("hit");//play "hit" sound
                    enemy.addWeaponFlame(firingPosition);//add 1 flame
                }
                if(enemy.getHealth()<0) {//if the //if its health is lower than 0
                    crash = true;//set it crushed
                }
            }
        }
        //Detect collision of player and enemy
        //if the player and enemy collide with each other
        if ( Rect.intersects(player.getHitBox(), enemy.getHitBox())) {
            //deduct player's health point with remaining health point of enemy
            player.getHit(enemy.getHealth());
            soundManager.playSound("hit");//also play "hit" sound
            crash = true;
        }
        //Detect collision of enemy's canon and player
        for( Canon canon: enemy.getCanons()) {//for each canon of the enemy
            //check if the player's ship is hit by enemy's canon
            if (Rect.intersects(canon.getHitBox(), player.getHitBox())) {
                soundManager.playSound("hit");//play "hit" sound
                player.getHit(canon.getDamage());//deduct player's health point
                player.addWeaponFlame(canon.getY());//add a flame on the body of player's ship
                canon.setExploded(true);//set the canon is exploded
            }
        }
        //Detect collision of enemy's laser and player
        if( enemy.getLaser().isFiring()) {//if the enemy is firing a laser
            float firingPosition = enemy.getY()+enemy.getBitmap().getHeight()/2;//get firing position
            //if the ship is hit by enemy's laser
            if (player.getY() <= firingPosition && firingPosition <= player.getY()+enemy.getBitmap().getHeight()) {
                //make sure not every 5 frame create 1 flame and one "hit" sound
                //otherwise it may be a chaos
                randomNum = generator.nextInt(5);
                if(randomNum == 1) {
                    player.addWeaponFlame(firingPosition);//play "hit" sound
                    soundManager.playSound("hit");//add 1 flame
                }
                player.getHit(enemy.getLaser().getDamage());//deduct enemy's health point
            }
        }
        return crash;//return the boolean
    }
    /*
    Detect if the enemy already fly out from the screen
    Called by update(), after it is called, a boolean will be
    return that indicate whether the enemy's is already fly out
    from the screen
     */
    public boolean detectFlyOut(Enemyship enemy){
        boolean out = false;
        //if the enemy already fly out from the screen
        if(enemy.getX()+enemy.getBitmap().getWidth() < 0){
            out = true;
        }
        return out;//return the boolean
    }
    //switch between pause and play status
    public void switchPlayingStatus() {
        playing = !playing;
    }

    //getter and setters
    public ArrayList<Enemyship> getEnemyships() {
        return enemyships;
    }

    public int getRespawn() {
        return respawn;
    }

    public boolean isPlaying() {
        return playing;
    }
}
