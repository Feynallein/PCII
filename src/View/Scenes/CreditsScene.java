package View.Scenes;

import View.Gfx.Assets;
import View.UiObjects.Button;
import View.UiObjects.UiObjectManager;
import View.Utils.Text;

import java.awt.*;

public class CreditsScene extends Scene {
    private final UiObjectManager uiObjectManager;

    public CreditsScene(SceneManager sceneManager) {
        super(sceneManager);
        this.uiObjectManager = new UiObjectManager();
        sceneManager.setUiObjectManager(this.uiObjectManager);

        initialize();
    }

    private void initialize() {
        uiObjectManager.addObject(new Button((Scene.WIDTH - (int) (Assets.mainMenu[0].getWidth() * Assets.SCALING)) / 2, Scene.HEIGHT * 7 / 8,
                Assets.mainMenu[0].getWidth(), Assets.mainMenu[0].getHeight(), Assets.mainMenu, () -> sceneManager.setCurrentScene(new MenuScene(sceneManager))));
    }

    @Override
    public void paint(Graphics g) {
        /* Drawing the back ground */
        g.drawImage(Assets.menuBg, 0, 0, Scene.WIDTH, Scene.HEIGHT, null);

        Text.drawString(g, "A game by Martin BERTHIER & Quentin BERTRAND", WIDTH / 2, HEIGHT * 3 / 9, true, Color.lightGray, Assets.charybdis40);
        Text.drawString(g, "Font by Tepid Monkey Fonts", WIDTH / 2, HEIGHT * 4 / 9, true, Color.lightGray, Assets.charybdis40);
        Text.drawString(g, "Menu Background by iywbr (on Pixabay)", WIDTH / 2, HEIGHT * 5 / 9, true, Color.lightGray, Assets.charybdis40);

        /* Drawing the buttons */
        uiObjectManager.paint(g);
    }
}
