package Model.Road;

import Model.Moto;
import View.Gfx;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The road
 */
public class Road {
    /**
     * Const : Half of the road width
     */
    public static final int INITIAL_WIDTH = Gfx.WIDTH / 2;

    /**
     * Const : Road's minimum width
     */
    public static final int FINAL_WIDTH = INITIAL_WIDTH / 12;

    /**
     * Const : segments key for the hashmap
     */
    public final static String CURBS = "curbs";

    /**
     * Const : obstacles key for the hashmap
     */
    public final static String OBS = "obstacles";

    /**
     * Const : gates key for the hashmap
     */
    public final static String GATES = "gates";

    /**
     * Const : surface marking key for the hashmap
     */
    public final static String SM = "surface marking";

    /**
     * Last distance where there was a gate
     */
    private int lastDistance;

    /**
     * Hashmap of the different objects that compose the road
     */
    private final HashMap<String, ArrayList<Elements>> road;


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
        this.lastDistance = 0;
        this.road = new HashMap<>();

        // Creating the different sub-array lists
        road.put(CURBS, new ArrayList<>());
        road.put(SM, new ArrayList<>());
        road.put(GATES, new ArrayList<>());
        road.put(OBS, new ArrayList<>());

        // Creating the road
        createRoad();
    }

    private void createRoad() {
        int height;
        boolean b = true;
        for (int i = Gfx.HEIGHT; i > Gfx.HORIZON; i -= height) {
            height = (int) (0.1639 * i - 58);
            if (height <= 0) height = 1;
            road.get(CURBS).add(new Curbs(i, b ? new Color(45, 45, 45) : new Color(40, 40, 40), moto, height));
            if(b) road.get(SM).add(new SurfaceMarking(i,moto, height, (Curbs) road.get(CURBS).get(road.get(CURBS).size() - 1)));
            b = !b;
        }
    }

    /**
     * Update every segments
     */
    public void update() {
        //Todo: optimizations
        road.forEach((s, a) -> {
            for (Elements e : a) {
                if (!(e instanceof Curbs)){
                    e.update();
                }
            }
        });

        for (int i = 0; i < road.get(CURBS).size(); i++) {
            if (i != 0) {
                road.get(CURBS).get(i).specialUpdate(road.get(CURBS).get(i - 1));
            }
            else road.get(CURBS).get(i).update();
        }

        // Removing if below the screen's height + adding a new one (not for gates)
        for (String s : new String[]{CURBS, SM, GATES}) {
            if (!road.get(s).isEmpty() && road.get(s).get(0).getY2() >= Gfx.HEIGHT) {
                int lastIndex = road.get(s).size() - 1;
                switch (s) {
                    // Adding a new Curbs
                    //TODO: -> j'pense que la coulour qui merde c'est ici, ca prend la couleur du dernier qui est la même que le dernier du coup ca fait un doublon
                    case CURBS -> road.get(s).add(new Curbs(road.get(s).get(lastIndex).getY2(), road.get(s).get(0).getColor(), moto, 1));
                    // Adding a new surface marking
                    //TODO: -> une fois y'a un doublon, je pense c'est un peu la même chose au dessus, avec genre en rajouter un a la fin donc ca se colle
                    case SM -> road.get(s).add(new SurfaceMarking(road.get(CURBS).get(road.get(CURBS).size() - 1).getY2(), moto, 1, (Curbs) road.get(CURBS).get(road.get(CURBS).size() - 1)));
                    // Adding time to the timer
                    case GATES -> moto.addTimer(Gate.ADDED_TIME);
                }
                // Removing the object
                road.get(s).remove(0);
            }
        }

        // Adding gates for every 3 km (the distance is in meters)
        if (moto.getDistanceTraveled() - lastDistance >= 3000) { //TODO: calculation instead of hard coding this + adjustable difficulty (w/ the menu & settings)
            road.get(GATES).add(new Gate(road.get(CURBS).get(road.get(CURBS).size() - 1).getY2(), Color.RED, moto, 1, (Curbs) road.get(CURBS).get(road.get(CURBS).size() - 1)));
            lastDistance = moto.getDistanceTraveled();
        }
    }

    /**
     * Getter for the elements of the road
     *
     * @param s the element desired must be a key of the hashmap
     * @return the arraylist of the specified element
     */
    public ArrayList<Elements> get(String s) {
        return road.get(s);
    }
}
