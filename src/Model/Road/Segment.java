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
     * The constructor
     *
     * @param y1    the first y
     * @param y2    the second y
     * @param color the color
     * @param moto  the player
     */
    public Segment(int y1, int y2, Color color, Moto moto) {
        super(y1, y2, color, moto);
    }

    /**
     * Calculate the widths of this segment
     */
    protected void scale() {
        width1 = (int) ((y1 - Gfx.HEIGHT) / coefficient + Road.INITIAL_WIDTH);
        width2 = (int) ((y2 - Gfx.HEIGHT) / coefficient + Road.INITIAL_WIDTH);
    }
}
