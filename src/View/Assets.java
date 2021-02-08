package View;

import java.awt.image.BufferedImage;

public class Assets {
    public static BufferedImage[][] player;

    public static BufferedImage bg;

    public static final int player_img_width = 470;
    public static final int player_img_height = 580;

    public static void init() {
        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/player_sheets.png"));

        player = new BufferedImage[4][4];

        for(int i = 0; i < player.length; i++) {
            for(int y = 0; y < player[0].length; y++){
                player[i][y] = playerSheet.crop(i*player_img_width, y*player_img_height, player_img_width, player_img_height);
            }
        }

        bg = ImageLoader.loadImage("/background.jpg");
    }
}
