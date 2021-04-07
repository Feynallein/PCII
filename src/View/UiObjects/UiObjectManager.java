package View.UiObjects;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Class UiObject Manager
 */
public class UiObjectManager {
    /**
     * Array of UiObjects
     */
    private final ArrayList<UiObject> uiObjects = new ArrayList<>();

    /**
     * Constructor
     */
    public UiObjectManager() {
    }

    /**
     * Add an UiObject to the array
     *
     * @param o an UiObject
     */
    public void addObject(UiObject o) {
        uiObjects.add(o);
    }

    /**
     * Paint every UiObjects of the array
     *
     * @param g the graphics
     */
    public void paint(Graphics g) {
        for (UiObject o : uiObjects) {
            o.paint(g);
        }
    }

    /* MOUSE MANAGER */

    /**
     * Called whenever the mouse moves, call every UiObjects similar method
     *
     * @param e a mouse event
     */
    public void onMouseMove(MouseEvent e) {
        for (UiObject o : uiObjects) {
            o.onMouseMove(e);
        }
    }

    /**
     * Called whenever the mouse is pressed, call every UiObjects similar method
     */
    public void onMousePressed() {
        for (UiObject o : uiObjects) {
            o.onMousePressed();
        }
    }

    /**
     * Called whenever the mouse is released, call every UiObjects similar method
     */
    public void onMouseReleased() {
        for (UiObject o : uiObjects) {
            o.onMouseReleased();
        }
    }

    /**
     * Called whenever the mouse is clicked, call every UiObjects similar method
     */
    public void onMouseClicked() {
        for (UiObject o : uiObjects) {
            o.onMouseClicked();
        }
    }
}
