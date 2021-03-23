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

    private Moto player;

    private Road road;

    /**
     * The game's key manager
     */
    private final KeyManager keyManager;

    /**
     * Constructor
     */
    public SceneManager(Display display) {
        this.display = display;
        this.uiObjectManager = new UiObjectManager(); // The object manager
        this.keyManager = new KeyManager(player); // The key manager
        this.mouseManager = new MouseManager(uiObjectManager); // The mouse manager
        this.menuScene = new MenuScene(uiObjectManager, this);
        this.currentScene = this.menuScene;

        setPreferredSize(new Dimension(Scene.WIDTH, Scene.HEIGHT)); // Setting the size
        addKeyListener(keyManager); // Adding the key manager
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
        player = new Moto(); // The player
        road = new Road(player); // The road
        gameScene = new GameScene(this);
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

    public KeyManager getKeyManager(){
        return keyManager;
    }

    public Moto getPlayer(){
        return player;
    }

    public Road getRoad(){
        return road;
    }

    public JFrame getDisplay(){
        return display.getDisplay();
    }
}
