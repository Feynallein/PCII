package Model.Threads;

import View.Scenes.GameScene;
import View.Scenes.MenuScene;
import View.Scenes.SceneManager;

public class TH_Handler extends Thread {
    private final SceneManager sceneManager;

    public void run() {
        // Displaying the Menu
        while(sceneManager.getCurrentScene() instanceof MenuScene) {
            try {
                //noinspection BusyWait
                sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sceneManager.repaint();
        }

        // Starting the game
        if (sceneManager.getCurrentScene() instanceof GameScene) {
            (new TH_Game(sceneManager)).start();
        }
    }

    public TH_Handler(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }
}
