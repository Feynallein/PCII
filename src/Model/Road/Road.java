package Model.Road;

import Model.Moto;
import View.Scenes.GameScene;
import View.Scenes.Scene;

import javax.swing.text.html.HTMLDocument;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

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

    private void rightInitialize(){
        for(int i = 1; i <= MAX_TURN; i += TURNING_SPEED){
            turningRightValues.add(i);
        }
        for(int i = MAX_TURN; i >= 1; i -= TURNING_SPEED){
            turningRightValues.add(i);
        }
    }

    private void leftInitialize(){
        for(int i = -1; i >= -MAX_TURN; i -= TURNING_SPEED){
            turningLeftValues.add(i);
        }
        for(int i = -MAX_TURN; i <= 1; i += TURNING_SPEED){
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
            road.add(new Curb(i, b ? new Color(45, 45, 45) : new Color(40, 40, 40), player, height, false, iterator == null ? 0 : iterator.next()));

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

        if(turningRight && iterator == null && road.get(road.size() - 1).getxOffset() == 0) iterator = turningRightValues.iterator();
        else if(turningLeft && iterator == null && road.get(road.size() - 1).getxOffset() == 0) iterator = turningLeftValues.iterator();
        else if (!turningRight && !turningLeft || iterator != null && !iterator.hasNext()) iterator = null;

        /* Removing under the screen's curb and adding new ones */
        if (!road.isEmpty() && road.get(0).getY2() >= Scene.HEIGHT) {
            /* Adding a new curb */
            road.add(new Curb(road.get(road.size() - 1).getY2(), road.get(0).getColor(), player, 1, player.getDistanceTraveled() - lastDistance >= 100, calculateOffset()));

            //TODO: change 100 to a calculation (1 gate ~3km) -> adjustable w/ difficulty in the settings
            if (player.getDistanceTraveled() - lastDistance >= 100) lastDistance = player.getDistanceTraveled();

            if (road.get(0).isSpecialCurb()) player.addTimer(GATE_ADDING_TIME);
            /* Removing the first curb */
            road.remove(0);
        }

        /* Deciding if there is a turn */

        if(!turningLeft && !turningRight && iterator == null){
            int randomInteger = random.nextInt(1000);
            if(randomInteger < 1) turningRight = true;
            else if(randomInteger < 60) turningLeft = false;
        }

        /* If there is a turn, checking if it is finished */
        if(iterator != null) {
            if (turningRight && !iterator.hasNext()) turningRight = false;
            if (turningLeft && !iterator.hasNext()) turningLeft = false;
        }
    }

    private int calculateOffset(){
        //return iterator == null || !iterator.hasNext() ? (road.get(road.size() - 1).getxOffset() != 0 ? road.get(road.size() - 1).getxOffset() - 5 : 0) : iterator.next();
        return  iterator == null || !iterator.hasNext() ? 0 : iterator.next();
    }

    /**
     * Getter for the elements of the road
     *
     * @return the arraylist of the specified element
     */
    public ArrayList<Curb> getRoad() {
        return road;
    }

    public ArrayList<Curb> getSpecialCurbs(){
        ArrayList<Curb> res = new ArrayList<>();
        for(Curb c : road) if(c.isSpecialCurb()) res.add(c);
        return res;
    }
}
