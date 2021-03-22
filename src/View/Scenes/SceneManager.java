package View.Scenes;

import View.Utils.Handler;

import javax.swing.*;
import java.awt.*;

/**
 * Class graphics
 */
public class SceneManager extends JPanel {
    private Scene currentScene;

    private GameScene gameScene;

    private final MenuScene menuScene;

    private final Handler handler;

    /**
     * Constructor
     *
     */
    public SceneManager(Handler handler) {
        this.setPreferredSize(new Dimension(Scene.WIDTH, Scene.HEIGHT));
        this.handler = handler;
        this.menuScene = new MenuScene(handler.getObjectManager(), this);
        this.currentScene = this.menuScene;
    }

    /**
     * Paint the different elements
     *
     * @param g the graphics
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        currentScene.paint(g);
    }

    public void setNewGameScene(){
        gameScene = new GameScene(handler.getPlayer(), handler.getRoad(), this);
    }

    public GameScene getGameScene() {
        return gameScene;
    }

    public MenuScene getMenuScene() {
        return menuScene;
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(Scene s){
        this.currentScene = s;
    }
}
