package Model.Road;

import Model.Player;
import View.Scenes.Scene;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class curb
 */
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
     * Coefficient of the function that calculate one curb's height based on his location
     * = ~0.1639
     */
    public final static float COEFFICIENT = 2f * (MAX_HEIGHT - 1f) / Scene.HEIGHT;

    /**
     * Origin of the function that calculate one curb's height based on his location
     * = -58
     */
    public final static int Origin = (int) (MAX_HEIGHT - COEFFICIENT * Scene.HEIGHT);


    /**
     * The array of segments
     */
    public final ArrayList<Segment> seg = new ArrayList<>();

    /**
     * The color of this curb
     */
    private final Color color;

    /**
     * The player
     */
    private final Player player;

    /**
     * True of it's a special curb (= a gate)
     */
    private final boolean specialCurb;

    /**
     * The x offset
     */
    private final int xOffset;

    /**
     * The y1 (below)
     */
    private int y1;

    /**
     * The y2 (above)
     */
    private int y2;

    /**
     * The height of this curb
     */
    private int height;

    /**
     * Constructor
     *
     * @param y1     the first y (below)
     * @param color  the color
     * @param player the player
     */
    public Curb(int y1, Color color, Player player, int height, boolean b, int xOffset) {
        this.y1 = y1;
        this.player = player;
        this.height = height;
        this.y2 = this.y1 - this.height;
        this.specialCurb = b;
        this.color = color;
        this.xOffset = xOffset;

        /* Initializing the segment's array */
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

    /* GETTER */

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

    /**
     * Return true if it's a special curb
     *
     * @return true if it's a special curb
     */
    public boolean isSpecialCurb() {
        return specialCurb;
    }

    /**
     * Getter to the mean of the y
     *
     * @return the mean of y1 and y2
     */
    public int getMeanY() {
        return y1 - height / 2;
    }

    /**
     * Getter to the full width at the middle of the curb
     *
     * @return the width of the middle of the curb
     */
    public int getMiddleFullWidth() {
        return seg.get(seg.size() / 2).getWidths()[0] + seg.get(seg.size() / 2).getWidths()[1];
    }

    /**
     * Getter to the middle of the curb
     *
     * @return the middle of the curb
     */
    public int getMiddleX() {
        return seg.size() > 0 ? seg.get(seg.size() / 2).getX()[0] : 0;
    }

    /**
     * Getter to the x offset
     *
     * @return the x offset
     */
    public int getXOffset() {
        return xOffset;
    }
}
