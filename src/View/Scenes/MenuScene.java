package View.Scenes;

import Model.UiObjects.ObjectManager;

import java.awt.*;

public class MenuScene extends Scene {
    private final ObjectManager objectManager;

    public MenuScene(ObjectManager manager, SceneManager sceneManager){
        super(sceneManager);
        this.objectManager = manager;
        initialize();
    }

    private void initialize(){
        objectManager.addObject(new Model.UiObjects.Button(0f, 0f, 100, 100, () -> {
            sceneManager.setNewGameScene();
            sceneManager.setCurrentScene(sceneManager.getGameScene());
        }));
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, Scene.WIDTH, Scene.HEIGHT);
        objectManager.paint(g);
    }
}
