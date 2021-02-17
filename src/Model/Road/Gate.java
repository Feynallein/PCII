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
     * @param color the color
     * @param moto  the player
     */
    public Gate(int y1, Color color, Moto moto) {
        super(y1, color, moto, 0);
    }

    /**
     * Calculate the widths of this segment
     */
    @Override
    protected void scale() {
        width1 = (int) ((y1 - Gfx.HEIGHT) / coefficient + Road.INITIAL_WIDTH);
        width2 = (int) ((y2 - Gfx.HEIGHT) / coefficient + Road.INITIAL_WIDTH);
    }

    @Override
    public void specialUpdate(Elements elements) {

    }
}
