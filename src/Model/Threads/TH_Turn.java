package Model.Threads;

import Controller.KeyManager;
import Model.Moto;

/**
 * Player's movement thread
 */
public class TH_Turn extends Thread {
    /**
     * The key manager
     */
    private final KeyManager kl;

    /**
     * The player
     */
    private final Moto moto;

    /**
     * Update the key manager
     */
    @Override
    public void run() {
        while (!moto.timedOut()) {
            try {
                // Updating at game's speed
                //noinspection BusyWait
                sleep(TH_Game.GAME_SPEED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Updating the key manager
            kl.update();
        }
    }

    /**
     * Constructor
     *
     * @param kl   the key manager
     * @param moto the player
     */
    public TH_Turn(KeyManager kl, Moto moto) {
        this.kl = kl;
        this.moto = moto;
    }
}
