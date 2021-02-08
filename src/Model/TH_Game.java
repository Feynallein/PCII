package Model;

import Controller.KeyManager;
import View.Assets;
import View.Gfx;

import javax.swing.*;

/**
 * Main thread
 */
public class TH_Game extends Thread {
    /**
     * Const : Every threads' speed
     */
    public static final int FRAME_PER_SECONDS = 60;

    /**
     * The graphics
     */
    private final Gfx gfx;

    /**
     * The player
     */
    private final Moto moto;

    /**
     * The game's key manager
     */
    private final KeyManager keyManager;

    /**
     * Player's movement
     */
    private final TH_Turn turn;

    /**
     * The road and objects scrolling
     */
    private final TH_Scrolling scroll;

    /**
     * The road
     */
    private final Road road;

    /**
     * Repaint the entire screen
     */
    @Override
    public void run() {
        double timePerUpdate = 1000000000f / FRAME_PER_SECONDS;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;

        while (true) {
            now = System.nanoTime();
            timer += now - lastTime;
            delta += timer / timePerUpdate;
            lastTime = now;

            if (delta >= 1) {
                gfx.repaint();
                delta--;
            }

            if (timer >= 1000000000) {
                timer = 0;
            }
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
        this.scroll = new TH_Scrolling(road, moto);


        // Creating the game frame
        JFrame display = new JFrame();
        display.setTitle("nom du jeu");
        display.setResizable(false);
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.add(gfx);
        display.pack();
        display.setVisible(true);
        display.addKeyListener(keyManager);

        // Init the sprites
        Assets.init();

        // Starting every threads
        turn.start();
        scroll.start();
    }
}
