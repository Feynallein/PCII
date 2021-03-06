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

        /* Draw the timer */
        Text.drawString(g, "Time " + player.getTimer(), 10, 30, false, Color.BLACK, Assets.charybdisItalic40);

        /* Draw the distance traveled */
        Text.drawString(g, "Score " + player.getDistanceTraveled(), 10, 55, false, Color.BLACK, Assets.charybdisItalic40);

        /* Draw the lives */
        drawLives(g);
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

        /* Draw the obstacles */
        for (int i = road.getObstacles().size() - 1; i >= 0; i--) {
            Obstacle o = road.getObstacles().get(i);
            g.drawImage(o.getSprite(), o.getX(), o.getY(), o.getWidth(), o.getHeight(), null);
        }

        /* Draw the gates */
        for (Curb c : road.getSpecialCurbs()) {
            if (firstGate || c == firstCurbGate) {
                firstCurbGate = c;
                g.drawImage(Assets.start, c.getMiddleX(), c.getMeanY() - c.getMiddleFullWidth() / 2, c.getMiddleFullWidth(), c.getMiddleFullWidth() / 2, null);
                firstGate = false;
            } else
                g.drawImage(Assets.gate, c.getMiddleX(), c.getMeanY() - c.getMiddleFullWidth() / 2, c.getMiddleFullWidth(), c.getMiddleFullWidth() / 2, null);
        }
    }

    /**
     * Draw an array of instanceof elements
     *
     * @param a an array of instanceof elements
     * @param g graphics
     */
    private void drawArray(ArrayList<Segment> a, Graphics g) {
        //TODO: fix the exceptions
        /* For each elements of the array (not an actual for each because of concurrent modification exception) */
        if (!a.isEmpty()) {
            for (int i = 0; i < a.size(); i++) {
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

    /**
     * Draw the player's lives
     *
     * @param g the graphics
     */
    private void drawLives(Graphics g) {
        for (int i = player.getLives(); i > 0; i--) {
            g.drawImage(Assets.life, WIDTH - 115 - Assets.life.getWidth() * (player.getLives() - i),
                    HEIGHT - 10 - Assets.life.getHeight(), Assets.life.getWidth(), Assets.life.getHeight(), null);
        }
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
