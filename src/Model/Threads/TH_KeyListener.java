package Model.Threads;

import Controller.KeyManager;
import Model.Player;

/**
 * Player's movement thread
 */
public class TH_KeyListener extends Thread {
    /**
     * The key manager
     */
    private final KeyManager keyManager;

    /**
     * The player
     */
    private final Player player;

    /**
     * Update the key manager
     */
    @Override
    public void run() {
        while (!player.timedOut()) {
            try {
                // Updating at game's speed
                //noinspection BusyWait
                sleep(TH_Game.GAME_SPEED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Updating the key manager
            keyManager.update();
        }
    }

    /**
     * Constructor
     *
     * @param keyManager the key manager
     * @param player     the player
     */
    public TH_KeyListener(KeyManager keyManager, Player player) {
        this.keyManager = keyManager;
        this.player = player;
    }
}
