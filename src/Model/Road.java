package Model;

import View.Gfx;

import java.awt.*;
import java.util.ArrayList;

public class Road {
    public class Segment{
        /**
         * Const : Height of a segment
         */
        public static final int HEIGHT = 10;

        private int p1;
        private int p2;
        private int y1;
        private int y2;
        private final static int x = Gfx.WIDTH/2;
        private final Color color;
        private int width1;
        private int width2;
        private final Moto moto;

        public Segment(int p1, int p2, Color color, Moto moto) {
            this.p1 = p1;
            this.p2 = p2;
            this.color = color;
            this.moto = moto;
            scale();
        }

        private void scale(){
            y1 = p1;
            y2 = p2;
            width1 = ROAD_WIDTH;
            width2 = width1;
            /*int scaleP1 = 10/(y1 - moto.getPosition());
            int scaleP2 = 10/(y2 - moto.getPosition());
            y1 = (Gfx.HEIGHT - scaleP1*Gfx.HEIGHT*(-1000))/2;
            y2 = (Gfx.HEIGHT - scaleP2*Gfx.HEIGHT*(-1000))/2;
            width1 = (Gfx.HEIGHT*ROAD_WIDTH*scaleP1)/2;
            width2 = (Gfx.HEIGHT*ROAD_WIDTH*scaleP2)/2;*/
        }

        public void update(){
            this.p1++;
            this.p2++;
            scale();
        }

        public int[] getX(){
            return new int[]{x - width1, x + width1, x + width2, x - width2};
        }

        public int[] getY(){
            return new int[]{y1, y1, y2, y2};
        }

        public int getP1() {
            return p1;
        }

        public int getP2() {
            return p2;
        }

        public Color getColor() {
            return color;
        }
    }

    /**
     * Const : Number of segments per rumble
     */
    public static final int RUMBLE_SIZE = 6;

    /**
     * Const : half od the road width
     */
    public static final int ROAD_WIDTH = Gfx.WIDTH/2;

    /**
     * Every segments
     */
    public ArrayList<Segment> segments = new ArrayList<>();

    private final Moto moto;

    private int idx;

    public Road(Moto moto) {
        this.moto = moto;
        for(idx = 0; idx < 500; idx++){ // arbitrary
            addSegment();
        }
    }

    public void update(){
        for(Segment s : segments){
            s.update();
        }
        if(segments.get(segments.size() - 1).getP2() > 0) {
            addSegment();
            idx++;
        }
        if(segments.get(0).getP1() > Gfx.HEIGHT) segments.remove(0);
    }

    private void addSegment(){
        segments.add(new Segment(idx*Segment.HEIGHT, (idx+1)*Segment.HEIGHT, idx/ RUMBLE_SIZE % 2 == 0 ? new Color(45, 45, 45) : new Color(40, 40, 40), moto));
    }
}
