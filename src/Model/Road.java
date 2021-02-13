package Model;

import View.Gfx;

import java.awt.*;
import java.util.ArrayList;

public class Road {
    public class Segment{
        public static final int SIZE = 10;

        private int p1;
        private int p2;
        private int idx;
        private Color color;

        public Segment(int p1, int p2, int idx, Color color) {
            this.p1 = p1;
            this.p2 = p2;
            this.idx = idx;
            this.color = color;
        }

        public int[] getX(){
            return new int[]{- ROAD_WIDTH, ROAD_WIDTH, - ROAD_WIDTH, ROAD_WIDTH};
        }

        public int[] getY(){
            return new int[]{p1, p1, p2, p2};
        }

        public Color getColor() {
            return color;
        }
    }
    public static final int RUMBLE_SIZE = 6;

    /**
     * Const : half od the road width
     */
    public static final int ROAD_WIDTH = Gfx.WIDTH/4;
    public ArrayList<Segment> road = new ArrayList<>();

    public Road() {
        for(int i = 0; i < Gfx.HORIZON/Segment.SIZE; i++){
            road.add(new Segment(i*Segment.SIZE, (i+1)*Segment.SIZE, i, i/RUMBLE_SIZE % 2 == 0 ? new Color(45, 45, 45) : new Color(40, 40, 40)));
        }
    }
}
