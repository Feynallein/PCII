package View;

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

    /**
     * Initialize all assets
     */
    public static void init() {
        // Load the player's sprite sheet
        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/player_sheets.png"));

        player = new BufferedImage[4][4];

        // Crop each different sprites of the player
        for(int i = 0; i < player.length; i++) {
            for(int y = 0; y < player[0].length; y++){
                player[i][y] = playerSheet.crop(i*player_img_width, y*player_img_height, player_img_width, player_img_height);
            }
        }

        // Load the background
        bg = ImageLoader.loadImage("/background.jpg");
    }
}
