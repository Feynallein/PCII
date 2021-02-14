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
    public final static int WIDTH = 470/3;

    /**
     * Const : the player's height
     */
    public final static int HEIGHT = 580/3;

    /**
     * Const : the player's initial position
     */
    public final static int Y = Gfx.HEIGHT - HEIGHT;

    public final static int X = (Gfx.WIDTH - WIDTH)/2;


    public static final int MAX_SPEED = 300;

    private int state = 0;

    private int animation = 0;

    private int clock = 0;

    private int offset;

    private int position;

    /**
     * The speed of the player
     */
    private float speed;

    public int getOffset() {
        return offset;
    }

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
        /*if(offset > - Gfx.WIDTH/2) */offset += MOVE_SPEED;
    }

    public void accelerate(){
        if(speed < MAX_SPEED) speed += 0.5;
        state = 2;
        anim();
    }

    public void brake(){
        if(speed >= 0.75) speed -= 0.75;
        else if (speed > 0) speed = 0;
        state = 3;
        anim();
    }

    public void decelerate(){
        if(speed >= 0.25) speed -= 0.25;
        else if(speed > 0) speed = 0;
        state = 1;
        if(speed == 0) state = 0;
        anim();
    }

    private void anim(){
        long setup = calculateSleep();
        clock++;
        if(clock > setup) {
            animation++;
            animation %= 4;
            clock = 0;
        }
    }

    public long calculateSleep(){
        return (long) (MAX_SPEED/(speed+1)) + 1;
    }

    public float getSpeed(){
        return speed;
    }

    public int getState() {
        return state;
    }

    public int getAnimation(){
        return animation;
    }

    public int getPosition(){
        return position;
    }

    public void addPosition(){
        // Distance traveled
        position += speed;
    }
}
