package View.Scenes;

import Model.Moto;
import Model.Road.Curbs;
import Model.Road.Elements;
import Model.Road.Road;
import View.Utils.Text;
import View.Utils.Assets;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GameScene extends Scene {
    /**
     * Const : horizon's line position
     */
    public final static int HORIZON = Scene.HEIGHT / 2;

    private final Moto moto;

    private final Road road;

    public GameScene(Moto moto, Road road, SceneManager sceneManager) {
        super(sceneManager);
        this.moto = moto;
        this.road = road;
    }

    @Override
    public void paint(Graphics g) {
        // Drawing the grass
        g.setColor(new Color(69, 100, 56));
        g.fillRect(0, HORIZON, Scene.WIDTH, HORIZON);

        // Draw the background
        //drawBackground(g);
        g.drawImage(Assets.bg, 0, 0, Scene.WIDTH, HORIZON, null);

        // Draw the road
        drawRoad(g);

        // Draw the player
        g.drawImage(Assets.player[moto.getState()][moto.getAnimation()], Moto.X, Moto.Y, Moto.WIDTH, Moto.HEIGHT, null);

        // Speed Counter
        drawSpeedCounter(g);



        /* TEMPORARY with those bad graphics : */

        // Distance traveled
        Text.drawString(g, Integer.toString(moto.getDistanceTraveled()), 50, Scene.HEIGHT - 50, true, Color.black, Assets.font40);

        // Timer
        Text.drawString(g, Integer.toString(moto.getTimer()), Scene.WIDTH / 2, 50, true, Color.WHITE, Assets.font40);
    }

    /**
     * Draw the background
     *
     * @param g the graphics
     */
    private void drawBackground(Graphics g) {
        //TODO: changer, ca tourne quand la route tourne...

        // If it has to draw two images
        if (Math.abs(moto.getOffset()) % Assets.bg.getWidth() != 0) {

            // If the image is over on the left
            if (moto.getOffset() < 0) {
                // Calculating the offset
                int offset = Math.abs(moto.getOffset()) % Assets.bg.getWidth();

                // Getting the sub-image
                BufferedImage subImage = Assets.bg.getSubimage(0, 0, Scene.WIDTH, HORIZON);

                // Printing the first image
                g.drawImage(subImage, offset, 0, Scene.WIDTH, HORIZON, null);

                // Getting the second sub-image
                subImage = Assets.bg.getSubimage(Assets.bg.getWidth() - offset, 0, offset, HORIZON);

                // Printing the second sub-image
                g.drawImage(subImage, 0, 0, offset, HORIZON, null);
            }

            // If the image is over on the right
            else if (moto.getOffset() + Scene.WIDTH > Assets.bg.getWidth()) {
                // Calculating the offset
                int offset = (Math.abs(moto.getOffset() + Scene.WIDTH) - Assets.bg.getWidth()) % Assets.bg.getWidth();

                // Getting the first sub-image
                BufferedImage subImage = Assets.bg.getSubimage(Assets.bg.getWidth() - Scene.WIDTH + offset, 0, Scene.WIDTH - offset, HORIZON);

                // Printing the first sub-image
                g.drawImage(subImage, 0, 0, Scene.WIDTH - offset, HORIZON, null);

                // Getting the second sub-image
                subImage = Assets.bg.getSubimage(0, 0, offset, HORIZON);

                // Printing the second sub-image
                g.drawImage(subImage, Scene.WIDTH - offset, 0, offset, HORIZON, null);
            }
        }

        // Default case (1 image)
        else
            g.drawImage(Assets.bg.getSubimage(moto.getOffset(), 0, Scene.WIDTH, HORIZON), 0, 0, Scene.WIDTH, HORIZON, null);
    }

    /**
     * Draw the road
     *
     * @param g the graphics
     */
    private void drawRoad(Graphics g) {
        /* Draw the road */

        // Not a for each because of concurrent modification exception
        for (int i = 0; i < road.get(Road.CURBS).size(); i++) {
            // Get the curbs
            Curbs c = (Curbs) road.get(Road.CURBS).get(i);

            // Draw  the grass background
            g.setColor(new Color(86, 125, 70));
            g.drawRect(0, c.getY1(), Scene.WIDTH, -c.getHeight());

            // Draw the segments of the curb
            drawArray(c.getSeg(), g);
        }

        /* Draw other elements */
        for (String s : new String[]{Road.GATES, Road.SM}) drawArray(road.get(s), g);
    }

    /**
     * Draw an array of instanceof elements
     *
     * @param a an array of instanceof elements
     * @param g graphics
     */
    private void drawArray(ArrayList<Elements> a, Graphics g) {
        //TODO: fix the warnings

        // For each elements of the array (not an actual for each because of concurrent modification exception)
        for (int i = 0; i < a.size(); i++) {
            // Get his color
            g.setColor(a.get(i).getColor());

            // Print it 
            if (!a.isEmpty() && a.get(i).getY1() >= HORIZON) g.fillPolygon(a.get(i).getX(), a.get(i).getY(), 4);

            // Draw the gate's sprite
/*            if(!a.isEmpty() && a.get(i) instanceof Gate){
                g.drawImage(Assets.gate, a.get(i).getMidLoneX(), a.get(i).getMidY() - a.get(i).getMidFullWidth()/2, a.get(i).getMidFullWidth(), a.get(i).getMidFullWidth()/2, null);
            }*/
        }
    }

    private void drawSpeedCounter(Graphics g) {
        /* The Counter */
        g.drawImage(Assets.speed_counter, Scene.WIDTH - Assets.speed_counter.getWidth(), Scene.HEIGHT - Assets.speed_counter.getHeight(), null);

        /* The Needle */

        // 180/225 -> if their is a 180 angle of the original needle, it correspond to 225 km/h
        // Linear straight line, coefficient = 180/225, the angle is calculate by multiplying it to the player's speed
        int theta = (int) (Math.ceil(moto.getSpeed()) * (180f / 225f));

        // Rotation
        AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(theta), Assets.needleAnchor[0], Assets.needleAnchor[1]);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        // Drawing
        g.drawImage(op.filter(Assets.needle, null), Scene.WIDTH - Assets.needle.getWidth(), Scene.HEIGHT - Assets.needle.getHeight(), null);
    }
}
