package View;

import Model.Moto;
import Model.Road;

import javax.swing.*;
import java.awt.*;

/**
 * Class graphics
 */
public class Gfx extends JPanel {
    /**
     * Const : JPanel's width
     */
    public final static int WIDTH = 1280;

    /**
     * Const : JPanel's height
     */
    public final static int HEIGHT = 720;

    /**
     * Const : horizon's line position
     */
    public final static int HORIZON = HEIGHT/2 - (HEIGHT/2 - HEIGHT/3)/2;

    /**
     * The player
     */
    private Moto moto;

    private Road road;

    /**
     * Constructor
     * @param moto the player
     */
    public Gfx(Moto moto, Road road) {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.moto = moto;
        this.road = road;
    }

    /**
     * Paint method
     * @param g the graphics
     */
    @Override
    public void paint(Graphics g){
        super.paint(g);
        drawRoad(g);
        g.setColor(Color.BLACK);
        g.drawLine(0, HORIZON, WIDTH, HORIZON);
        g.drawLine(0, HEIGHT, WIDTH/2 - 50, HORIZON);
        g.drawLine(WIDTH, HEIGHT, WIDTH/2 + 50, HORIZON);
        g.drawRect(moto.getX(), Moto.Y, Moto.WIDTH, Moto.HEIGHT);
    }

    private void drawRoad(Graphics g){
        boolean[] b = road.getPoints();
        for(int i = 0; i < b.length; i++){
            if(b[i]) g.setColor(Color.GRAY);
            else g.setColor(Color.DARK_GRAY);
            g.drawLine(0, HEIGHT-i, WIDTH, HEIGHT-i);
            if(b[i]) {
                g.setColor(Color.white);
                g.drawLine(WIDTH / 2 - 10, HEIGHT - i, WIDTH / 2 + 10, HEIGHT - i);
            }
        }
    }
}
