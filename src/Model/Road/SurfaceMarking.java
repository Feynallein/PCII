package Model.Road;

import Model.Moto;
import View.Gfx;

import java.awt.*;

/**
 * The surface marking
 */
public class SurfaceMarking extends Elements {
    public static final int WIDTH = 2;

    private final Curbs c;

    /**
     * Constructor
     *
     * @param y1    the first y (below)
     * @param moto  the player
     */
    public SurfaceMarking(int y1, Moto moto, int height, Curbs c) {
        super(y1, Color.WHITE, moto, height);
        this.widths = new int[]{WIDTH, WIDTH, WIDTH, WIDTH};
        this.c = c;
    }

    @Override
    public void update(){
        if(y1 < Gfx.HEIGHT) y1 = c.getY2();
        else y1++;

        //TODO: remplacer w/ calculations (same as in curbs)
        height = (int) (0.1639 * y1 - 58);
        y2 = y1 - height;
        scale();
    }

    @Override
    protected void scale() {
    }

    @Override
    public void specialUpdate(Elements elements) {

    }
}
