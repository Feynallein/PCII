package View.Gfx;

import Model.Threads.TH_Handler;
import View.Scenes.SceneManager;

import javax.swing.*;

public class Display extends JFrame {
    public Display(){
        /* Initialization of the sprites */
        Assets.init();

        /* Creation of the scene manager */
        SceneManager sceneManager = new SceneManager(this); // The scene manager

        /* Creating the game's frame */
        setTitle("Name"); // The name of the window
        setResizable(false); // Not resizable
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // What to do on close
        add(sceneManager); // Adding the scene
        pack(); // Packing
        setVisible(true); // Displaying
        (new TH_Handler(sceneManager)).start(); // Start the main thread
    }

    public JFrame getDisplay() {
        return this;
    }
}
