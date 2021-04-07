package View.Scenes;

import View.Gfx.Assets;
import View.UiObjects.Button;
import View.UiObjects.UiObjectManager;
import View.Utils.Utils;
import View.Utils.Text;

import java.awt.*;
import java.util.ArrayList;

/**
 * High score scene class
 */
public class HighScoreScene extends Scene {
    /**
     * Const : max length of an high score
     */
    public static final int HIGH_SCORE_LENGTH = 32;

    /**
     * Const : max number of high score displayed and stored
     */
    public static final int MAX_HIGH_SCORES = 5;

    /**
     * The ui object manager
     */
    private final UiObjectManager uiObjectManager;

    /**
     * Content of high score file
     */
    private final ArrayList<String> text;

    /**
     * Constructor
     *
     * @param sceneManager the scene manager
     */
    public HighScoreScene(SceneManager sceneManager) {
        super(sceneManager);
        this.uiObjectManager = new UiObjectManager();
        sceneManager.setUiObjectManager(this.uiObjectManager);

        text = Utils.read("Resources/HighScore");

        /* Creating the high score output */
        for (int i = 0; i < text.size(); i++) {
            String[] parts = text.get(i).split(";");
            text.set(i, parts[0] + ".".repeat(Math.max(0, HIGH_SCORE_LENGTH - (parts[0].length() + parts[1].length()))) + parts[1]);
        }

        initialize();
    }

    /**
     * Initialize the ui object manager
     */
    private void initialize() {
        /* Main menu button */
        uiObjectManager.addObject(new Button((Scene.WIDTH - (int) (Assets.mainMenu[0].getWidth() * Assets.SCALING)) / 2, Scene.HEIGHT * 7 / 8,
                Assets.mainMenu[0].getWidth(), Assets.mainMenu[0].getHeight(), Assets.mainMenu, () -> sceneManager.setCurrentScene(new MenuScene(sceneManager))));
    }

    @Override
    public void paint(Graphics g) {
        /* Drawing the back ground */
        g.drawImage(Assets.menuBg, 0, 0, Scene.WIDTH, Scene.HEIGHT, null);

        /* Printing the high score */
        for (int i = 0; i < text.size(); i++) {
            Text.drawString(g, text.get(i), WIDTH / 2, HEIGHT * (i + 2) / 9, true, Color.lightGray, Assets.charybdis40);
        }

        /* Drawing the buttons */
        uiObjectManager.paint(g);
    }
}
