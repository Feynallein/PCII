package View;

import Model.Moto;
import Model.Road;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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
        // Draw the road
        drawRoad(g);

        // Draw the background
        drawBackground(g);

        // Draw the player
        g.drawImage(Assets.player[moto.getState()][moto.getAnimation()], Moto.X, Moto.Y, Moto.WIDTH, Moto.HEIGHT, null);
    }

    private void drawBackground(Graphics g){
        // If it has to draw two images
        if (Math.abs(moto.getOffset()) % Assets.bg.getWidth() != 0) {
            // If the image is over on the left
            if (moto.getOffset() < 0) {
                int offset = Math.abs(moto.getOffset()) % Assets.bg.getWidth();
                BufferedImage subImage = Assets.bg.getSubimage(0, 0, WIDTH, HORIZON);
                g.drawImage(subImage, offset, 0, WIDTH, HORIZON, null);
                subImage = Assets.bg.getSubimage(Assets.bg.getWidth() - offset, 0, offset, HORIZON);
                g.drawImage(subImage, 0, 0, offset, HORIZON, null);
            }

            // If the image is over on the right
            else if (moto.getOffset() + WIDTH > Assets.bg.getWidth()) {
                int offset = (Math.abs(moto.getOffset() + WIDTH) - Assets.bg.getWidth()) % Assets.bg.getWidth();
                BufferedImage subImage = Assets.bg.getSubimage(Assets.bg.getWidth() - WIDTH + offset, 0, WIDTH - offset, HORIZON);
                g.drawImage(subImage, 0, 0, WIDTH - offset, HORIZON, null);
                subImage = Assets.bg.getSubimage(0, 0, offset, HORIZON);
                g.drawImage(subImage, WIDTH - offset, 0, offset, HORIZON, null);
            }
        }

        // Default case (1 image)
        else g.drawImage(Assets.bg.getSubimage(moto.getOffset(), 0, WIDTH, HORIZON), 0, 0, WIDTH, HORIZON, null);
    }

    /**
     * Draw the road
     * @param g graphics
     */
    private void drawRoad(Graphics g){
        // To not have a concurrent modification exception
        ArrayList<Road.Segment> seg = road.getSegment();

        // For each segments of the road (not an actual for each because of concurrent modification exception)
        for(int i = 0; i < seg.size(); i++){
            // Get his color
            g.setColor(seg.get(i).getColor());

            // Print it if below horizon
            if(seg.get(i).getY1() >= HORIZON) g.fillPolygon(seg.get(i).getX(), seg.get(i).getY(),4);
        }
    }
}
