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
        while (!player.timedOut()) {
            try {
                // Updating time based on the player's speed
                //noinspection BusyWait
                sleep(player.calculateSleep());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Update the player's speed
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
