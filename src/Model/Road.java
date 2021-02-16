package Model;

import View.Gfx;

import java.awt.*;
import java.util.ArrayList;

/**
 * The road
 */
public class Road {
    /**
     * Segments of the road
     */
    public static class Segment{
        /**
         * Const : Height of a segment
         */
        public static final int HEIGHT = CURBING_HEIGHT / CURBING_SIZE;

        /**
         * Const : leading coefficient
         */
        private static final double coeff = -2d*(Gfx.HORIZON - Gfx.HEIGHT)/(Gfx.WIDTH - FINAL_WIDTH);

        /**
         * Const : middle of the screen
         */
        private final static int center = Gfx.WIDTH/2;

        /**
         * The first y (below)
         */
        private int y1;

        /**
         * The second y (above)
         */
        private int y2;

        /**
         * The first  width (below)
         */
        private int width1;

        /**
         * The second width (above)
         */
        private int width2;

        /**
         * The color of this segment
         */
        private final Color color;

        /**
         * The player
         */
        private final Moto moto;

        /**
         * The constructor
         * @param y1 the first y
         * @param y2 the second y
         * @param color the color
         * @param moto the player
         */
        public Segment(int y1, int y2, Color color, Moto moto) {
            this.y1 = y1;
            this.y2 = y2;
            this.color = color;
            this.moto = moto;
            // Scaling to get the widths
            scale();
        }

        /**
         * Calculate the widths of this segment
         */
        private void scale(){
            width1 = (int) ((y1 - Gfx.HEIGHT)/coeff + INITIAL_WIDTH);
            width2 = (int) ((y2 - Gfx.HEIGHT)/coeff + INITIAL_WIDTH);
        }

        /**
         * Updating the position of this segment
         */
        public void update(){
            // Incrementing the y
            y1++;
            y2++;

            // Re-scaling to the new y
            scale();
        }

        /**
         * Getter to the x
         * @return array list of x to get the bounds of this segment
         */
        public int[] getX(){
            return new int[]{(center - width1) - moto.getOffset(), (center + width1) - moto.getOffset(), (center + width2) - moto.getOffset(), (center - width2) - moto.getOffset()};
        }

        /**
         * Getter to the y
         * @return array list of y to get the bounds of this segment
         */
        public int[] getY(){
            return new int[]{y1, y1, y2, y2};
        }

        /**
         * Getter to y1
         * @return the first y
         */
        public int getY1() {
            return y1;
        }

        /**
         * Getter to y2
         * @return the second y
         */
        public int getY2() {
            return y2;
        }

        /**
         * Getter to color
         * @return the color of this segment
         */
        public Color getColor() {
            return color;
        }
    }

    /**
     * Const : Height of a rumble
     */
    public static final int CURBING_HEIGHT = 100;

    /**
     * Const : Number of segments per rumble
     */
    public static final int CURBING_SIZE = 10;

    /**
     * Const : Half of the road width
     */
    public static final int INITIAL_WIDTH = Gfx.WIDTH / 2;

    /**
     * Const : Road's minimum width
     */
    public static final int FINAL_WIDTH = INITIAL_WIDTH / 12;

    /**
     * Every segments
     */
    public ArrayList<Segment> segments = new ArrayList<>();

    /**
     * The player
     */
    private final Moto moto;

    /**
     * Constructor
     * @param moto the player
     */
    public Road(Moto moto) {
        this.moto = moto;
        for(int i = (int) Math.ceil((float) Gfx.HEIGHT/ CURBING_HEIGHT); i > 0; i--){
            for(int idx = (i * Segment.HEIGHT) - 1; idx >= (i * Segment.HEIGHT) - CURBING_SIZE; idx--) {
                segments.add(new Segment(idx * Segment.HEIGHT, (idx - 1) * Segment.HEIGHT, Math.abs((idx / CURBING_SIZE)) % 2 == 0 ? new Color(45, 45, 45) : new Color(40, 40, 40), this.moto));
            }
        }
    }

    /**
     * Update every segments
     */
    public void update(){
        // For each segments, updating...
        for(Segment s : segments){
            s.update();
        }

        // Removing if not shown below the screen's height + creating a new at the beginning of the screen
        if(segments.get(0).getY2() >= Gfx.HEIGHT){
            segments.add(new Segment(segments.get(segments.size() - 1).getY2(), segments.get(segments.size() - 1).getY2() - Segment.HEIGHT, segments.get(0).getColor(), moto));
            segments.remove(0);
        }
    }

    /**
     * Getter to the segments
     * @return the array list of segments of the road
     */
    public ArrayList<Segment> getSegment(){
        return segments;
    }
}
