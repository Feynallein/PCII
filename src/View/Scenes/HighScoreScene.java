package View.Scenes;

import View.Gfx.Assets;
import View.UiObjects.Button;
import View.UiObjects.UiObjectManager;

import java.awt.*;

public class HighScoreScene extends Scene{
    private final UiObjectManager uiObjectManager;

    public HighScoreScene(SceneManager sceneManager) {
        super(sceneManager);
        this.uiObjectManager = new UiObjectManager();
        sceneManager.setUiObjectManager(this.uiObjectManager);

        initialize();
    }

    private void initialize(){
        uiObjectManager.addObject(new Button((Scene.WIDTH - (int) (Assets.mainMenu[0].getWidth() * Assets.SCALING)) / 2, Scene.HEIGHT * 7 / 8,
                Assets.mainMenu[0].getWidth(), Assets.mainMenu[0].getHeight(), Assets.mainMenu, () -> {
            sceneManager.setCurrentScene(new MenuScene(sceneManager));
        }));
    }

    @Override
    public void paint(Graphics g) {
        /* Drawing the back ground */
        g.drawImage(Assets.menuBg, 0, 0, Scene.WIDTH, Scene.HEIGHT, null);

        /* Drawing the buttons */
        uiObjectManager.paint(g);
    }
}
