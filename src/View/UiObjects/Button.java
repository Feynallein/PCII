package View.UiObjects;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Button extends UiObject {
    private final BufferedImage[] sprite;
    private final ClickListener clicker;

    public Button(int x, int y, int width, int height, BufferedImage[] sprite, ClickListener clicker) {
        super(x, y, width, height);
        this.sprite = sprite;
        this.clicker = clicker;
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage a;

        if (click) a = sprite[2];
        else if (hovering) a = sprite[1];
        else a = sprite[0];

        g.drawImage(a, x, y, width, height, null);
    }

    /* Clicker */
    @Override
    public void onClick() {
        try {
            clicker.onClick();
        } catch (Exception ignored) {
        }
    }

    @Override
    public int getY() {
        return super.getY();
    }
}
