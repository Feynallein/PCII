package Model.Road;

import Model.Moto;
import View.Gfx;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    private final HashMap<String, ArrayList<Elements>> road = new HashMap<>();

    /**
     * The player
     */
    private final Moto moto;

    /**
     * Constructor
     *
     * @param moto the player
     */
    public Road(Moto moto) {
        this.moto = moto;
        road.put("segment", new ArrayList<>());
        road.put("surface marking", new ArrayList<>());
        road.put("gates", new ArrayList<>());
        road.put("obstacles", new ArrayList<>());
        createRoad();



        road.get("surface marking").add(new SurfaceMarking(0, 0, Color.RED, moto));
        road.get("gates").add(new Gate(0, 0, Color.GREEN, moto));
    }

    private void createRoad() {
        for (int i = (int) Math.ceil((float) Gfx.HEIGHT / CURBING_HEIGHT); i > 0; i--) {
            for (int idx = (i * Segment.HEIGHT) - 1; idx >= (i * Segment.HEIGHT) - CURBING_SIZE; idx--) {
                road.get("segment").add(new Segment(idx * Segment.HEIGHT, (idx - 1) * Segment.HEIGHT, Math.abs((idx / CURBING_SIZE)) % 2 == 0 ? new Color(45, 45, 45) : new Color(40, 40, 40), this.moto));
            }
        }
    }

    /**
     * Update every segments
     */
    public void update() {
        road.forEach((s, a) -> {
            for (Elements e : a) {
                e.update();
            }
        });

        // Removing if below the screen's height + adding a new one (not for gates)
        for(String s : new String[]{"segment", "surface marking", "gates"}){
            if(road.get(s).get(0).getY2() >= Gfx.HEIGHT){
                int lastIndex = road.get(s).size() - 1;
                if(s.equals("segment"))
                    road.get(s).add(new Segment(road.get(s).get(lastIndex).getY2(), road.get(s).get(lastIndex).getY2() - Segment.HEIGHT, road.get(s).get(0).getColor(), moto));
                else if(s.equals("surface marking"))
                    road.get(s).add(new SurfaceMarking(road.get(s).get(lastIndex).getY2(), road.get(s).get(lastIndex).getY2() - Segment.HEIGHT, road.get(s).get(0).getColor(), moto));
                road.get(s).remove(0);
            }
        }

        // Adding gates for a  certain distance
        //if((moto.getPosition() / 1000) % 10 == 0) el.add(new Gate(0, CURBING_HEIGHT, Color.RED, moto));
    }

    /**
     * Getter to the segments
     *
     * @return the array list of segments of the road
     */
    public ArrayList<Elements> getSegment() {
        return road.get("segment");
    }

    public ArrayList<Elements> getElements() {
        return road.get("gates");
    }
}
