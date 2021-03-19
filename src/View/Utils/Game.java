package View.Utils;

import Controller.KeyManager;
import Controller.MouseManager;
import Model.Moto;
import Model.Road.Road;
import Model.Threads.TH_Handler;
import Model.UiObjects.ObjectManager;
import View.Scenes.SceneManager;

import javax.swing.*;

public class Game {
    /**
     * The graphics
     */
    private final SceneManager sceneManager;

    /**
     * The player
     */
    private final Moto moto;

    /**
     * The game's key manager
     */
    private final KeyManager keyManager;

    /**
     * The road
     */
    private final Road road;

    /**
     * The display
     */
    private final JFrame display;

    private final MouseManager mouseManager;

    private final ObjectManager objectManager;

    private final Handler handler;

    public Game(){
        // Initialize the sprites
        Assets.init();

        // Initialize the handler
        this.handler = new Handler(this);

        // Defines game objects and utils

        this.moto = new Moto();
        this.road = new Road(handler);
        this.objectManager = new ObjectManager();
        this.sceneManager = new SceneManager(handler);
        this.keyManager = new KeyManager(handler);
        this.mouseManager = new MouseManager();
        this.mouseManager.setObjectManager(handler);

        // Creating the game frame
        this.display = new JFrame();
        this.display.setTitle("nom du jeu");
        this.display.setResizable(false);
        this.display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.display.add(sceneManager);
        this.display.addKeyListener(keyManager);
        this.display.addMouseListener(mouseManager);
        this.display.addMouseMotionListener(mouseManager);
        this.display.pack();
        this.display.setVisible(true);

        (new TH_Handler(handler)).start();
    }

    public SceneManager getSceneManager() {
        return sceneManager;
    }

    public Moto getPlayer() {
        return moto;
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public Road getRoad() {
        return road;
    }

    public JFrame getDisplay() {
        return display;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public ObjectManager getObjectManager() {
        return objectManager;
    }
}
