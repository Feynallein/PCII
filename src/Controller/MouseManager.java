package Controller;

import View.UiObjects.UiObjectManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {
    private final UiObjectManager manager;

    public MouseManager(UiObjectManager manager) {
        this.manager = manager;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println("ok");
        if (manager != null) manager.onMouseMove(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (manager != null) manager.onMouseClicked();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (manager != null) manager.onMousePressed();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (manager != null) manager.onMouseReleased();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }
}
