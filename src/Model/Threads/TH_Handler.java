package Model.Threads;

import View.Scenes.MenuScene;
import View.Utils.Handler;

public class TH_Handler extends Thread {
    private final Handler handler;

    public void run(){
        while(true){
            if(handler.getSceneManager().getCurrentScene() instanceof MenuScene){
                handler.getObjectManager().update();
            }
        }
    }

    public TH_Handler(Handler handler){
        this.handler = handler;
    }
}
