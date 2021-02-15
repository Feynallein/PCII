package Model;

import View.Gfx;

import java.awt.*;
import java.util.ArrayList;

public class Road {
    public static class Segment{
        /**
         * Const : Height of a segment
         */
        public static final int HEIGHT = RUMBLE_HEIGHT/RUMBLE_SIZE;

        private int p1;
        private int p2;
        private final static int x = Gfx.WIDTH/2;
        private final Color color;
        private int width1;
        private int width2;

        public Segment(int p1, int p2, Color color) {
            this.p1 = p1;
            this.p2 = p2;
            this.color = color;
            scale();
        }

        private void scale(){
            width1 = ROAD_WIDTH;
            width2 = width1;
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
            return new int[]{p1, p1, p2, p2};
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
     * Const : Height of a rumble
     */
    public static final int RUMBLE_HEIGHT = 100;

    /**
     * Const : Number of segments per rumble
     */
    public static final int RUMBLE_SIZE = 10;

    /**
     * Const : half of the road width
     */
    public static final int ROAD_WIDTH = Gfx.WIDTH/2;

    /**
     * Every segments
     */
    public ArrayList<Segment> segments = new ArrayList<>();


    private int idx;

    public Road() {
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
        //if(segments.get(0).getP1() > Gfx.HEIGHT) segments.remove(0);
    }

    private void addSegment(){
        segments.add(new Segment(idx * Segment.HEIGHT, (idx + 1) * Segment.HEIGHT, (idx / RUMBLE_SIZE) % 2 == 0 ? new Color(45, 45, 45) : new Color(40, 40, 40)));
    }

    public Segment getSegment(int i){
        return segments.get(i);
    }
}
