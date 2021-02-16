package Model.Threads;

import Model.Moto;
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
    private final Moto moto;

    /**
     * The thread itself
     */
    @Override
    public void run() {
        while (!moto.timedOut()) {
            try {
                // Updating time based on the player's speed
                //noinspection BusyWait
                sleep(moto.calculateSleep());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Update the player's speed
            if (moto.getSpeed() > 0) road.update();
        }
    }

    /**
     * Constructor
     *
     * @param road the road
     * @param moto the player
     */
    public TH_Scrolling(Road road, Moto moto) {
        this.road = road;
        this.moto = moto;
    }
}
