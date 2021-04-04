package View.Scenes;

import Model.Player;
import Model.Road.Curb;
import Model.Road.Obstacle;
import Model.Road.Road;
import Model.Road.Segment;
import View.Gfx.Assets;
import View.Utils.Text;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.util.ArrayList;

/**
 * Class game scene
 */
public class GameScene extends Scene {
    /**
     * Const : horizon's line position
     */
    public final static int HORIZON = Scene.HEIGHT / 2;

    /**
     * The player
     */
    private final Player player;

    /**
     * The road
     */
    private final Road road;

    /**
     * Start instead of checkpoint for first gate
     */
    private boolean firstGate;

    /**
     * First curb of gate = start
     */
    private Curb firstCurbGate;

    /**
     * Constructor
     *
     * @param sceneManager the scene manager
     */
    public GameScene(SceneManager sceneManager) {
        super(sceneManager);
        this.player = new Player(); // The player
        this.road = new Road(player); // The road
        this.firstGate = true;
    }

    /**
     * Paint method
     *
     * @param g the graphics
     */
    @Override
    public void paint(Graphics g) {
        /* Draw the grass */
        g.setColor(new Color(69, 100, 56));
        g.fillRect(0, HORIZON, Scene.WIDTH, HORIZON);

        /* Draw the background */
        g.drawImage(Assets.bg, 0, 0, Scene.WIDTH, HORIZON, null);
        //drawBackground(g);

        /* Draw the road */
        drawRoad(g);

        /* Draw the player */
        drawPlayer(g);

        /* Draw the speed Counter */
        drawSpeedCounter(g);



        /* TEMPORARY with those bad graphics : */

        // Distance traveled
        Text.drawString(g, Integer.toString(player.getDistanceTraveled()), 50, Scene.HEIGHT - 50, true, Color.black, Assets.charybdis25);

        // Timer
        Text.drawString(g, Integer.toString(player.getTimer()), Scene.WIDTH / 2, 50, true, Color.WHITE, Assets.charybdis25);

        //Lives
        Text.drawString(g, Integer.toString(player.getLives()), 20, 20, true, Color.WHITE, Assets.charybdis25);
    }

    /**
     * Draw the player
     *
     * @param g the graphics
     */
    private void drawPlayer(Graphics g) {
        if (player.getTurningPosition() > 27452) {
            int theta = player.getTurningPosition();

            // Rotation
            AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(theta), 214f, 575f);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

            // Drawing
            g.drawImage(op.filter(Assets.player[player.getState()][player.getAnimation()], null), Player.X, Player.Y, Player.WIDTH, Player.HEIGHT, null);
        } else
            g.drawImage(Assets.player[player.getState()][player.getAnimation()], Player.X, Player.Y, Player.WIDTH, Player.HEIGHT, null);
    }

    /*
    private void drawBackground(Graphics g) {
        //TODO: changer, ca tourne quand la route tourne...

        // If it has to draw two images
        if (Math.abs(road.getLastXOffset()) % Assets.bg.getWidth() != 0) {

            // If the image is over on the left
            if (road.getLastXOffset() < 0) {
                // Calculating the offset
                int offset = Math.abs(road.getLastXOffset()) % Assets.bg.getWidth();

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
            else if (road.getLastXOffset() + Scene.WIDTH > Assets.bg.getWidth()) {
                // Calculating the offset
                int offset = (Math.abs(road.getLastXOffset() + Scene.WIDTH) - Assets.bg.getWidth()) % Assets.bg.getWidth();

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
            g.drawImage(Assets.bg.getSubimage(road.getLastXOffset(), 0, Scene.WIDTH, HORIZON), 0, 0, Scene.WIDTH, HORIZON, null);
    }*/

    /**
     * Draw the road
     *
     * @param g the graphics
     */
    private void drawRoad(Graphics g) {
        /* Draw the road */

        /* Not a for each because of concurrent modification exception */
        for (int i = 0; i < road.getRoad().size(); i++) {
            /* Get the curb */
            Curb c = road.getRoad().get(i);

            /* Draw  the grass background */
            g.setColor(new Color(86, 125, 70));
            g.drawRect(0, c.getY1(), Scene.WIDTH, -c.getHeight());

            /* Draw the segments of the curb */
            drawArray(c.getSeg(), g);
        }

        //todo: faire dans l'autre sens
        /* Draw the obstacles */
        for (Obstacle o : road.getObstacles()) {
            g.drawImage(o.getSprite(), o.getX(), o.getY(), o.getWidth(), o.getHeight(), null);
        }

        /* Draw the gates */
        for (Curb c : road.getSpecialCurbs()) {
            if(firstGate || c == firstCurbGate){
                firstCurbGate = c;
                g.drawImage(Assets.start, c.getMiddleX(), c.getMeanY() - c.getMiddleFullWidth() / 2, c.getMiddleFullWidth(), c.getMiddleFullWidth() / 2, null);
                firstGate = false;
            }
            else g.drawImage(Assets.gate, c.getMiddleX(), c.getMeanY() - c.getMiddleFullWidth() / 2, c.getMiddleFullWidth(), c.getMiddleFullWidth() / 2, null);
        }
    }

    /**
     * Draw an array of instanceof elements
     *
     * @param a an array of instanceof elements
     * @param g graphics
     */
    private void drawArray(ArrayList<Segment> a, Graphics g) {
        //TODO: fix the warnings
        /* For each elements of the array (not an actual for each because of concurrent modification exception) */
        if (!a.isEmpty()) {
            for (int i = 0; i < a.size(); i++) {
                System.out.println(i);
                if (a.get(i) != null) {
                    /* Get his color */
                    g.setColor(a.get(i).getColor());

                    /* Print it */
                    if (!a.isEmpty() && a.get(i).getY() != null && a.get(i).getY1() >= HORIZON && a.get(i).getX() != null)
                        g.fillPolygon(a.get(i).getX(), a.get(i).getY(), 4);
                }
            }
        }
    }

    /**
     * Draw the speed counter
     *
     * @param g the graphics
     */
    private void drawSpeedCounter(Graphics g) {
        /* The Counter */
        g.drawImage(Assets.speed_counter, Scene.WIDTH - Assets.speed_counter.getWidth(), Scene.HEIGHT - Assets.speed_counter.getHeight(), null);

        /* The Needle */

        // 180/225 -> if their is a 180 angle of the original needle, it correspond to 225 km/h
        // Linear straight line, coefficient = 180/225, the angle is calculate by multiplying it to the player's speed
        int theta = (int) (Math.ceil(player.getSpeed()) * (180f / 225f));

        /* Rotation */
        AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(theta), Assets.needleAnchor[0], Assets.needleAnchor[1]);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        /* Drawing */
        g.drawImage(op.filter(Assets.needle, null), Scene.WIDTH - Assets.needle.getWidth(), Scene.HEIGHT - Assets.needle.getHeight(), null);
    }

    /* GETTER */

    /**
     * Getter to the player
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Getter to the road
     *
     * @return the road
     */
    public Road getRoad() {
        return road;
    }
}
