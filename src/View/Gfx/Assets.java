package View.Gfx;

import View.Utils.FontLoader;
import View.Utils.ImageLoader;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
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
     * Const : the height of a button
     */
    public static final int BUTTON_HEIGHT = 250;

    /**
     * Const : the scaling factor
     */
    public static final double SCALING = 0.2;

    /**
     * The charybdis font at size 40 and italic
     */
    public static final Font charybdisItalic40 = FontLoader.loadFont("Resources/Fonts/charybdis.ttf", 40, true);

    /**
     * The charybdis font at size 40
     */
    public static final Font charybdis40 = FontLoader.loadFont("Resources/Fonts/charybdis.ttf", 40, false);

    /**
     * Anchor of the needle's sprite
     */
    public static final float[] needleAnchor = new float[]{154, 154};

    /**
     * Anchor of the player's sprite
     */
    public static final float[] playerAnchor = new float[]{player_img_width / 2f, player_img_height};

    /**
     * Player's array of sprite
     */
    public static BufferedImage[][] player;

    /**
     * Background's sprite
     */
    public static BufferedImage bg;

    /**
     * Gate's sprite
     */
    public static BufferedImage gate;

    /**
     * First gate's (start) sprite
     */
    public static BufferedImage start;

    /**
     * Speed counter's sprite
     */
    public static BufferedImage speed_counter;

    /**
     * Needle's sprite
     */
    public static BufferedImage needle;

    /**
     * Menu background's sprite
     */
    public static BufferedImage menuBg;

    /**
     * Life's sprite
     */
    public static BufferedImage life;

    /**
     * Play button's array of sprites
     */
    public static BufferedImage[] play;

    /**
     * High score button's array of sprites
     */
    public static BufferedImage[] highScore;

    /**
     * Credits button's array of sprites
     */
    public static BufferedImage[] credits;

    /**
     * Quit button's array of sprites
     */
    public static BufferedImage[] quit;

    /**
     * Main menu button's array of sprites
     */
    public static BufferedImage[] mainMenu;

    /**
     * Obstacles' array of sprites
     */
    public static BufferedImage[] obstacles;

    /**
     * Initialize all assets
     */
    public static void init() {
        player = new BufferedImage[4][4];
        play = new BufferedImage[3];
        highScore = new BufferedImage[3];
        credits = new BufferedImage[3];
        quit = new BufferedImage[3];
        mainMenu = new  BufferedImage[3];
        obstacles = new BufferedImage[1];

        /* Loading sprites sheets */
        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/Textures/player_sheets.png"));
        SpriteSheet playSheet = new SpriteSheet(ImageLoader.loadImage("/Buttons/Play.png"));
        SpriteSheet highScoreSheet = new SpriteSheet(ImageLoader.loadImage("/Buttons/Highscore.png"));
        SpriteSheet creditsSheet = new SpriteSheet(ImageLoader.loadImage("/Buttons/Credits.png"));
        SpriteSheet quitSheet = new SpriteSheet(ImageLoader.loadImage("/Buttons/Quit.png"));
        SpriteSheet mainMenuSheet = new SpriteSheet(ImageLoader.loadImage("/Buttons/Main_menu.png"));
        SpriteSheet obstacleSheet = new SpriteSheet(ImageLoader.loadImage("/Textures/Obstacles_Sheet.png"));

        /* Crop each different sprites of the player */
        for (int i = 0; i < player.length; i++) {
            for (int y = 0; y < player[0].length; y++) {
                player[i][y] = playerSheet.crop(i * player_img_width, y * player_img_height, player_img_width, player_img_height);
            }
        }

        /* Cropping the buttons' sprites */
        for (int i = 0; i < 3; i++) {
            play[i] = playSheet.crop(i * 950, 0, 950, BUTTON_HEIGHT);
            highScore[i] = highScoreSheet.crop(i * 1920, 0, 1920, BUTTON_HEIGHT);
            credits[i] = creditsSheet.crop(i * 1550, 0, 1550, BUTTON_HEIGHT);
            quit[i] = quitSheet.crop(i * 910, 0, 910, BUTTON_HEIGHT);
            mainMenu[i] = mainMenuSheet.crop(i * 1930, 0, 1930, BUTTON_HEIGHT);
        }

        /* Rescaling the buttons */
        rescale(play);
        rescale(highScore);
        rescale(credits);
        rescale(quit);
        rescale(mainMenu);

        /* Cropping the different obstacles' sprites*/
        int obstacleWidth = obstacleSheet.getWidth();
        for (int i = 0; i < obstacles.length; i++) {
            obstacles[i] = obstacleSheet.crop(i * obstacleWidth, 0, obstacleWidth, (int) (obstacleWidth * 0.58));
        }

        /* Loading other sprites */
        bg = ImageLoader.loadImage("/Textures/background.png");
        gate = ImageLoader.loadImage("/Textures/gate.png");
        speed_counter = ImageLoader.loadImage("/Textures/speed_counter.png");
        needle = ImageLoader.loadImage("/Textures/needle.png");
        menuBg = ImageLoader.loadImage("/Textures/menu_bg.jpg");
        start = ImageLoader.loadImage("/Textures/Gate_start.png");
        life = ImageLoader.loadImage("/Textures/Lives.png");
    }

    /**
     * Rescale an array of buffered image
     *
     * @param b the array of buffered image to rescale
     */
    private static void rescale(BufferedImage[] b) {
        BufferedImage temp;
        AffineTransform at = new AffineTransform();
        at.scale(SCALING, SCALING); // Scaling
        AffineTransformOp op;

        for (int i = 0; i < 3; i++) {
            temp = new BufferedImage(b[i].getWidth(), b[i].getHeight(), BufferedImage.TYPE_INT_ARGB);
            op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
            b[i] = op.filter(b[i], temp); // Applying the scaling on each sprite of the array
        }
    }
}
