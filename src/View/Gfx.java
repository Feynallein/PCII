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
    private final Moto moto;

    /**
     * The road
     */
    private final Road road;

    /**
     * The road's width at the horizon
     */
    public static final int ROAD_FINAL_WIDTH = 50;

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
        drawBackground(g);
        drawRoad(g);
        g.drawImage(Assets.player[moto.getState()][moto.getAnimation()], Moto.X, Moto.Y, Moto.WIDTH, Moto.HEIGHT, null);
    }

    private void drawBackground(Graphics g){
        // If the image is over on the left
        if(moto.getOffset() < 0){
            int offset = Math.abs(moto.getOffset()) % Assets.bg.getWidth();
            g.drawImage(Assets.bg.getSubimage(0, 0, WIDTH, HORIZON), offset, 0, WIDTH, HORIZON, null);
            g.drawImage(Assets.bg.getSubimage(Assets.bg.getWidth() - offset, 0, offset, HORIZON), 0, 0, offset, HORIZON, null);
        }

        // If the image is over on the right
        else if(moto.getOffset() + WIDTH > Assets.bg.getWidth()){
            int offset = Math.abs(((moto.getOffset() + WIDTH) - Assets.bg.getWidth())) % (Assets.bg.getWidth() - WIDTH);
            g.drawImage(Assets.bg.getSubimage(Assets.bg.getWidth() - (WIDTH - offset), 0, WIDTH - offset, HORIZON), 0, 0, WIDTH - offset, HORIZON, null);
            g.drawImage(Assets.bg.getSubimage(0, 0, offset, HORIZON), WIDTH - offset, 0, offset, HORIZON, null);
        }

        // Default case
        else g.drawImage(Assets.bg.getSubimage(moto.getOffset(), 0, WIDTH, HORIZON), 0, 0, WIDTH, HORIZON, null);
    }

    private void drawRoad(Graphics g){
        boolean[] b = road.getPoints();
        for(int x = 0; x < b.length; x++){
            // Color selection
            if(b[x]) g.setColor(new Color(45, 45, 45));
            else g.setColor(new Color(40, 40, 40));

            // y calculation
            double coeff = 2d*(HORIZON - HEIGHT)/(WIDTH - ROAD_FINAL_WIDTH);
            int y = (int) (coeff * x + HEIGHT);
            int offset = -(moto.getOffset() - (Assets.bg.getWidth() - WIDTH)/2);
            g.drawLine(offset + x, y, offset + (WIDTH - x), y);
        }
    }
}
