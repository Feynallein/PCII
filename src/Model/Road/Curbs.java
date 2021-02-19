package Model.Road;

import Model.Moto;
import View.Gfx;

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
            //TODO: largeur fixe, mais on calcule les coordonnées x d'affichage en fct de la position y et de l'offset de la moto
            y2 = y1 - height;
            coefficient = (moto.getOffset() * (-0.00091687)) + (-2d * (Gfx.HORIZON - Gfx.HEIGHT) / (Gfx.WIDTH - Road.FINAL_WIDTH));
            width1 = calculateWidth(y1);
            width2 = calculateWidth(y2);
        }

        private int calculateWidth(int y){
            return (int) (((y - Gfx.HEIGHT) / coefficient) + Road.INITIAL_WIDTH);
        }

        @Override
        public void specialUpdate(Elements elements) { }
    }

    public final static int SIZE = 10;
    public final static int MAX_HEIGHT = 60;
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

    private void initialize() {
        int segHeight;
        seg.clear();
        for (int i = y1; i >= y2; i -= segHeight) {
            segHeight = height / SIZE;
            if (segHeight <= 0) segHeight = 1;
            seg.add(new Segment(i, color, moto, segHeight));
        }
    }

    @Override
    protected void scale() {
    }

    @Override
    public void specialUpdate(Elements e) {
        Curbs c = (Curbs) e;
        y1 = c.getY2();
        //TODO: remplacer par des calcules
        height = (int) (0.1639 * y1 - 58);
        y2 = y1 - height;

        //TODO: add instead of recreating all (optimization)
        initialize();
    }

    @Override
    public void update() {
        y1++;
        y2++;

        //TODO: add instead of recreating all (optimization)
        initialize();
    }

    public ArrayList<Elements> getSeg() {
        return seg;
    }
}
