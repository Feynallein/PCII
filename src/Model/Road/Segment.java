package Model.Road;

import Model.Player;
import View.Scenes.GameScene;
import View.Scenes.Scene;

import java.awt.*;

/**
 * Segments of the road
 */
public class Segment {
    /**
     * Const : middle of the screen
     */
    public final static int center = Scene.WIDTH / 2;
    private final int y1;
    private final int y2;
    private final Color color;
    private final Player player;
    private final int xOffset;

    /**
     * The 4 widths
     */
    private final int[] widths;

    /**
     * Constructor
     *
     * @param y1    the first y (below)
     * @param color the color
     * @param player  the player
     */
    public Segment(int y1, Color color, Player player, int height, int xOffset) {
        this.y1 = y1;
        this.color = color;
        this.player = player;
        this.y2 = this.y1 - height;
        this.xOffset = xOffset;

        /* Calculating the widths of this segment */
        double[] coefficients = new double[]{-2d * (GameScene.HORIZON - getOriginIncreased()) / (Scene.WIDTH - Road.FINAL_WIDTH),
                -2d * (GameScene.HORIZON - getOriginDecreased()) / (Scene.WIDTH - Road.FINAL_WIDTH)};

        this.widths = new int[]{(int) (((this.y1 - getOriginDecreased()) / coefficients[1]) + Road.INITIAL_WIDTH),
                (int) (((this.y1 - getOriginIncreased()) / coefficients[0]) + Road.INITIAL_WIDTH),
                (int) (((this.y2 - getOriginIncreased()) / coefficients[0]) + Road.INITIAL_WIDTH),
                (int) (((this.y2 - getOriginDecreased()) / coefficients[1]) + Road.INITIAL_WIDTH)
        };
    }

    /**
     * Calculate the ordered at the origin with the player's offset
     *
     * @return the ordered at the origin
     */
    protected int getOriginIncreased() {
        return (int) (((float) (GameScene.HORIZON / 2 - GameScene.HORIZON) / Road.INITIAL_WIDTH) * player.getOffset() + Scene.HEIGHT);
    }

    /**
     * Calculate the ordered at the origin with the opposite of the player's offset
     *
     * @return the ordered at the origin
     */
    protected int getOriginDecreased() {
        return (int) (((float) (GameScene.HORIZON / 2 - GameScene.HORIZON) / Road.INITIAL_WIDTH) * -player.getOffset() + Scene.HEIGHT);
    }

    /**
     * Getter to color
     *
     * @return the color of this segment
     */
    public Color getColor() {
        return color;
    }

    /**
     * Getter to the x
     *
     * @return array list of x to get the bounds of this segment
     */
    public int[] getX() {
        return new int[]{center - widths[0] + xOffset, center + widths[1] + xOffset, center + widths[2] + xOffset, center - widths[3] + xOffset};
    }

    public int[] getWidths() {
        return widths;
    }

    /**
     * Getter to the y
     *
     * @return array list of y to get the bounds of this segment
     */
    public int[] getY() {
        return new int[]{y1, y1, y2, y2};
    }

    /**
     * Getter to y1
     *
     * @return the first y
     */
    public int getY1() {
        return y1;
    }
}
