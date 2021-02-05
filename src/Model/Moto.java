package Model;

import View.Gfx;

/**
 * The player
 */
public class Moto {
    /**
     * Const : The player's horizontal speed
     */
    public final static int MOVE_SPEED = 3;

    /**
     * Const : the player's width
     */
    public final static int WIDTH = 50;

    /**
     * Const : the player's height
     */
    public final static int HEIGHT = 50;

    /**
     * Const : the player's initial position
     */
    public final static int Y = Gfx.HEIGHT - HEIGHT*2;


    public static final int MAX_SPEED = 350;


    /**
     * The actual player's position
     */
    private int x;

    /**
     * The speed of the player
     */
    private float speed;

    /**
     * Constructor
     */
    public Moto() {
        x = Gfx.WIDTH/2 - WIDTH/2;
        speed = 0;
    }

    /**
     * Move the player to the left
     */
    public void moveLeft(){
        if(x > 0) x -= MOVE_SPEED;
    }

    /**
     * Move the player to the right
     */
    public void moveRight(){
        if(x + WIDTH < Gfx.WIDTH) x += MOVE_SPEED;
    }

    public void accelerate(){
        if(speed < MAX_SPEED) speed += 0.5;
    }

    public void brake(){
        if(speed >= 0.75) speed -= 0.75;
        else if (speed > 0) speed = 0;
    }

    public void decelerate(){
        if(speed >= 0.25) speed -= 0.25;
        else if(speed > 0) speed = 0;
    }

    /**
     * Get the X position of the player
     * @return the position x of the player
     */
    public int getX() {
        return x;
    }

    public float getSpeed(){
        return speed;
    }
}
