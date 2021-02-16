package Model.Road;

import Model.Moto;
import View.Gfx;

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

    public Elements(int y1, int y2, Moto moto) {
        this.y1 = y1;
        this.y2 = y2;
        this.moto = moto;

        // Scaling to get the widths
        scale();
    }

    abstract public void update();

    abstract protected void scale();


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
}
