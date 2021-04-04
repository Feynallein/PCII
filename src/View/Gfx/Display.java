package View.Gfx;

import Model.Threads.TH_Handler;
import View.Scenes.SceneManager;

import javax.swing.*;

/**
 * Class creating the window
 */
public class Display extends JFrame {
    /**
     * Creating the window
     */
    public Display() {
        /* Assets initialization */
        Assets.init();

        /* Scene manager creation */
        SceneManager sceneManager = new SceneManager(this); // The scene manager

        /* Window creation */
        setTitle("Name"); // The name of the window
        setResizable(false); // Not resizable
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // What to do on close
        add(sceneManager); // Adding the scene
        pack(); // Packing
        setVisible(true); // Displaying
        (new TH_Handler(sceneManager)).start(); // Start the main thread
    }

    /* GETTER */

    /**
     * Getter to the display
     *
     * @return the display
     */
    public JFrame getDisplay() {
        return this;
    }
}
