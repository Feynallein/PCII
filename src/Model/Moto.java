package Model;

import View.Gfx;

/**
 * The player
 */
public class Moto {
    /**
     * Const : The movement speed
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

    /**
     * The actual player's position
     */
    private int x;

    /**
     * Constructor
     */
    public Moto() {
        x = Gfx.WIDTH/2 - WIDTH/2;
    }

    /**
     * Move the player to the left
     */
    public void moveLeft(){
        x -= MOVE_SPEED;
    }

    /**
     * Move the player to the right
     */
    public void moveRight(){
        x += MOVE_SPEED;
    }

    /**
     * Get the X position of the player
     * @return the position x of the player
     */
    public int getX() {
        return x;
    }
}
