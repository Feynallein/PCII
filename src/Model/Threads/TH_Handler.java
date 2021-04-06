package Model.Threads;

import View.Scenes.*;

import java.awt.*;

/**
 * Class Th_Handler
 */
public class TH_Handler extends Thread {
    /**
     * The scene manager
     */
    private final SceneManager sceneManager;

    /**
     * What the thread do
     */
    public void run() {
        // Displaying the Menu
        long start = System.nanoTime();
        while (sceneManager.getCurrentScene() instanceof MenuScene || sceneManager.getCurrentScene() instanceof CreditsScene || sceneManager.getCurrentScene() instanceof HighScoreScene) {
            //noinspection StatementWithEmptyBody
            while (System.nanoTime() - start < 100000000){

            }
            sceneManager.repaint();
            start = System.nanoTime();
        }

        // Starting the game
        if (sceneManager.getCurrentScene() instanceof GameScene) {
            (new TH_Game(sceneManager)).start();
        }
    }

    /**
     * Constructor
     *
     * @param sceneManager the scene manager
     */
    public TH_Handler(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
