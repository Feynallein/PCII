package Model.Threads;

import View.Scenes.GameScene;
import View.Scenes.MenuScene;
import View.Utils.Handler;

public class TH_Handler extends Thread {
    private final Handler handler;

    public void run() {
        // Displaying the Menu
        while(handler.getSceneManager().getCurrentScene() instanceof MenuScene) {
            try {
                //noinspection BusyWait
                sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
