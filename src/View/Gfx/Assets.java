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
    public static final float[] playerAnchor = new float[]{player_img_width/2f, player_img_height};

    public static BufferedImage[] play;
    public static BufferedImage[] highScore;
    public static BufferedImage[] credits;
    public static BufferedImage[] setting;
    public static BufferedImage[] quit;
    public static BufferedImage menuBg;
    public static BufferedImage[] obstacles;

    public static final int BUTTON_HEIGHT = 250;
    public static final double SCALING = 0.2;


    //temp
    public static final int size = 64;

    /**
     * The font at size 40
     */
    public static final Font font40 = FontLoader.loadFont("Resources/Others/times.ttf", 40);

    /**
     * Initialize all assets
     */
    public static void init() {
        player = new BufferedImage[4][4];
        play = new BufferedImage[3];
        highScore = new BufferedImage[3];
        credits = new BufferedImage[3];
        setting = new BufferedImage[3];
        quit = new BufferedImage[3];
        obstacles = new BufferedImage[1];

        /* Loading sprites sheets */
        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/Textures/player_sheets.png"));
        SpriteSheet playSheet = new SpriteSheet(ImageLoader.loadImage("/Buttons/Play.png"));
        SpriteSheet highScoreSheet = new SpriteSheet(ImageLoader.loadImage("/Buttons/Highscore.png"));
        SpriteSheet creditsSheet = new SpriteSheet(ImageLoader.loadImage("/Buttons/Credits.png"));
        SpriteSheet settingSheet = new SpriteSheet(ImageLoader.loadImage("/Buttons/Settings.png"));
        SpriteSheet quitSheet = new SpriteSheet(ImageLoader.loadImage("/Buttons/Quit.png"));
        SpriteSheet obstacleSheet = new SpriteSheet(ImageLoader.loadImage("/Textures/Rock.png"));

        /* Crop each different sprites of the player */
        for (int i = 0; i < player.length; i++) {
            for (int y = 0; y < player[0].length; y++) {
                player[i][y] = playerSheet.crop(i * player_img_width, y * player_img_height, player_img_width, player_img_height);
            }
        }

        /* Cropping the buttons' sprites */
        for (int i = 0; i < 3; i++) {
            play[i] = playSheet.crop(i * 950, 0, 950, BUTTON_HEIGHT);
            highScore[i] = highScoreSheet.crop(i * 1920, 0, 1920, size);
            credits[i] = creditsSheet.crop(i * 1550, 0, 1550, BUTTON_HEIGHT);
            setting[i] = settingSheet.crop(i * 1790, 0, 1790, BUTTON_HEIGHT);
            quit[i] = quitSheet.crop(i * 910, 0, 910, BUTTON_HEIGHT);
        }

        /* Rescaling the buttons */
        rescale(play);
        rescale(highScore);
        rescale(credits);
        rescale(setting);
        rescale(quit);

        obstacles[0] = obstacleSheet.crop(0, 0, 920, 500);

        /* Loading other things */
        bg = ImageLoader.loadImage("/Textures/background.png");
        gate = ImageLoader.loadImage("/Textures/gate.png");
        speed_counter = ImageLoader.loadImage("/Textures/speed_counter.png");
        needle = ImageLoader.loadImage("/Textures/needle.png");
        menuBg = ImageLoader.loadImage("/Textures/menu_bg.jpg");
    }

    /* Rescaling method */
    private static void rescale(BufferedImage[] b){
        BufferedImage temp;
        AffineTransform at = new AffineTransform();
        at.scale(SCALING, SCALING);
        AffineTransformOp op;

        for(int i = 0; i < 3; i++){
            temp = new BufferedImage(b[i].getWidth(), b[i].getHeight(), BufferedImage.TYPE_INT_ARGB);
            op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
            b[i] = op.filter(b[i], temp);
        }
    }
}