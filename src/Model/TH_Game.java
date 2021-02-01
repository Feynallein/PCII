package Model;

import Controller.KeyManager;
import View.Gfx;

import javax.swing.*;

/**
 * Main thread
 */
public class TH_Game extends Thread {
    /**
     * Const : Every threads' speed
     */
    public static final int GAME_SPEED = 10;

    /**
     * The graphics
     */
    private Gfx gfx;

    /**
     * The player
     */
    private Moto moto;

    /**
     * The game's key manager
     */
    private KeyManager keyManager;

    /**
     * Player's movement
     */
    private TH_Turn turn;

    private TH_Scrolling scroll;

    private Road road;

    /**
     * Repaint the entire screen
     */
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

    /**
     * Constructor of this thread, create the windows, set up every elements and start other threads
     */
    public TH_Game() {
        // Defines game objects and utils
        this.moto = new Moto();
        this.road = new Road();
        this.gfx = new Gfx(moto, road);
        this.keyManager = new KeyManager(moto);
        this.turn = new TH_Turn(keyManager);
        this.scroll = new TH_Scrolling(road);


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
        scroll.start();
    }
}
