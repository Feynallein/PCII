package Model.Road;

import View.Scenes.Scene;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Class obstacle
 */
public class Obstacle {
    /**
     * The curb whose this obstacle is linked to
     */
    private final Curb curb;

    /**
     * The obstacle's sprite
     */
    private final BufferedImage sprite;

    /**
     * The x
     */
    private int x;

    /**
     * The y
     */
    private int y;

    /**
     * The width
     */
    private int width;

    /**
     * The height
     */
    private int height;

    private int lastX;

    /**
     * Constructor
     *
     * @param sprite the sprite of this obstacle
     * @param c      the curb whose this obstacle is linked to
     */
    public Obstacle(BufferedImage sprite, Curb c) {
        this.sprite = sprite;
        this.curb = c;
        this.width = c.getMiddleFullWidth() / 2;
        this.height = this.width / 2;
        this.x = (new Random()).nextInt(((Scene.WIDTH + c.getMiddleFullWidth())/2) - ((Scene.WIDTH - c.getMiddleFullWidth())/2)) + (Scene.WIDTH - c.getMiddleFullWidth() )/ 2;
        this.x += curb.getXOffset();
        this.y = c.getMeanY();
        this.lastX = x;
    }

    /**
     * Update this obstacle when its curb is updated
     */
    public void update() {
        y = curb.getMeanY();
        width = curb.getMiddleFullWidth() / 2;
        height = (int) (width * 0.6);
        if(x < Scene.WIDTH/2) {
            x += curb.getMiddleX() - lastX;
        }
        else {
            x += curb.getMiddleXLeftOffset() - lastX;
        }
        lastX = x;
    }

    /* GETTER */

    /**
     * Getter to the sprite
     *
     * @return the sprite
     */
    public BufferedImage getSprite() {
        return sprite;
    }

    /**
     * Getter to the x
     *
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * Getter to the y
     *
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * Getter to the width
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Getter to the height
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }
}
