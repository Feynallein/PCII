package Model.UiObjects;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class Object {
    private final float x, y;
    private final int width, height;
    private final Rectangle bounds;
    private boolean hovering = false, click = false;

    public Object(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle((int) x, (int) y, width, height);
    }

    public abstract void update();

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
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
