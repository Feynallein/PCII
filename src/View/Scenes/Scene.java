package View.Scenes;

import java.awt.*;

public abstract class Scene {
    /**
     * Const : JPanel's height
     */
    public final static int HEIGHT = 720;
    /**
     * Const : JPanel's width
     */
    public final static int WIDTH = 1280;

    protected final SceneManager sceneManager;

    public Scene(SceneManager sceneManager){
        this.sceneManager = sceneManager;
    }

    public abstract void paint(Graphics g);
}
