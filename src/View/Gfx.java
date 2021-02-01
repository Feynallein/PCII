package View;

import Model.Moto;

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

    /**
     * Constructor
     * @param moto the player
     */
    public Gfx(Moto moto) {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.moto = moto;
    }

    /**
     * Paint method
     * @param g the graphics
     */
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawLine(0, HORIZON, WIDTH, HORIZON);
        g.drawLine(0, HEIGHT, WIDTH/2 - 50, HORIZON);
        g.drawLine(WIDTH, HEIGHT, WIDTH/2 + 50, HORIZON);
        g.drawRect(moto.getX(), Moto.Y, Moto.WIDTH, Moto.HEIGHT);
    }
}
