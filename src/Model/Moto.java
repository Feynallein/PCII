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
     * Const : The player's width
     */
    public final static int WIDTH = 470/3;

    /**
     * Const : The player's height
     */
    public final static int HEIGHT = 580/3;

    /**
     * Const : The player's y position
     */
    public final static int Y = Gfx.HEIGHT - HEIGHT;

    /**
     * Const : The player's x position
     */
    public final static int X = (Gfx.WIDTH - WIDTH)/2;

    /**
     * Const : The player's max speed
     */
    public static final int MAX_SPEED = 300;

    /**
     * Which state (Braking, accelerating, free wheel)
     */
    private int state = 0;

    /**
     * Which frame of the animation
     */
    private int animation = 0;

    /**
     * A clock to calculate the animation
     */
    private int clock = 0;

    /**
     * X offset of the player
     */
    private int offset;

    /**
     * The distance traveled, based on the speed
     */
    private int position;

    /**
     * The speed of the player
     */
    private float speed;

    /**
     * Constructor
     */
    public Moto() {
        speed = 0;
        offset = 0;
        position = 1;
    }

    /**
     * Move the player to the left
     */
    public void moveLeft(){
        /*if(offset < Gfx.WIDTH/2) */offset -= MOVE_SPEED;
    }

    /**
     * Move the player to the right
     */
    public void moveRight(){
        offset += MOVE_SPEED;
    }

    /**
     * Accelerating
     */
    public void accelerate(){
        if(speed < MAX_SPEED) speed += 0.5;
        state = 2;
        anim();
    }

    /**
     * Braking
     */
    public void brake(){
        if(speed >= 0.75) speed -= 0.75;
        else if (speed > 0) speed = 0;
        state = 3;
        anim();
    }

    /**
     * Decelerating (free wheel)
     */
    public void decelerate(){
        if(speed >= 0.25) speed -= 0.25;
        else if(speed > 0) speed = 0;
        state = 1;
        if(speed == 0) state = 0;
        anim();
    }

    /**
     * Calculate the animation frame
     */
    private void anim(){
        long setup = calculateSleep();
        clock++;
        if(clock > setup) {
            animation++;
            animation %= 4;
            clock = 0;
        }
    }

    /**
     * Calculate the sleep time of TH_Scrolling, based on speed
     * @return the sleep time
     */
    public long calculateSleep(){
        return (long) (MAX_SPEED/(speed+1)) + 1;
    }

    /**
     * Getter for the speed
     * @return the speed
     */
    public float getSpeed(){
        return speed;
    }

    /**
     * Getter for the state
     * @return the state of the player
     */
    public int getState() {
        return state;
    }

    /**
     * Getter for the animation's frame
     * @return which frame of the animation it has to display
     */
    public int getAnimation(){
        return animation;
    }

    /**
     * Getter for the position
     * @return the distance traveled
     */
    public int getPosition(){
        return position;
    }

    /**
     * Add a calculated distance traveled since last time based on speed
     */
    public void addPosition(){
        // Distance traveled
        position += speed;
    }

    /**
     * Getter for the offset
     * @return the offset
     */
    public int getOffset() {
        return offset;
    }
}
