package Model.Road;

import Model.Moto;
import View.Gfx;

import java.awt.*;

/**
 * Abstract class : the elements that are compose the road
 */
public abstract class Elements {
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
     * The 4 widths
     */
    protected int[] widths;

    /**
     * Height of this element
     */
    protected int height;

    /**
     * The player
     */
    protected final Moto moto;

    /**
     * The color of this segment
     */
    protected final Color color;

    /**
     * The array of the coefficients of the road's axes
     */
    protected double[] coefficients = new double[2];


    /**
     * Constructor
     *
     * @param y1    the first y (below)
     * @param color the color
     * @param moto  the player
     */
    public Elements(int y1, Color color, Moto moto, int height) {
        this.y1 = y1;
        this.color = color;
        this.moto = moto;
        this.height = height;
        this.y2 = this.y1 - this.height;
        setCoefficients();

        // Scaling to get the widths and the height
        scale();
    }

    /**
     * The scaling
     */
    abstract void scale();

    /**
     * Special update for the curbs
     * @param elements an element
     */
    public abstract void specialUpdate(Elements elements);

    /**
     * Set the coefficients of the road axes
     */
    public void setCoefficients(){
        coefficients[0] = -2d * (Gfx.HORIZON - getOriginIncreased()) / (Gfx.WIDTH - Road.FINAL_WIDTH);
        coefficients[1] = -2d * (Gfx.HORIZON - getOriginDecreased()) / (Gfx.WIDTH - Road.FINAL_WIDTH);
    }

    /**
     * Calculate the ordered at the origin with the player's offset
     * @return the ordered at the origin
     */
    protected int getOriginIncreased(){
        return (int) (((float) (Gfx.HORIZON/2 - Gfx.HORIZON)/Road.INITIAL_WIDTH) * moto.getOffset() + Gfx.HEIGHT);
    }

    /**
     * Calculate the ordered at the origin with the opposite of the player's offset
     * @return the ordered at the origin
     */
    protected int getOriginDecreased(){
        return (int) (((float) (Gfx.HORIZON/2 - Gfx.HORIZON)/Road.INITIAL_WIDTH) * -moto.getOffset() + Gfx.HEIGHT);
    }

    /**
     * Updating the position of this segment
     */
    public void update() {
        // Incrementing the y
        y1++;

        // Re-scaling to the new y
        scale();
    }

    /**
     * Getter to the x
     *
     * @return array list of x to get the bounds of this segment
     */
    public int[] getX() {
        return new int[]{(center - widths[0]), (center + widths[1]), (center + widths[2]), (center - widths[3])};
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

    /**
     * Getter to color
     *
     * @return the color of this segment
     */
    public Color getColor() {
        return color;
    }

    /**
     * Getter to the height of the element
     * @return the height of the element
     */
    public int getHeight() {
        return height;
    }

    /**
     * Getter to the centered full first width
     * @return the centered sum of the above width
     */
    public int getLoneX2(){
        return (center - (widths[2] + widths[3]));
    }

    /**
     * Getter to the centered full second width
     * @return the centered sum of the below width
     */
    public int getLoneX1(){
        return (center - (widths[0] + widths[1]));
    }

    /**
     * Getter to the sum of the first width (above)
     * @return the sum of the above width
     */
    public int getFullWidth1(){
        return widths[0] + widths[1];
    }

    /**
     * Getter to the sum of the second width (below)
     * @return the sum of the below width
     */
    public int getFullWidth2(){
        return widths[2] + widths[3];
    }

    /**
     * Getter to the mean of the full widths
     * @return the mean of the full widths
     */
    public int getMidFullWidth(){
        return (getFullWidth1() + getFullWidth2())/2;
    }

    /**
     * Getter to the mean of the lone X
     * @return the mean of the lone X
     */
    public int getMidLoneX(){
        return (getLoneX1() + getLoneX2())/2;
    }

    /**
     * Getter to the mean of the y
     * @return the mean of the y
     */
    public int getMidY(){
        return (y1 + y2)/2;
    }
}
