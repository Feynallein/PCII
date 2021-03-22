package Model.Threads;

import View.Scenes.GameScene;
import View.Scenes.MenuScene;
import View.Utils.Handler;

public class TH_Handler extends Thread {
    private final Handler handler;

    public void run() {
        // Displaying the Menu
        while(handler.getSceneManager().getCurrentScene() instanceof MenuScene) {
            handler.getObjectManager().update();
            handler.getSceneManager().repaint();
        }

        // Starting the game
        if (handler.getSceneManager().getCurrentScene() instanceof GameScene) {
            (new TH_Game(handler)).start();
        }
    }

    public TH_Handler(Handler handler) {
        this.handler = handler;
    }
}
