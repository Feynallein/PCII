package View.UiObjects;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UiObjectManager {
    private final ArrayList<UiObject> uiObjects;

    public UiObjectManager() {
        uiObjects = new ArrayList<>();
    }

    public void addObject(UiObject o) {
        uiObjects.add(o);
    }

    public void clear() {
        uiObjects.clear();
    }

    //Todo: ca a rien a faire la mais j'arrive pas a le mettre dans menu scene...
    public void paint(Graphics g){
        for(UiObject o : uiObjects){
            o.paint(g);
        }
    }

    /* Mouse Manager */
    public void onMouseMove(MouseEvent e) {
        System.out.println( e.getX() + " " + e.getY());
        for (UiObject o : uiObjects) {
            o.onMouseMove(e);
        }
    }

    public void onMousePressed() {
        for (UiObject o : uiObjects) {
            o.onMousePressed();
        }
    }

    public void onMouseReleased() {
        for (UiObject o : uiObjects) {
            o.onMouseReleased();
        }
    }

    public void onMouseClicked() {
        for (UiObject o : uiObjects) {
            o.onMouseClicked();
        }
    }

    public ArrayList<UiObject> getObjects() {
        return uiObjects;
    }
}
