package Model.Road;

import Model.Moto;

import java.awt.*;

/**
 * The surface marking
 */
public class SurfaceMarking extends Elements {
    //public static final int HEIGHT = Road.CURBING_HEIGHT;
    /**
     * Constructor
     *
     * @param y1    the first y (below)
     * @param y2    the second y (above)
     * @param color the color
     * @param moto  the player
     */
    public SurfaceMarking(int y1, int y2, Color color, Moto moto) {
        super(y1, color, moto, 0);
    }

    /**
     * Calculate the widths of this segment
     */
    @Override
    protected void scale() {
        width1 = 10;
        width2 = 10;
    }

    @Override
    public void specialUpdate(Elements elements) {

    }
}
