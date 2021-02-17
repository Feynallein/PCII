package Model.Threads;

import Controller.KeyManager;
import Model.Moto;
import Model.Road.Road;
import View.Utils.Assets;
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
     * The display
     */
    private final JFrame display;

    /**
     * Repaint the entire screen
     */
    @Override
    public void run() {
        // Starting every threads
        turn.start();
        scroll.start();

        while (!moto.timedOut()) {
            try {
                //noinspection BusyWait
                sleep(GAME_SPEED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gfx.repaint();
            moto.addDistanceTraveled();
        }

        // temp
        lose();
    }

    /**
     * What to do when losing
     */
    private void lose() {
        JOptionPane.showMessageDialog(display, "Timed Out!\nDistance traveled : " + moto.getDistanceTraveled() + " meters", "", JOptionPane.ERROR_MESSAGE);
        // Quitter
        System.exit(0);
    }

    /**
     * Constructor of this thread, create the windows, set up every elements and start other threads
     */
    public TH_Game() {
        // Initialize the sprites
        Assets.init();

        // Defines game objects and utils
        this.moto = new Moto();
        this.road = new Road(moto);
        this.gfx = new Gfx(moto, road);
        this.keyManager = new KeyManager(moto);
        this.turn = new TH_Turn(keyManager, moto);
        this.scroll = new TH_Scrolling(road, moto);

        // Creating the game frame
        this.display = new JFrame();
        this.display.setTitle("nom du jeu");
        this.display.setResizable(false);
        this.display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.display.add(gfx);
        this.display.pack();
        this.display.setVisible(true);
        this.display.addKeyListener(keyManager);
    }
}
