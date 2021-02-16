package Model.Threads;

import Controller.KeyManager;

/**
 * Player's movement thread
 */
public class TH_Turn extends Thread {
    /**
     * The key manager
     */
    private final KeyManager kl;

    /**
     * Update the key manager
     */
    @Override
    public void run() {
        while (true) {
            try {
                // Updating at game's speed
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
     * @param kl the key manager
     */
    public TH_Turn(KeyManager kl) {
        this.kl = kl;
    }
}
