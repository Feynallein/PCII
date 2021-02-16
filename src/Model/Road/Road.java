package Model.Road;

import Model.Moto;
import View.Gfx;

import java.awt.*;
import java.util.ArrayList;

/**
 * The road
 */
public class Road {
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
