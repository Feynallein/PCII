package View.Scenes;

import java.awt.*;

/**
 * Abstract class scene
 */
public abstract class Scene {
    /**
     * Const : JPanel's height
     */
    public final static int HEIGHT = 720;
    /**
     * Const : JPanel's width
     */
    public final static int WIDTH = 1280;

    /**
     * The scene manager
     */
    protected final SceneManager sceneManager;

    /**
     * Constructor
     *
     * @param sceneManager the scene manager
     */
    public Scene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    /**
     * Paint method
     *
     * @param g the graphics
     */
    public abstract void paint(Graphics g);
}
