package View.Scenes;

import Controller.KeyManager;
import Controller.MouseManager;
import Model.Player;
import Model.Road.Road;
import View.UiObjects.UiObjectManager;
import View.Gfx.Display;

import javax.swing.*;
import java.awt.*;

/**
 * Class graphics
 */
public class SceneManager extends JPanel {
    private Scene currentScene;

    private GameScene gameScene;

    //private final MenuScene menuScene;

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
/*        this.menuScene = new MenuScene(uiObjectManager, this);
        setCurrentScene(menuScene);*/

        setPreferredSize(new Dimension(Scene.WIDTH, Scene.HEIGHT)); // Setting the size
        addMouseListener(mouseManager); // Adding the mouse manager
        addMouseMotionListener(mouseManager); // Adding the mouse motion manager

        /* Temporary */
        newGameScene();
        setCurrentScene(gameScene);
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

        // Temporary
        grabFocus();
    }

    public void newGameScene() {
        gameScene = new GameScene(this);
        keyManager = new KeyManager(gameScene.getPlayer()); // The key manager
        grabFocus();
        addKeyListener(keyManager); // Adding the key manager
    }

    public GameScene getGameScene() {
        return gameScene;
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

    public Player getPlayer() {
        return gameScene.getPlayer();
    }

    public Road getRoad() {
        return gameScene.getRoad();
    }

    public JFrame getDisplay() {
        return display.getDisplay();
    }
}
