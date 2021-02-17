package Model.Road;

import Model.Moto;
import View.Gfx;

import java.awt.*;

/**
 * Abstract class : the elements that are compose the road
 */
public abstract class Elements {
    /**
     * Const : leading coefficient
     */
    public static final double coefficient = -2d * (Gfx.HORIZON - Gfx.HEIGHT) / (Gfx.WIDTH - Road.FINAL_WIDTH);

    /**
     * Const : middle of the screen
     */
    public final static int center = Gfx.WIDTH / 2;

    /**
     * The first y (below)
     */
    protected int y1;

    /**
     * The second y (above)
     */
    protected int y2;

    /**
     * The first width (below)
     */
    protected int width1;

    /**
     * The second width (above)
     */
    protected int width2;

    protected int height;

    /**
     * The player
     */
    protected final Moto moto;

    /**
     * The color of this segment
     */
    protected final Color color;

    /**
     * Constructor
     *
     * @param y1     the first y (below)
     * @param color  the color
     * @param moto   the player
     */
    public Elements(int y1, Color color, Moto moto, int height) {
        this.y1 = y1;
        this.color = color;
        this.moto = moto;
        this.height = height;
        this.y2 = this.y1 - this.height;

        // Scaling to get the widths and the height
        scale();
    }

    /**
     * The scaling
     */
    abstract void scale();

    /**
     * Updating the position of this segment
     */
    public void update() {
        // Incrementing the y
        y1++;

        // Re-scaling to the new y
        scale();
    }

    /**
     * Getter to the x
     *
     * @return array list of x to get the bounds of this segment
     */
    public int[] getX() {
        return new int[]{(center - width1) - moto.getOffset(), (center + width1) - moto.getOffset(), (center + width2) - moto.getOffset(), (center - width2) - moto.getOffset()};
    }

    /**
     * Getter to the y
     *
     * @return array list of y to get the bounds of this segment
     */
    public int[] getY() {
        return new int[]{y1, y1, y2, y2};
    }

    /**
     * Getter to y1
     *
     * @return the first y
     */
    public int getY1() {
        return y1;
    }

    /**
     * Getter to y2
     *
     * @return the second y
     */
    public int getY2() {
        return y2;
    }

    /**
     * Getter to color
     *
     * @return the color of this segment
     */
    public Color getColor() {
        return color;
    }

    public int getHeight(){
        return height;
    }

    public abstract void specialUpdate(Elements elements);
}
