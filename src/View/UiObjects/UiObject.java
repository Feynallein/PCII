package View.UiObjects;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class UiObject {
    protected final int x, y;
    protected final int width, height;
    private final Rectangle bounds;
    public boolean hovering = false, click = false;

    public UiObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bounds = new Rectangle(x, y, width, height);
    }

    public abstract void paint(Graphics g);

    /* Clicker */

    public abstract void onClick();

    /* Mouse Manager */

    public void onMouseMove(MouseEvent e) {
        hovering = bounds.contains(e.getX(), e.getY());
    }

    public void onMouseClicked() {
        if (hovering) onClick();
    }

    public void onMousePressed() {
        if (hovering) click = true;
    }

    public void onMouseReleased() {
        if (hovering) click = false;
    }


    /* Getter */

    public boolean isHovering() {
        return hovering;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
