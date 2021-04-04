package Controller;

import View.UiObjects.UiObjectManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Class key manager
 */
public class MouseManager implements MouseListener, MouseMotionListener {
    /**
     * UiObject manager
     */
    private final UiObjectManager manager;

    /**
     * Constructor
     *
     * @param manager an UiObject manager
     */
    public MouseManager(UiObjectManager manager) {
        this.manager = manager;
    }

    /**
     * Called whenever the mouse move
     *
     * @param e a mouse event
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        if (manager != null) manager.onMouseMove(e);
    }

    /**
     * Called whenever the mouse click
     *
     * @param e a mouse event
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (manager != null) manager.onMouseClicked();
    }

    /**
     * Called whenever the mouse is pressed
     *
     * @param e a mouse event
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (manager != null) manager.onMousePressed();
    }

    /**
     * Called whenever the mouse is released
     *
     * @param e a mouse event
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        if (manager != null) manager.onMouseReleased();
    }

    /* NOT USED IN THE PROGRAM */

    /* Called whenever the mouse enter the window */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /* Called whenever the mouse exit the window */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    /* Called when the mouse is dragging */
    @Override
    public void mouseDragged(MouseEvent e) {
    }
}
