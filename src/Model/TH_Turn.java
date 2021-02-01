package Model;

import Controller.KeyManager;

public class TH_Turn extends Thread {
    private KeyManager kl;

    @Override
    public void run() {
        while(true) {
            try {
                sleep(TH_Game.GAME_SPEED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            kl.update();
        }
    }

    public TH_Turn(KeyManager kl){
        this.kl = kl;
    }
}
