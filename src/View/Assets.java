package View;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {
    public static BufferedImage[][] player;

    public static BufferedImage bg;
    public static BufferedImage bg2;

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

        Image temp = bg.getScaledInstance(bg.getWidth()*2, bg.getHeight()*2, Image.SCALE_DEFAULT);
        bg2 = new BufferedImage(temp.getWidth(null), temp.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bg2.createGraphics();
        bGr.drawImage(temp, 0, 0, null);
        bGr.dispose();
    }
}
