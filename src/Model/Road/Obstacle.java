package Model.Road;

import View.Scenes.Scene;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Obstacle {
    private BufferedImage sprite;
    private int x;
    private int y;
    private int width;
    private int height;
    private Curb curb;

    public Obstacle(BufferedImage sprite, Curb c) {
        this.sprite = sprite;
        this.curb = c;
        this.width = c.getMiddleFullWidth()/2;
        this.height = this.width/2;
        this.x = (new Random()).nextInt((Scene.WIDTH/2 + c.getMiddleFullWidth()/2 - this.width) - (Scene.WIDTH/2 - c.getMiddleFullWidth()/2)) + Scene.WIDTH/2 - c.getMiddleFullWidth()/2;
        this.y = c.getMeanY();

    }

    public void update(){
        y = curb.getMeanY();
        x += curb.getXOffset();
        width = curb.getMiddleFullWidth()/2;
        height = width/2;
        //todo x update
    }

    public BufferedImage getSprite() {
        return sprite;
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
