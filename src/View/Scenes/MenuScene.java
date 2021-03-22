package View.Scenes;

import Model.UiObjects.UiObjectManager;

import java.awt.*;

public class MenuScene extends Scene {
    private final UiObjectManager uiObjectManager;

    public MenuScene(UiObjectManager manager, SceneManager sceneManager){
        super(sceneManager);
        this.uiObjectManager = manager;
        initialize();
    }

    private void initialize(){
        uiObjectManager.addObject(new Model.UiObjects.Button(0f, 0f, 100, 100, () -> {
            sceneManager.setNewGameScene();
            sceneManager.setCurrentScene(sceneManager.getGameScene());
        }));
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, Scene.WIDTH, Scene.HEIGHT);
        uiObjectManager.paint(g);
    }
}
