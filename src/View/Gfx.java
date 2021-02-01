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
    public final static int HORIZON = HEIGHT/2;

    /**
     * The player
     */
    private Moto moto;

    private Road road;

    public static final int ROAD_FINAL_WIDTH = 100;

    private int lastX = 0, lastY = HEIGHT;

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
        g.fillRect(0, 0, WIDTH, HORIZON);
        g.drawRect(moto.getX(), Moto.Y, Moto.WIDTH, Moto.HEIGHT);
    }

    private void drawRoad(Graphics g){
        boolean[] b = road.getPoints();
        for(int i = 0; i < b.length; i++){
            // Color selection
            if(b[i]) g.setColor(Color.GRAY);
            else g.setColor(Color.DARK_GRAY);

            // Offset calculation
            int x = i - HEIGHT/6;
            //float coeff = (float) - (2*HORIZON - HEIGHT*10/6)/(WIDTH + ROAD_FINAL_WIDTH);
            float coeff = (float) -24/59;

            int y = (int) (coeff * x) + HEIGHT*5/6;
            if(i < HEIGHT/6) {
                x = 0;
                y = HEIGHT - i;
            }
            g.fillPolygon(new int[]{lastX, WIDTH - lastX, x, WIDTH - x}, new int[]{lastY, lastY, y, y}, 4);
            lastX = x;
            lastY = y;
        }
    }
}
