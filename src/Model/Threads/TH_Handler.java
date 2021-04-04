package Model.Threads;

import View.Scenes.GameScene;
import View.Scenes.MenuScene;
import View.Scenes.SceneManager;

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
        while (sceneManager.getCurrentScene() instanceof MenuScene) {
            sceneManager.repaint();
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
