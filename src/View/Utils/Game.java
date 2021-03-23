package View.Utils;

import Controller.KeyManager;
import Controller.MouseManager;
import Model.Moto;
import Model.Road.Road;
import Model.Threads.TH_Handler;
import View.UiObjects.UiObjectManager;
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

    private final UiObjectManager uiObjectManager;

    private final Handler handler;

    public Game(){
        /* Initializing sprites */
        Assets.init();

        /* Initializing the handler */
        this.handler = new Handler(this);

        /* Declaration of games objects */
        this.moto = new Moto(); // The player
        this.road = new Road(handler); // The road
        this.uiObjectManager = new UiObjectManager(); // The object manager
        this.sceneManager = new SceneManager(handler); // The scene manager
        this.keyManager = new KeyManager(handler); // The key manager
        this.mouseManager = new MouseManager(uiObjectManager); // The mouse manager

        /* Creating the game's frame */
        this.display = new JFrame(); // Creating the display
        this.display.setTitle("Game's name"); // The name of the window
        this.display.setResizable(false); // Not resizable
        this.display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // What to do on close
        this.display.add(sceneManager); // Adding the scene
        this.display.addKeyListener(keyManager); // Adding the key manager
        this.display.addMouseListener(mouseManager); // Adding the mouse manager
        this.display.addMouseMotionListener(mouseManager); // Adding the mouse motion manager
        this.display.pack(); // Packing
        this.display.setVisible(true); // Displaying

        (new TH_Handler(handler)).start(); // Start the main thread
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

    public UiObjectManager getObjectManager() {
        return uiObjectManager;
    }
}
