package View;

import java.awt.image.BufferedImage;

/**
 * Sprite sheet handler
 */
class SpriteSheet {
    private final BufferedImage sheet;

    /**
     * Constructor
     *
     * @param sheet the sprite sheet
     */
    public SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet;
    }

    /**
     * Crop the part of the sprite sheet
     *
     * @param x      x of sub-image
     * @param y      y of sub-image
     * @param width  width of sub-image
     * @param height height of sub-image
     * @return defined sub-image
     */
    public BufferedImage crop(int x, int y, int width, int height) {
        return sheet.getSubimage(x, y, width, height);
    }
}
