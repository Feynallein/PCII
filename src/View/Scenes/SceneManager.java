package View.Scenes;

import Controller.KeyManager;
import Controller.MouseManager;
import Model.Moto;
import Model.Road.Road;
import View.UiObjects.UiObjectManager;
import View.Utils.Display;

import javax.swing.*;
import java.awt.*;

/**
 * Class graphics
 */
public class SceneManager extends JPanel {
    private Scene currentScene;

    private GameScene gameScene;

    private final MenuScene menuScene;

    private final Display display;

    private final MouseManager mouseManager;

    private final UiObjectManager uiObjectManager;

    /**
     * The game's key manager
     */
    private KeyManager keyManager;

    /**
     * Constructor
     */
    public SceneManager(Display display) {
        this.display = display;
        this.uiObjectManager = new UiObjectManager(); // The object manager
        this.mouseManager = new MouseManager(uiObjectManager); // The mouse manager
        this.menuScene = new MenuScene(uiObjectManager, this);
        this.currentScene = this.menuScene;

        setPreferredSize(new Dimension(Scene.WIDTH, Scene.HEIGHT)); // Setting the size
        addMouseListener(mouseManager); // Adding the mouse manager
        addMouseMotionListener(mouseManager); // Adding the mouse motion manager
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

    public void setNewGameScene() {
        gameScene = new GameScene(this);
        keyManager = new KeyManager(gameScene.getPlayer()); // The key manager
        grabFocus();
        addKeyListener(keyManager); // Adding the key manager
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

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public Moto getPlayer() {
        return gameScene.getPlayer();
    }

    public Road getRoad() {
        return gameScene.getRoad();
    }

    public JFrame getDisplay() {
        return display.getDisplay();
    }
}
