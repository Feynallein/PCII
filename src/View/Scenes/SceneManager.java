package View.Scenes;

import Controller.KeyManager;
import Controller.MouseManager;
import Model.Moto;
import Model.Road.Road;
import View.UiObjects.UiObjectManager;
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

    private final MouseManager mouseManager;

    private final UiObjectManager uiObjectManager;

    /**
     * The game's key manager
     */
    private final KeyManager keyManager;

    /**
     * Constructor
     */
    public SceneManager(Handler handler) {
        this.handler = handler;
        this.uiObjectManager = new UiObjectManager(); // The object manager
        this.keyManager = new KeyManager(handler); // The key manager
        this.mouseManager = new MouseManager(uiObjectManager); // The mouse manager

        setPreferredSize(new Dimension(Scene.WIDTH, Scene.HEIGHT)); // Setting the size
        addKeyListener(keyManager); // Adding the key manager
        addMouseListener(mouseManager); // Adding the mouse manager
        addMouseMotionListener(mouseManager); // Adding the mouse motion manager

        this.menuScene = new MenuScene(uiObjectManager, this);
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
        System.out.println(getMousePosition());
    }

    public void setNewGameScene() {
        gameScene = new GameScene(handler, this);
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

    public void setCurrentScene(Scene s) {
        this.currentScene = s;
    }
}
