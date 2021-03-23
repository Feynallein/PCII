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
    public static final int GAME_SPEED = 10;

    /**
     * Player's movement
     */
    private final TH_Turn turn;

    /**
     * The road and objects scrolling
     */
    private final TH_Scrolling scroll;

    private final SceneManager sceneManager;

    /**
     * Repaint the entire screen
     */
    @Override
    public void run() {
        // Starting every threads
        turn.start();
        scroll.start();

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
        JOptionPane.showMessageDialog(sceneManager.getDisplay(), "Timed Out!\nDistance traveled : " + sceneManager.getPlayer().getDistanceTraveled() + " meters", "", JOptionPane.ERROR_MESSAGE);
        // Quitter
        System.exit(0);
    }

    /**
     * Constructor of this thread, create the windows, set up every elements and start other threads
     */
    public TH_Game(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.turn = new TH_Turn(sceneManager.getKeyManager(), sceneManager.getPlayer());
        this.scroll = new TH_Scrolling(sceneManager.getRoad(), sceneManager.getPlayer());
    }
}
