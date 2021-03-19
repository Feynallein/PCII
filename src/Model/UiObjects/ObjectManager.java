package Model.UiObjects;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ObjectManager {
    private final ArrayList<Object> objects;

    public ObjectManager() {
        objects = new ArrayList<>();
    }

    public void addObject(Object o) {
        objects.add(o);
    }

    public void clear() {
        objects.clear();
    }

    public void update(){
        for(Object o : objects){
            o.update();
        }
    }

    //Todo: ca a rien a faire la mais j'arrive pas a le mettre dans menu scene...
    public void paint(Graphics g){
        for(Object o : objects){
            g.setColor(Color.green);
            g.fillRect(o.getX(), o.getY(), o.getWidth(), o.getHeight());
        }
    }

    /* Mouse Manager */
    public void onMouseMove(MouseEvent e) {
        for (Object o : objects) {
            o.onMouseMove(e);
        }
    }

    public void onMousePressed() {
        for (Object o : objects) {
            o.onMousePressed();
        }
    }

    public void onMouseReleased() {
        for (Object o : objects) {
            o.onMouseReleased();
        }
    }

    public void onMouseClicked() {
        for (Object o : objects) {
            o.onMouseClicked();
        }
    }

    public ArrayList<Object> getObjects() {
        return objects;
    }
}
