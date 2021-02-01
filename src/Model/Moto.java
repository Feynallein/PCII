package Model;

import View.Gfx;

public class Moto {
    public final static int MOVE_SPEED = 3;
    public final static int WIDTH = 50;
    public final static int HEIGHT = 50;
    public final static int Y = Gfx.HEIGHT - HEIGHT*2;

    private int x;
    private int speed;

    public Moto() {
        x = Gfx.WIDTH/2 - WIDTH/2;
    }

    public void moveLeft(){
        x -= MOVE_SPEED;
    }

    public void moveRight(){
        x += MOVE_SPEED;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
