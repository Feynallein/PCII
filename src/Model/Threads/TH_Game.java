package Model.Threads;

import View.Scenes.SceneManager;

import javax.swing.*;

/**
 * Main thread
 */
public class TH_Game extends Thread {
    /**
     * Const : Every threads' speed
     */
    public static final int GAME_SPEED = 16; // ~60 FPS

    /**
     * Player's movement
     */
    private final TH_KeyManager turn;

    /**
     * The road and objects scrolling
     */
    private final TH_Scrolling scroll;

    /**
     * The scene manager
     */
    private final SceneManager sceneManager;

    /**
     * Repaint the entire screen
     */
    @Override
    public void run() {
        /* Starting other threads */
        turn.start(); // Key manager
        scroll.start(); // Scrolling

        while (!sceneManager.getPlayer().timedOut() && sceneManager.getPlayer().getLives() != 0) {
            try {
                //noinspection BusyWait
                sleep(GAME_SPEED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sceneManager.repaint(); // Repaint the scene
            sceneManager.getPlayer().addDistanceTraveled(); // Update the total distance traveled
        }

        lose();
    }

    /**
     * What to do when losing
     */
    private void lose() {
        String msg;
        if(sceneManager.getPlayer().getLives() == 0) msg = "No more lives!";
        else msg = "Timed Out!\nDistance traveled : " + sceneManager.getPlayer().getDistanceTraveled() + " meters";

        JOptionPane.showMessageDialog(sceneManager.getDisplay(), msg, "Game Over", JOptionPane.ERROR_MESSAGE);

        // Quitter
        System.exit(0);
    }

    /**
     * Constructor of this thread, create the windows, set up every elements and start other threads
     */
    public TH_Game(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.turn = new TH_KeyManager(sceneManager.getKeyManager(), sceneManager.getPlayer());
        this.scroll = new TH_Scrolling(sceneManager.getRoad(), sceneManager.getPlayer());
    }
}
