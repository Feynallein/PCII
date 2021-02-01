package Model;

import Controller.KeyManager;
import View.Gfx;

import javax.swing.*;

public class TH_Game extends Thread {
    public static final int GAME_SPEED = 10;
    private Gfx gfx;
    private Moto moto;
    private KeyManager keyManager;
    private TH_Turn turn;

    @Override
    public void run() {
        while(true){
            try {
                sleep(GAME_SPEED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gfx.repaint();
        }
    }

    public TH_Game() {
        // Defines variables aka game objects and utils
        this.moto = new Moto();
        this.gfx = new Gfx(moto);
        this.keyManager = new KeyManager(moto);
        this.turn = new TH_Turn(keyManager);

        // Creating the game frame
        JFrame display = new JFrame();
        display.setTitle("nom du jeu");
        display.setResizable(false);
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.add(gfx);
        display.pack();
        display.setVisible(true);
        display.addKeyListener(keyManager);

        // Starting every threads
        turn.start();
    }
}
