package View.UiObjects;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Abstract class UiObject
 */
public abstract class UiObject {
    /**
     * the x position
     */
    protected final int x;

    /**
     * the y position
     */
    protected final int y;

    /**
     * the width
     */
    protected final int width;

    /**
     * the hight
     */
    protected final int height;

    /**
     * True if hovering on the object
     */
    protected boolean hovering;

    /**
     * True if clicking on the object
     */
    protected boolean click;

    /**
     * The hit box
     */
    private final Rectangle bounds;

    /**
     * Constructor to UiObject
     *
     * @param x      the x position
     * @param y      the y position
     * @param width  the width
     * @param height the height
     */
    public UiObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hovering = false;
        this.click = false;

        /* Creating the hit box*/
        this.bounds = new Rectangle(x, y, width, height);
    }

    /**
     * Painting method
     *
     * @param g the grpahics
     */
    public abstract void paint(Graphics g);

    /**
     * What to do on click
     */
    public abstract void onClick();

    /* MOUSE MANAGER */

    /**
     * Called whenever the mouse is moving
     *
     * @param e a mouse event
     */
    public void onMouseMove(MouseEvent e) {
        hovering = bounds.contains(e.getX(), e.getY());
    }

    /**
     * Called whenever the mouse is clicked
     */
    public void onMouseClicked() {
        if (hovering) onClick();
    }

    /**
     * Called whenever the mouse is pressed
     */
    public void onMousePressed() {
        if (hovering) click = true;
    }

    /**
     * Called whenever the mouse is released
     */
    public void onMouseReleased() {
        if (hovering) click = false;
    }


    /* GETTER */

    /**
     * Getter to the y
     *
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * Getter to the width
     *
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Getter to the height
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }
}
