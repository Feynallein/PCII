package Model.Road;

import Model.Moto;
import View.Gfx;

import java.awt.*;

/**
 * Segments of the road
 */
public class Segment extends Elements {
    /**
     * Const : Height of a segment
     */
    public static final int HEIGHT = Road.CURBING_HEIGHT / Road.CURBING_SIZE;

    /**
     * The color of this segment
     */
    private final Color color;

    /**
     * The constructor
     *
     * @param y1    the first y
     * @param y2    the second y
     * @param color the color
     * @param moto  the player
     */
    public Segment(int y1, int y2, Color color, Moto moto) {
        super(y1, y2, moto);
        this.color = color;
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
     * Getter to color
     *
     * @return the color of this segment
     */
    public Color getColor() {
        return color;
    }
}
