package View.UiObjects;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Button class
 */
public class Button extends UiObject {
    /**
     * The different sprites of the button
     */
    private final BufferedImage[] sprite;

    /**
     * What to do on click
     */
    private final ClickListener clicker;

    /**
     * The constructor
     *
     * @param x       the x position
     * @param y       the y position
     * @param width   the width
     * @param height  the height
     * @param sprite  the array list of sprites
     * @param clicker the clicker method (what to do on click)
     */
    public Button(int x, int y, int width, int height, BufferedImage[] sprite, ClickListener clicker) {
        super(x, y, width, height);
        this.sprite = sprite;
        this.clicker = clicker;
    }

    /**
     * Painting the button
     *
     * @param g the graphics
     */
    @Override
    public void paint(Graphics g) {
        BufferedImage a;

        /* Setting the sprite to paint depending on hovering and clicking on the button */
        if (click) a = sprite[2];
        else if (hovering) a = sprite[1];
        else a = sprite[0];

        g.drawImage(a, x, y, width, height, null);
    }

    /**
     * What to do on click
     */
    @Override
    public void onClick() {
        try {
            clicker.onClick();
        } catch (Exception ignored) {
        } // Ignored
    }

    /* GETTER */

    /**
     * Getter to the y
     *
     * @return the y
     */
    @Override
    public int getY() {
        return super.getY();
    }
}
