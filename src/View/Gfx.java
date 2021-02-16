package View;

import Model.Moto;
import Model.Road.Elements;
import Model.Road.Road;
import View.Utils.Assets;

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
    public final static int HORIZON = HEIGHT / 2;

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
     *
     * @param moto the player
     */
    public Gfx(Moto moto, Road road) {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.moto = moto;
        this.road = road;
    }

    /**
     * Paint the different elements
     *
     * @param g the graphics
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // Draw the road
        drawRoad(g);

        // Draw the background
        drawBackground(g);

        // Draw the player
        g.drawImage(Assets.player[moto.getState()][moto.getAnimation()], Moto.X, Moto.Y, Moto.WIDTH, Moto.HEIGHT, null);

        /* TEMPORARY with those bad graphics : */

        // Distance traveled
        Text.drawString(g, Integer.toString(moto.getDistanceTraveled()), 50, HEIGHT - 50, true, Color.black, Assets.font40);

        // Speed
        Text.drawString(g, Integer.toString((int) moto.getSpeed()), WIDTH - 50, HEIGHT - 50, true, Color.black, Assets.font40);

        // Timer
        Text.drawString(g, Integer.toString(moto.getTimer()), WIDTH / 2, 50, true, Color.WHITE, Assets.font40); //todo the timer
    }

    /**
     * Draw the background
     *
     * @param g the graphics
     */
    private void drawBackground(Graphics g) {
        // If it has to draw two images
        if (Math.abs(moto.getOffset()) % Assets.bg.getWidth() != 0) {

            // If the image is over on the left
            if (moto.getOffset() < 0) {
                // Calculating the offset
                int offset = Math.abs(moto.getOffset()) % Assets.bg.getWidth();

                // Getting the sub-image
                BufferedImage subImage = Assets.bg.getSubimage(0, 0, WIDTH, HORIZON);

                // Printing the first image
                g.drawImage(subImage, offset, 0, WIDTH, HORIZON, null);

                // Getting the second sub-image
                subImage = Assets.bg.getSubimage(Assets.bg.getWidth() - offset, 0, offset, HORIZON);

                // Printing the second sub-image
                g.drawImage(subImage, 0, 0, offset, HORIZON, null);
            }

            // If the image is over on the right
            else if (moto.getOffset() + WIDTH > Assets.bg.getWidth()) {
                // Calculating the offset
                int offset = (Math.abs(moto.getOffset() + WIDTH) - Assets.bg.getWidth()) % Assets.bg.getWidth();

                // Getting the first sub-image
                BufferedImage subImage = Assets.bg.getSubimage(Assets.bg.getWidth() - WIDTH + offset, 0, WIDTH - offset, HORIZON);

                // Printing the first sub-image
                g.drawImage(subImage, 0, 0, WIDTH - offset, HORIZON, null);

                // Getting the second sub-image
                subImage = Assets.bg.getSubimage(0, 0, offset, HORIZON);

                // Printing the second sub-image
                g.drawImage(subImage, WIDTH - offset, 0, offset, HORIZON, null);
            }
        }

        // Default case (1 image)
        else g.drawImage(Assets.bg.getSubimage(moto.getOffset(), 0, WIDTH, HORIZON), 0, 0, WIDTH, HORIZON, null);
    }

    /**
     * Draw the road
     *
     * @param g the graphics
     */
    private void drawRoad(Graphics g) {
        // Draw every elements
        for (String s : new String[]{Road.SEG, Road.GATES, Road.SM}) drawArray(road.get(s), g);
    }

    /**
     * Draw an array of instanceof elements
     *
     * @param a and array of instanceof elements
     * @param g graphics
     */
    private void drawArray(ArrayList<Elements> a, Graphics g) {
        // For each elements of the array (not an actual for each because of concurrent modification exception)
        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < a.size(); i++) {
            // Get his color
            g.setColor(a.get(i).getColor());

            // Print it if below horizon
            if (a.get(i).getY1() >= HORIZON) g.fillPolygon(a.get(i).getX(), a.get(i).getY(), 4);
        }

    }
}
