package View.Scenes;

import View.Gfx.Assets;
import View.UiObjects.Button;
import View.UiObjects.UiObjectManager;

import java.awt.*;

/**
 * Class menu scene
 */
public class MenuScene extends Scene {
    /**
     * The UiObject manager
     */
    private final UiObjectManager uiObjectManager;

    /**
     * Constructor
     *
     * @param manager      the UiObject manager
     * @param sceneManager the scene manager
     */
    public MenuScene(UiObjectManager manager, SceneManager sceneManager) {
        super(sceneManager);
        this.uiObjectManager = manager;

        /* Adding the buttons to the UiObjectManager */
        initialize();
    }

    private void initialize() {
        /* Play button */
        uiObjectManager.addObject(new Button((Scene.WIDTH - (int) (Assets.play[0].getWidth() * Assets.SCALING)) / 2, Scene.HEIGHT * 3 / 8,
                Assets.play[0].getWidth(), Assets.play[0].getHeight(), Assets.play, () -> {
            sceneManager.newGameScene();
            sceneManager.setCurrentScene(sceneManager.getGameScene());
        }));

        /* High score button */
        uiObjectManager.addObject(new Button((Scene.WIDTH - Assets.highScore[0].getWidth()) / 2, Scene.HEIGHT * 4 / 8,
                Assets.highScore[0].getWidth(), Assets.highScore[0].getHeight(), Assets.highScore, () -> {
            //TODO: high score
        }));

        /* Credits button */
        uiObjectManager.addObject(new Button((Scene.WIDTH - (int) (Assets.credits[0].getWidth() * Assets.SCALING)) / 2, Scene.HEIGHT * 6 / 8,
                Assets.credits[0].getWidth(), Assets.credits[0].getHeight(), Assets.credits, () -> {
            //TODO: credits
        }));

        /* Quit button */
        uiObjectManager.addObject(new Button((Scene.WIDTH - (int) (Assets.quit[0].getWidth() * Assets.SCALING)) / 2, Scene.HEIGHT * 7 / 8,
                Assets.quit[0].getWidth(), Assets.quit[0].getHeight(), Assets.quit, () -> {
            sceneManager.getDisplay().dispose();
            System.exit(0);
        }));
    }

    /**
     * Paint method
     *
     * @param g the graphics
     */
    @Override
    public void paint(Graphics g) {
        /* Drawing the back ground */
        g.drawImage(Assets.menuBg, 0, 0, Scene.WIDTH, Scene.HEIGHT, null);

        /* Drawing the buttons */
        uiObjectManager.paint(g);
    }
}
