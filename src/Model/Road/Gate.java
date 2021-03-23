package Model.Road;

import Model.Moto;
import View.Scenes.Scene;

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
     * The curbs this gate is attached to
     */
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

    /**
     * Update method
     */
    @Override
    public void update() {
        if (y1 < Scene.HEIGHT) y1 = c.getY2();
        else y1++;

        //TODO: replace by a calculation (same as in curbs)
        height = (int) (0.1639 * y1 - 58);
        scale();
    }

    /**
     * Calculate the widths of this segment
     */
    @Override
    void scale() {
        y2 = y1 - height;
        widths = calculateWidth(y1, y2);
    }

    /**
     * Calculate the widths
     *
     * @param y1 the above height
     * @param y2 the below height
     * @return the widths of every sub segment
     */
    private int[] calculateWidth(int y1, int y2) {
        return new int[]{(int) (((y1 - getOriginDecreased()) / coefficients[1]) + Road.INITIAL_WIDTH),
                (int) (((y1 - getOriginIncreased()) / coefficients[0]) + Road.INITIAL_WIDTH),
                (int) (((y2 - getOriginIncreased()) / coefficients[0]) + Road.INITIAL_WIDTH),
                (int) (((y2 - getOriginDecreased()) / coefficients[1]) + Road.INITIAL_WIDTH)
        };
    }

    /**
     * Not used here
     *
     * @param e an element
     */
    @Override
    public void specialUpdate(Elements e) {
    }
}
