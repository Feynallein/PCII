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
    private final TH_KeyListener turn;

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

        while (!sceneManager.getPlayer().timedOut()) {
            try {
                //noinspection BusyWait
                sleep(GAME_SPEED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sceneManager.repaint();
            sceneManager.getPlayer().addDistanceTraveled();
        }

        // temp
        lose();
    }

    /**
     * What to do when losing
     */
    private void lose() {
        //todo: changer le msg et la conditoin d'arrête des threards
        JOptionPane.showMessageDialog(sceneManager.getDisplay(), "Timed Out!\nDistance traveled : " + sceneManager.getPlayer().getDistanceTraveled() + " meters", "", JOptionPane.ERROR_MESSAGE);

        // Quitter
        System.exit(0);
    }

    /**
     * Constructor of this thread, create the windows, set up every elements and start other threads
     */
    public TH_Game(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.turn = new TH_KeyListener(sceneManager.getKeyManager(), sceneManager.getPlayer());
        this.scroll = new TH_Scrolling(sceneManager.getRoad(), sceneManager.getPlayer());
    }
}
