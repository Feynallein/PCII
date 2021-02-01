package Model;

import Controller.KeyManager;

import java.util.concurrent.TimeUnit;

public class TH_Turn implements Runnable {
    private KeyManager kl;

    @Override
    public void run() {
        while(true){
            try {
                TimeUnit.MILLISECONDS.sleep(10);
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
