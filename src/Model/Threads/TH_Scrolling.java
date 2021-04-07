package Model.Threads;

import Model.Player;
import Model.Road.Road;

/**
 * Thread : scroll the road
 */
public class TH_Scrolling extends Thread {
    /**
     * The road
     */
    private final Road road;

    /**
     * The player
     */
    private final Player player;

    /**
     * The thread itself
     */
    @Override
    public void run() {
        long start = System.nanoTime();
        while (!player.timedOut() && player.getLives() != 0) {
            //noinspection StatementWithEmptyBody
            while (System.nanoTime() - start < player.calculateSleep()) {

            }
            start = System.nanoTime();
            /* Update the road */
            if (player.getSpeed() > 0) road.update();
        }
    }

    /**
     * Constructor
     *
     * @param road   the road
     * @param player the player
     */
    public TH_Scrolling(Road road, Player player) {
        this.road = road;
        this.player = player;
    }
}
