package Model.Road;

import Model.Moto;

import java.awt.*;
import java.util.ArrayList;

public class Curbs extends Elements {
    /**
     * Segments of the road
     */
    private static class Segment extends Elements {
        /**
         * Constructor
         *
         * @param y1    the first y (below)
         * @param color the color
         * @param moto  the player
         */
        public Segment(int y1, Color color, Moto moto, int height) {
            super(y1, color, moto, height);
        }

        @Override
        protected void scale() {
            y2 = y1 - height;
            widths = calculateWidth(y1, y2);
        }

        private int[] calculateWidth(int y1, int y2){
            return new int[]{(int) (((y1 - getOriginDecreased()) / coefficients[1]) + Road.INITIAL_WIDTH),
                        (int) (((y1 - getOriginIncreased()) / coefficients[0]) + Road.INITIAL_WIDTH),
                        (int) (((y2 - getOriginIncreased()) / coefficients[0]) + Road.INITIAL_WIDTH),
                        (int) (((y2 - getOriginDecreased()) / coefficients[1]) + Road.INITIAL_WIDTH)
            };
        }

        /**
         * Not used here
         * @param elements an element
         */
        @Override
        public void specialUpdate(Elements elements) { }
    }

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
    public final ArrayList<Elements> seg;

    /**
     * Constructor
     *
     * @param y1    the first y (below)
     * @param color the color
     * @param moto  the player
     */
    public Curbs(int y1, Color color, Moto moto, int height) {
        super(y1, color, moto, height);
        this.seg = new ArrayList<>();
        initialize();
    }

    /**
     * Initialize the array of segments
     */
    private void initialize() {
        int segHeight;
        seg.clear();
        for (int i = y1; i >= y2; i -= segHeight) {
            segHeight = height / SIZE;
            if (segHeight <= 0) segHeight = 1;
            seg.add(new Segment(i, color, moto, segHeight));
        }
    }

    /**
     * Not used here
     */
    @Override
    protected void scale() { }

    //TODO: pour les courbes : changer la position en x max (comme le bug que y'a avec gate et SM)

    /**
     * Special update, variation of update, only used here to update the curb and the segments
     * @param e an element
     */
    @Override
    public void specialUpdate(Elements e) {
        Curbs c = (Curbs) e;
        y1 = c.getY2();
        //TODO: remplacer par des calcules
        height = (int) (0.1639 * y1 - 58);
        y2 = y1 - height;

        //Possible optimization : add instead of recreating all
        initialize();
    }

    /**
     * Update the curb and the segments
     */
    @Override
    public void update() {
        y1++;
        y2++;

        //Possible optimization : add instead of recreating all
        initialize();
    }

    /**
     * Return the array of segments
     * @return the segments
     */
    public ArrayList<Elements> getSeg() {
        return seg;
    }
}
