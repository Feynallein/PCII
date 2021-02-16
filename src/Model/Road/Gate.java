package Model.Road;

import Model.Moto;
import View.Gfx;

import java.awt.*;

public class Gate extends Elements{
    public static final int ADDED_TIME = 60;

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
