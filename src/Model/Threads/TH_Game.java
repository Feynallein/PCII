package Model.Threads;

import View.Utils.Handler;

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

    private final Handler handler;

    /**
     * Repaint the entire screen
     */
    @Override
    public void run() {
        // Starting every threads
        turn.start();
        scroll.start();

        while (!handler.getPlayer().timedOut()) {
            try {
                //noinspection BusyWait
                sleep(GAME_SPEED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.getSceneManager().repaint();
            handler.getPlayer().addDistanceTraveled();
        }

        // temp
        lose();
    }

    /**
     * What to do when losing
     */
    private void lose() {
        JOptionPane.showMessageDialog(handler.getDisplay(), "Timed Out!\nDistance traveled : " + handler.getPlayer().getDistanceTraveled() + " meters", "", JOptionPane.ERROR_MESSAGE);
        // Quitter
        System.exit(0);
    }

    /**
     * Constructor of this thread, create the windows, set up every elements and start other threads
     */
    public TH_Game(Handler handler) {
        this.handler = handler;
        this.turn = new TH_Turn(handler.getKeyManager(), handler.getPlayer());
        this.scroll = new TH_Scrolling(handler.getRoad(), handler.getPlayer());
    }
}
