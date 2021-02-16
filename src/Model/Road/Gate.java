package Model.Road;

import Model.Moto;
import View.Gfx;

import java.awt.*;

/**
 * The gates
 */
public class Gate extends Elements {
    /**
     * Const : Time added when passing a gate
     */
    public static final int ADDED_TIME = 60;

    /**
     * Constructor
     *
     * @param y1    the first y (below)
     * @param y2    the second y (above)
     * @param color the color
     * @param moto  the player
     */
    public Gate(int y1, int y2, Color color, Moto moto) {
        super(y1, y2, color, moto);
    }

    /**
     * Calculate the widths of this segment
     */
    protected void scale() {
        width1 = (int) ((y1 - Gfx.HEIGHT) / coeff + Road.INITIAL_WIDTH);
        width2 = (int) ((y2 - Gfx.HEIGHT) / coeff + Road.INITIAL_WIDTH);
    }
}
