package View.Scenes;

import View.UiObjects.UiObjectManager;
import View.Gfx.Assets;
import View.UiObjects.Button;

import java.awt.*;

public class MenuScene extends Scene {
    private final UiObjectManager uiObjectManager;

    public MenuScene(UiObjectManager manager, SceneManager sceneManager){
        super(sceneManager);
        this.uiObjectManager = manager;
        initialize();
    }

    private void initialize(){
        uiObjectManager.addObject(new Button((Scene.WIDTH - Assets.play[0].getWidth())/2, Scene.HEIGHT*3/8,
                Assets.play[0].getWidth(), Assets.play[0].getHeight(), Assets.play, () -> {
            sceneManager.newGameScene();
            sceneManager.setCurrentScene(sceneManager.getGameScene());
        }));
        uiObjectManager.addObject(new Button((Scene.WIDTH - Assets.highScore[0].getWidth())/2, Scene.HEIGHT*4/8,
                Assets.highScore[0].getWidth(), Assets.highScore[0].getHeight(), Assets.highScore, () -> {
            //TODO: high score
        }));
        uiObjectManager.addObject(new Button((Scene.WIDTH - Assets.setting[0].getWidth())/2, Scene.HEIGHT*5/8,
                Assets.setting[0].getWidth(), Assets.setting[0].getHeight(), Assets.setting, () -> {
            //TODO: settings
        }));
        uiObjectManager.addObject(new Button((Scene.WIDTH - Assets.credits[0].getWidth())/2, Scene.HEIGHT*6/8,
                Assets.credits[0].getWidth(), Assets.credits[0].getHeight(), Assets.credits, () -> {
            //TODO: credits
        }));
        uiObjectManager.addObject(new Button((Scene.WIDTH - Assets.quit[0].getWidth())/2, Scene.HEIGHT*7/8,
                Assets.quit[0].getWidth(), Assets.quit[0].getHeight(), Assets.quit, () -> {
            sceneManager.getDisplay().dispose();
            System.exit(0);
        }));
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(Assets.menuBg, 0, 0, Scene.WIDTH, Scene.HEIGHT, null);
        uiObjectManager.paint(g);
    }
}
