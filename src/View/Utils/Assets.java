package View.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Load the assets
 */
public class Assets {
    /**
     * Const : player's width
     */
    public static final int player_img_width = 470;

    /**
     * Const : player's height
     */
    public static final int player_img_height = 580;

    /**
     * Player's assets
     */
    public static BufferedImage[][] player;

    /**
     * Background's assets
     */
    public static BufferedImage bg;

    public static BufferedImage gate;

    public static BufferedImage speed_counter;

    public static BufferedImage needle;

    public static final float[] needleAnchor = new float[]{154, 154};

    public static BufferedImage[] play;
    public static BufferedImage[] highScore;
    public static BufferedImage[] credits;
    public static BufferedImage[] setting;
    public static BufferedImage[] quit;
    public static BufferedImage menuBg;

    public static int size = 64;

    /**
     * The font at size 40
     */
    public static final Font font40 = FontLoader.loadFont("Resources/Others/times.ttf", 40);

    /**
     * Initialize all assets
     */
    public static void init() {
        // Load the player's sprite sheet
        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/Textures/player_sheets.png"));
        SpriteSheet playSheet = new SpriteSheet(ImageLoader.loadImage("/Buttons/Play.png"));
        SpriteSheet highScoreSheet = new SpriteSheet(ImageLoader.loadImage("/Buttons/Restart.png"));
        SpriteSheet creditsSheet = new SpriteSheet(ImageLoader.loadImage("/Buttons/Credits.png"));
        SpriteSheet settingSheet = new SpriteSheet(ImageLoader.loadImage("/Buttons/Settings.png"));
        SpriteSheet quitSheet = new SpriteSheet(ImageLoader.loadImage("/Buttons/Quit.png"));


        player = new BufferedImage[4][4];
        play = new BufferedImage[3];
        highScore = new BufferedImage[3];
        credits = new BufferedImage[3];
        setting = new BufferedImage[3];
        quit = new BufferedImage[3];

        // Crop each different sprites of the player
        for (int i = 0; i < player.length; i++) {
            for (int y = 0; y < player[0].length; y++) {
                player[i][y] = playerSheet.crop(i * player_img_width, y * player_img_height, player_img_width, player_img_height);
            }
        }

        for (int i = 0; i < 3; i++) {
            play[i] = playSheet.crop(i * size * 2, 0, 2 * size, size);
            highScore[i] = highScoreSheet.crop(i * (size / 2) * 7, 0, 7 * (size / 2), size);
            credits[i] = creditsSheet.crop(i * size * 3, 0, 3 * size, size);
            setting[i] = settingSheet.crop(i * size * 4, 0, 4 * size, size);
            quit[i] = quitSheet.crop(i * size * 2, 0, 2 * size, size);
        }

        // Load the background
        bg = ImageLoader.loadImage("/Textures/background.png");
        gate = ImageLoader.loadImage("/Textures/gate.png");
        speed_counter = ImageLoader.loadImage("/Textures/speed_counter.png");
        needle = ImageLoader.loadImage("/Textures/needle.png");
        menuBg = ImageLoader.loadImage("/Textures/menu_bg.jpg");
    }
}
