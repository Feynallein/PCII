package Model.Road;

import Model.Moto;
import View.Scenes.Scene;

import java.awt.*;
import java.util.ArrayList;

public class Curb {

    /**
     * The number of segments per curbs
     */
    public final static int SIZE = 10;

    /**
     * The maximal height of a curb
     */
    public final static int MAX_HEIGHT = 60;

    /**
     * The array of segments
     */
    public final ArrayList<Segment> seg;

    /**
     * Coefficient of the function that calculate one curb's height based on his location
     * = ~0.1639
     */
    public final static float COEFFICIENT = 2f * (MAX_HEIGHT - 1f) / Scene.HEIGHT;

    /**
     * Origin of the function that calculate one curb's height based on his location
     * = -58
     */
    public final static int Origin = (int) (MAX_HEIGHT - COEFFICIENT * Scene.HEIGHT);

    private int y1;
    private int y2;
    private final Color color;
    private final Moto player;
    private int height;
    private final boolean specialCurb;
    private final int xOffset;

    /**
     * Constructor
     *
     * @param y1    the first y (below)
     * @param color the color
     * @param moto  the player
     */
    public Curb(int y1, Color color, Moto moto, int height, boolean b, int xOffset) {
        this.y1 = y1;
        this.player = moto;
        this.height = height;
        this.y2 = this.y1 - this.height;
        this.seg = new ArrayList<>();
        this.specialCurb = b;
        this.color = color;
        this.xOffset = xOffset;

        initialize();
    }

    /**
     * Initialize the array of segments
     */
    private void initialize() {
        int segHeight;

        /* Getting the old size and increasing it by 1 to approximately deduce how many segment there will be in this curb */
        int old_size = seg.size();
        old_size++;
        if (old_size > 10) old_size = 10;

        /* Clearing previous segments of this curb */
        seg.clear();

        /* The antialiasing */
        int antialiasing = 0;
        if (xOffset != 0) antialiasing = Road.TURNING_SPEED / old_size;

        /* Creating all the segments */
        for (int i = y1; i >= y2; i -= segHeight) {
            /* Changing the sign if it's a negative offset (turning left) */
            if (xOffset < 0) antialiasing *= -1;

            /* Calculating the segment's height */
            segHeight = height / SIZE;

            /* Normalization */
            if (segHeight <= 0) segHeight = 1;

            /* Creating the segment */
            seg.add(new Segment(i, color, player, segHeight, xOffset + antialiasing));

            /* Increasing the antialiasing */
            if (xOffset != 0) antialiasing += Road.TURNING_SPEED / old_size;
        }
    }

    /**
     * Special update, variation of update, only used here to update the curb and the segments
     *
     * @param c an element
     */
    public void specialUpdate(Curb c) {
        /* Casting */

        /* Getting the new y1 location */
        y1 = c.getY2();

        /* Calculating the new height */
        height = (int) (COEFFICIENT * y1 + Origin);

        /* Calculating the new y2 */
        y2 = y1 - height;

        /* Initialization of the new segments */
        initialize();
    }

    /**
     * Update the curb and the segments
     */
    public void update() {
        /* Basic update of previous' height = 1 */
        y1++;
        y2++;

        /* Initialization of the new segments */
        initialize();
    }

    /**
     * Return the array of segments
     *
     * @return the segments
     */
    public ArrayList<Segment> getSeg() {
        return seg;
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
     * Getter to the height of the element
     *
     * @return the height of the element
     */
    public int getHeight() {
        return height;
    }

    /**
     * Getter to color
     *
     * @return the color of this segment
     */
    public Color getColor() {
        return color;
    }

    public boolean isSpecialCurb() {
        return specialCurb;
    }

    public int getMeanY() {
        return y1 - height / 2;
    }

    public int getMiddleFullWidth() {
        return seg.get(seg.size() / 2).getWidths()[0] + seg.get(seg.size() / 2).getWidths()[1];
    }

    public int getMiddleX() {
        return seg.size() > 0 ? seg.get(seg.size() / 2).getX()[0] : 0;
    }

    public int getXOffset() {
        return xOffset;
    }
}
