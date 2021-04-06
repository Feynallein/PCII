package View.Scenes;

import View.Gfx.Assets;
import View.UiObjects.Button;
import View.UiObjects.UiObjectManager;
import View.Utils.Text;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HighScoreScene extends Scene {
    public static final int HIGH_SCORE_LENGTH = 32;
    public static final int MAX_HIGH_SCORES = 5;
    private final UiObjectManager uiObjectManager;

    public HighScoreScene(SceneManager sceneManager) {
        super(sceneManager);
        this.uiObjectManager = new UiObjectManager();
        sceneManager.setUiObjectManager(this.uiObjectManager);

        initialize();
    }

    private void initialize() {
        uiObjectManager.addObject(new Button((Scene.WIDTH - (int) (Assets.mainMenu[0].getWidth() * Assets.SCALING)) / 2, Scene.HEIGHT * 7 / 8,
                Assets.mainMenu[0].getWidth(), Assets.mainMenu[0].getHeight(), Assets.mainMenu, () -> {
            sceneManager.setCurrentScene(new MenuScene(sceneManager));
        }));
    }

    @Override
    public void paint(Graphics g) {
        /* Drawing the back ground */
        g.drawImage(Assets.menuBg, 0, 0, Scene.WIDTH, Scene.HEIGHT, null);

        ArrayList<String> text = new ArrayList<>();

        /* Reading the high score file */
        try {
            File file = new File("Resources/HighScore.fal");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null && text.size() < MAX_HIGH_SCORES) {
                text.add(line);
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* Printing the high scores */
        for (int i = 0; i < text.size(); i++) {
            Text.drawString(g, text.get(i), WIDTH / 2, HEIGHT * (i + 2) / 9, true, Color.lightGray, Assets.charybdis40);
        }

        /* Drawing the buttons */
        uiObjectManager.paint(g);
    }
}
