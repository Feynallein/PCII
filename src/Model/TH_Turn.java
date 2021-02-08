package Model;

import Controller.KeyManager;

/**
 * Player's movement thread
 */
public class TH_Turn extends Thread {
    /**
     * The key manager
     */
    private KeyManager kl;

    /**
     * Update the key manager
     */
    @Override
    public void run() {
        while(true) {
            try {
                sleep(TH_Game.FRAME_PER_SECONDS/6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            kl.update();
        }
    }

    /**
     * Constructor
     * @param kl the key manager
     */
    public TH_Turn(KeyManager kl){
        this.kl = kl;
    }
}
