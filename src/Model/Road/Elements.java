package Model.Road;

import Model.Moto;
import View.Gfx;

import java.awt.*;

public abstract class Elements {
    /**
     * Const : leading coefficient
     */
    public static final double coeff = -2d * (Gfx.HORIZON - Gfx.HEIGHT) / (Gfx.WIDTH - Road.FINAL_WIDTH);

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

    /**
     * The player
     */
    protected final Moto moto;

    /**
     * The color of this segment
     */
    protected final Color color;

    public Elements(int y1, int y2, Color color, Moto moto) {
        this.y1 = y1;
        this.y2 = y2;
        this.color = color;
        this.moto = moto;

        // Scaling to get the widths
        scale();
    }

    /**
     * Calculate the widths of this segment
     */
    protected void scale() {
        width1 = (int) ((y1 - Gfx.HEIGHT) / coeff + Road.INITIAL_WIDTH);
        width2 = (int) ((y2 - Gfx.HEIGHT) / coeff + Road.INITIAL_WIDTH);
    }

    /**
     * Updating the position of this segment
     */
    public void update() {
        // Incrementing the y
        y1++;
        y2++;

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
}
