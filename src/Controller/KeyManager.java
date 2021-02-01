package Controller;

import Model.Moto;
import View.Gfx;

import java.awt.event.KeyEvent;

public class KeyManager implements java.awt.event.KeyListener {
    private Gfx gfx;
    private boolean[] keys;
    private boolean left = false, right = false;
    private Moto moto;

    public KeyManager(Gfx gfx, Moto moto) {
        this.gfx = gfx;
        keys = new boolean[256];
        this.moto = moto;
    }

    public void update(){
        if(left) moto.moveLeft();

        if(right) moto.moveRight();

        if(left || right){
            gfx.repaint();
        }

        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
            return;
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
            return;
        keys[e.getKeyCode()] = false;
    }
}
