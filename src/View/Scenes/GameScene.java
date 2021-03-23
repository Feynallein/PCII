package View.Scenes;

import Model.Moto;
import Model.Road.Curb;
import Model.Road.Road;
import Model.Road.Segment;
import View.Utils.Text;
import View.Gfx.Assets;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.util.ArrayList;

//TODO: idée changer le thème des couleurs : vert = hherbe mais faire plutot gris pour ville, jaune pour desert, ....
public class GameScene extends Scene {
    /**
     * Const : horizon's line position
     */
    public final static int HORIZON = Scene.HEIGHT / 2;

    private final Moto player;

    private final Road road;

    public GameScene(SceneManager sceneManager) {
        super(sceneManager);
        this.player = new Moto(); // The player
        this.road = new Road(player); // The road
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
        g.drawImage(Assets.player[player.getState()][player.getAnimation()], Moto.X, Moto.Y, Moto.WIDTH, Moto.HEIGHT, null);

        // Speed Counter
        drawSpeedCounter(g);



        /* TEMPORARY with those bad graphics : */

        // Distance traveled
        Text.drawString(g, Integer.toString(player.getDistanceTraveled()), 50, Scene.HEIGHT - 50, true, Color.black, Assets.font40);

        // Timer
        Text.drawString(g, Integer.toString(player.getTimer()), Scene.WIDTH / 2, 50, true, Color.WHITE, Assets.font40);
    }

    /**
     * Draw the road
     *
     * @param g the graphics
     */
    private void drawRoad(Graphics g) {
        /* Draw the road */

        // Not a for each because of concurrent modification exception
        for (int i = 0; i < road.getRoad().size(); i++) {
            // Get the curbs
            Curb c = road.getRoad().get(i);

            // Draw  the grass background
            g.setColor(new Color(86, 125, 70));
            g.drawRect(0, c.getY1(), Scene.WIDTH, -c.getHeight());

            // Draw the segments of the curb
            drawArray(c.getSeg(), g);
        }
        for (Curb c : road.getSpecialCurbs()) {
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
        //TODO: fix the warnings

        /* For each elements of the array (not an actual for each because of concurrent modification exception) */
        for (int i = 0; i < a.size(); i++) {
            /* Get his color */
            g.setColor(a.get(i).getColor());

            /* Print it */
            if (!a.isEmpty() && a.get(i).getY1() >= HORIZON) g.fillPolygon(a.get(i).getX(), a.get(i).getY(), 4);
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

        // Rotation
        AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(theta), Assets.needleAnchor[0], Assets.needleAnchor[1]);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        // Drawing
        g.drawImage(op.filter(Assets.needle, null), Scene.WIDTH - Assets.needle.getWidth(), Scene.HEIGHT - Assets.needle.getHeight(), null);
    }

    /**
     * Getter to the player
     *
     * @return the player
     */
    public Moto getPlayer() {
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
