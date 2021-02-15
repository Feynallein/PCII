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
        private static final double coeff = 2d*(Gfx.HORIZON - HEIGHT)/(Gfx.WIDTH - FINAL_WIDTH);
        private int p1;
        private int p2;
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

        //TODO
        private void scale(){
            /*width1 = p1 - (int) (coeff * idx + Gfx.HEIGHT);
            width2 = p2 - (int) (coeff * idx + Gfx.HEIGHT);*/
            width1 = 1280;
            width2 = width1;
        }

        public void update(){
            this.p1++;
            this.p2++;
            scale();
        }

        public int[] getX(){
            return new int[]{(x - width1) - moto.getOffset(), (x + width1) - moto.getOffset(), (x + width2) - moto.getOffset(), (x - width2) - moto.getOffset()};
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
     * Const : Half of the road width
     */
    public static final int ROAD_WIDTH = Gfx.WIDTH/2;

    /**
     * Const : Road's minimum width
     */
    public static final int FINAL_WIDTH = 50;

    /**
     * Every segments
     */
    public ArrayList<Segment> segments = new ArrayList<>();

    private final Moto moto;

    public Road(Moto moto) {
        this.moto = moto;
        int idx;
        for(int i = (int) Math.ceil((float) Gfx.HEIGHT/RUMBLE_HEIGHT); i >= 0; i--){
            for(idx = (i * Segment.HEIGHT) - 1; idx >= (i * Segment.HEIGHT) - RUMBLE_SIZE; idx--) {
                segments.add(new Segment(idx * Segment.HEIGHT, (idx - 1) * Segment.HEIGHT, Math.abs((idx / RUMBLE_SIZE)) % 2 == 0 ? new Color(45, 45, 45) : new Color(40, 40, 40), this.moto));
            }
        }
    }

    public void update(){
        for(Segment s : segments){
            s.update();
        }
        // to fix (color bug)
        if(segments.get(0).getP2() >= Gfx.HEIGHT + 1){
            segments.add(new Segment(segments.get(segments.size() - 1).getP2(), segments.get(segments.size() - 1).getP2() - Segment.HEIGHT, segments.get(0).getColor(), moto));
            segments.remove(0);
        }
    }

    public Segment getSegment(int i){
        return segments.get(i);
    }

    public int getSegmentSize(){
        return segments.size();
    }
}
