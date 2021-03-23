package View.Utils;

import Controller.KeyManager;
import Controller.MouseManager;
import Model.Moto;
import Model.Road.Road;
import View.UiObjects.UiObjectManager;
import View.Scenes.SceneManager;

import javax.swing.*;

public class Handler {
    private Game game;

    public Handler(Game game){
        this.game = game;
    }

    public Moto getPlayer(){
        return game.getPlayer();
    }

    public Road getRoad(){
        return game.getRoad();
    }

    public SceneManager getSceneManager(){
        return game.getSceneManager();
    }

    public JFrame getDisplay(){
        return game.getDisplay();
    }
}
