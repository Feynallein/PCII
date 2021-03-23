package View.Utils;

import Model.Threads.TH_Handler;
import View.Scenes.SceneManager;

import javax.swing.*;

public class Game {
    /**
     * The display
     */
    private final JFrame display;

    public Game(){
        /* Initialization of the sprites */
        Assets.init();

        /* Creation of the scene manager */
        SceneManager sceneManager = new SceneManager(this); // The scene manager

        /* Creating the game's frame */
        display = new JFrame(); // Creating the display
        display.setTitle("Name"); // The name of the window
        display.setResizable(false); // Not resizable
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // What to do on close
        display.add(sceneManager); // Adding the scene
        display.pack(); // Packing
        display.setVisible(true); // Displaying

        (new TH_Handler(sceneManager)).start(); // Start the main thread
    }

    public JFrame getDisplay() {
        return display;
    }
}
