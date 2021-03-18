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

    private final Curbs c;

    /**
     * Constructor
     *
     * @param y1    the first y (below)
     * @param color the color
     * @param moto  the player
     */
    public Gate(int y1, Color color, Moto moto, int height, Curbs c) {
        super(y1, color, moto, height);
        this.c = c;
    }

    @Override
    public void update() {
        if(y1 < Gfx.HEIGHT) y1 = c.getY2();
        else y1++;

        height = (int) (0.1639 * y1 - 58);
        y2 = y1 - height;
        scale();
    }

    /**
     * Calculate the widths of this segment
     */
    @Override
    void scale() {
/*        width1 = (int) ((y1 - Gfx.HEIGHT) / coefficient + Road.INITIAL_WIDTH);
        width2 = (int) ((y2 - Gfx.HEIGHT) / coefficient + Road.INITIAL_WIDTH);*/
    }

    @Override
    public void specialUpdate(Elements e) { }
}
