package Model.Road;

import Model.Moto;
import View.Gfx.Assets;
import View.Scenes.GameScene;
import View.Scenes.Scene;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;
import java.util.logging.XMLFormatter;

/**
 * The road
 */
public class Road {
    /**
     * Const : Half of the road width
     */
    public static final int INITIAL_WIDTH = Scene.WIDTH / 2;

    public static final int GATE_ADDING_TIME = 60;

    /**
     * Const : Road's minimum width
     */
    public static final int FINAL_WIDTH = INITIAL_WIDTH / 12;

    /**
     * Hashmap of the different objects that compose the road
     */
    private final ArrayList<Curb> road;

    /**
     * The player
     */
    private final Moto player;

    private int lastDistance;
    private boolean turningRight;
    private boolean turningLeft;
    private final ArrayList<Integer> turningRightValues = new ArrayList<>();
    private final ArrayList<Integer> turningLeftValues = new ArrayList<>();
    private Iterator<Integer> iterator;
    private final Random random = new Random(101218);
    public final static int MAX_TURN = 300;
    public final static int TURNING_SPEED = 10;
    //1 gate ~3km
    public final static int DISTANCE_BW_GATES = 3000;
    public final ArrayList<Obstacle> obstacles = new ArrayList<>();


    /**
     * Constructor
     */
    public Road(Moto player) {
        this.road = new ArrayList<>();
        this.player = player;
        this.turningRight = false;
        this.turningLeft = false;

        /* Initialize the turns*/
        rightInitialize();
        leftInitialize();

        /* Creating the road */
        createRoad();
    }

    /* Initialize the offset for a right turn */
    private void rightInitialize() {
        for (int i = 1; i <= MAX_TURN; i += TURNING_SPEED) {
            turningRightValues.add(i);
        }
        for (int i = MAX_TURN - 1; i >= 1; i -= TURNING_SPEED) {
            turningRightValues.add(i);
        }
    }

    /* Initialize the offset for a left turn */
    private void leftInitialize() {
        for (int i = -1; i >= -MAX_TURN; i -= TURNING_SPEED) {
            turningLeftValues.add(i);
        }
        for (int i = -MAX_TURN + 1; i <= 1; i += TURNING_SPEED) {
            turningLeftValues.add(i);
        }
    }

    /**
     * Create the road
     */
    private void createRoad() {
        int height;
        boolean b = true;

        /* Going from the bottom of the screen to the horizon */
        for (int i = Scene.HEIGHT; i >= GameScene.HORIZON; i -= height) {
            /* Calculating the height */
            height = (int) (Curb.COEFFICIENT * i + Curb.Origin);

            /* Normalization */
            if (height <= 0) height = 1;

            /* Creating a new curb */
            road.add(new Curb(i, b ? new Color(45, 45, 45) : new Color(40, 40, 40), player, height, false, 0));

            /* Changing the color */
            b = !b;
        }
    }

    /**
     * Update every curbs
     */
    public void update() {
        /* Special update or basic update the curbs depending on their position */
        for (int i = 0; i < road.size(); i++) {
            if (i != 0) {
                road.get(i).specialUpdate(road.get(i - 1));
            } else road.get(i).update();
        }

        /* Updating obstacles */
        for(Obstacle o : obstacles){
            o.update();
        }

        /* Deciding if there is a turn (random) */
        if (!turningLeft && !turningRight && iterator == null && road.get(road.size() - 1).getXOffset() == 0) {
            int randomInteger = random.nextInt(1000); //todo: 1000 a changer (bcp incr)
            if (randomInteger < 1) turningRight = false;
            else if (randomInteger < 2) turningLeft = false;//todo: temp, remettre a true!!
            //todo: val a changer
        }

        /* Updating the iterators and the booleans */
        if (turningRight && iterator == null) iterator = turningRightValues.iterator();
        else if (turningLeft && iterator == null) iterator = turningLeftValues.iterator();
        else if (!turningRight && !turningLeft) iterator = null;

        /* If there is a turn, checking if it is finished */
        if (iterator != null && !iterator.hasNext()) {
                if (turningRight) turningRight = false;
                else if (turningLeft) turningLeft = false;
        }

        /* Removing under the screen's curb and adding new ones */
        if (!road.isEmpty() && road.get(0).getY2() >= Scene.HEIGHT) {
            /* Adding a new curb */
            Curb c = new Curb(road.get(road.size() - 1).getY2(), road.get(0).getColor(), player, 1,
                    player.getDistanceTraveled() - lastDistance >= DISTANCE_BW_GATES, getXOffset());
            road.add(c);
            if(random.nextInt(5000) < 1000){//todo: tweak values
                obstacles.add(new Obstacle(Assets.obstacles[0], c));
            }

            /* Changing the distance traveled when crossing a gate */
            if (player.getDistanceTraveled() - lastDistance >= DISTANCE_BW_GATES)
                lastDistance = player.getDistanceTraveled();

            /* Adding time to timer if a gate is crossed */
            if (road.get(0).isSpecialCurb()) player.addTimer(GATE_ADDING_TIME);

            /* Removing the first curb */
            road.remove(0);
        }

        /* removing out of screen obstacles */
        obstacles.removeIf(o -> o.getY() > Scene.HEIGHT);
    }

    private int getXOffset(){
        return iterator == null || !iterator.hasNext() ? 0 : iterator.next();
    }

    /**
     * Getter for the elements of the road
     *
     * @return the arraylist of the specified element
     */
    public ArrayList<Curb> getRoad() {
        return road;
    }

    /**
     * Get the array of special curbs
     *
     * @return the special curbs
     */
    public ArrayList<Curb> getSpecialCurbs() {
        ArrayList<Curb> res = new ArrayList<>();
        for (Curb c : road) if (c.isSpecialCurb()) res.add(c);
        return res;
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }
}
