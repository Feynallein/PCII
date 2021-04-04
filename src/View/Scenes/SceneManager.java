package View.Scenes;

import Controller.KeyManager;
import Controller.MouseManager;
import Model.Player;
import Model.Road.Road;
import View.Gfx.Display;
import View.UiObjects.UiObjectManager;

import javax.swing.*;
import java.awt.*;

/**
 * Class scene manager
 */
public class SceneManager extends JPanel {
    /**
     * The current scene
     */
    private Scene currentScene;

    /**
     * The game scene
     */
    private GameScene gameScene;

    /**
     * The menu scene
     */
    //private final MenuScene menuScene;

    /**
     * The display (aka the window)
     */
    private final Display display;

    /**
     * The mouse manager
     */
    private final MouseManager mouseManager;

    /**
     * The UiObject manager
     */
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

    /**
     * Creating a new game scene
     */
    public void newGameScene() {
        gameScene = new GameScene(this);
        keyManager = new KeyManager(gameScene.getPlayer()); // The key manager
        grabFocus();
        addKeyListener(keyManager); // Adding the key manager
    }
    /* GETTER & SETTER */

    /**
     * Change the current scene
     *
     * @param s a scene
     */
    public void setCurrentScene(Scene s) {
        this.currentScene = s;
    }

    /**
     * Getter to the game scene
     *
     * @return the game scene
     */
    public GameScene getGameScene() {
        return gameScene;
    }

    /**
     * Getter to the current scene
     *
     * @return the current scene
     */
    public Scene getCurrentScene() {
        return currentScene;
    }

    /**
     * Getter to the key manager
     *
     * @return the key manager
     */
    public KeyManager getKeyManager() {
        return keyManager;
    }

    /**
     * Getter to the player
     *
     * @return the player
     */
    public Player getPlayer() {
        return gameScene.getPlayer();
    }

    /**
     * Getter to the road
     *
     * @return the road
     */
    public Road getRoad() {
        return gameScene.getRoad();
    }

    /**
     * Getter to the display
     *
     * @return th display
     */
    public JFrame getDisplay() {
        return display.getDisplay();
    }
}
