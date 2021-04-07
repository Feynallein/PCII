package Model;

import Model.Road.Road;
import Model.Threads.TH_Game;
import View.Gfx.Assets;
import View.Scenes.Scene;

/**
 * The player
 */
public class Player {
    /**
     * Const : The player's horizontal speed
     */
    public final static int MOVE_SPEED = 35;

    /**
     * Const : The player's width
     */
    public final static int WIDTH = 470 / 3;

    /**
     * Const : The player's height
     */
    public final static int HEIGHT = 580 / 3;

    /**
     * Const : The player's y position
     */
    public final static int Y = Scene.HEIGHT - HEIGHT;

    /**
     * Const : The player's x position
     */
    public final static int X = (Scene.WIDTH - WIDTH) / 2;

    /**
     * Const : The player's max speed
     */
    public static final int MAX_SPEED = 300;

    /**
     * Const : the braking speed
     */
    private static final float BRAKING_SPEED = 0.75f;

    /**
     * Const : the decelerating speed
     */
    private static final float DECELERATING_SPEED = BRAKING_SPEED / 3;

    /**
     * Const : maximum number of lives
     */
    private static final int MAX_LIVES = 5;

    /**
     * Const : the maximum turning position (not used)
     */
    private static final int MAX_TURNING_POSITION = 50;

    /**
     * Which state (Braking, accelerating, free wheel)
     */
    private int state = 0;

    /**
     * Which frame of the animation
     */
    private int animation = 0;

    /**
     * A clock to calculate the animation
     */
    private int clock = 0;

    /**
     * The accumulated time, serves as a clock for the distance traveled
     */
    private int accumulatedTime = 0;

    /**
     * The timer
     */
    private int timer = Road.GATE_ADDING_TIME;

    /**
     * X offset of the player
     */
    private int offset;

    /**
     * The distance traveled, based on the speed
     */
    private int distanceTraveled;

    /**
     * The speed of the player
     */
    private float speed;

    /**
     * The turning position
     */
    private int turningPosition;

    /**
     * The actual number of lives
     */
    private int lives;

    /**
     * The coefficient of the linear function that calculate sleep of TH_Scrolling's speed
     */
    private static final float COEFFICIENT = (1000000 - 2000000) / (MAX_SPEED - 50f);

    /**
     * The origin ordinate of the linear function described above
     */
    private final static int ORDINATE = (int) (2000000 - COEFFICIENT * 50);

    /**
     * Constructor
     */
    public Player() {
        this.speed = 0;
        this.offset = 0;
        this.distanceTraveled = 0;
        this.turningPosition = 0;
        this.lives = MAX_LIVES;
    }

    /**
     * Move the player to the left
     */
    public void moveLeft() {
        if (offset < Road.INITIAL_WIDTH) offset += MOVE_SPEED;
        if (turningPosition > -MAX_TURNING_POSITION) turningPosition--;
    }

    /**
     * Move the player to the right
     */
    public void moveRight() {
        if (offset > -Road.INITIAL_WIDTH) offset -= MOVE_SPEED;
        if (turningPosition < MAX_TURNING_POSITION) turningPosition++;
    }

    /**
     * Accelerating
     */
    public void accelerate() {
        if (speed < MAX_SPEED) {
            if (speed < 75) speed += 0.7;
            else if (speed < 200) speed += 0.5;
            else if (speed < 275) speed += 0.3;
            else speed += 0.1;
        }
        state = 2;
        anim();
    }

    /**
     * Braking
     */
    public void brake() {
        if (speed >= BRAKING_SPEED) speed -= BRAKING_SPEED;
        else if (speed > 0) speed = 0;
        state = 3;
        anim();
    }

    /**
     * Decelerating (free wheel)
     */
    public void decelerate() {
        if (speed >= DECELERATING_SPEED) speed -= DECELERATING_SPEED;
        else if (speed > 0) speed = 0;
        state = 1;
        if (speed == 0) state = 0;
        anim();
    }

    /**
     * Losing a life
     */
    public void loseLife() {
        lives--;
    }

    /**
     * Calculate the animation frame
     */
    private void anim() {
        /* Divided by 1000000 convert in secs from nanoseconds */
        long setup = calculateSleep() / 1000000;
        clock++;
        if (clock > setup) {
            animation++;
            animation %= Assets.player[0].length;
            clock = 0;
        }
    }

    /**
     * Tell if the time is out
     *
     * @return ture if timed out
     */
    public boolean timedOut() {
        return timer <= 0;
    }

    /**
     * Calculate the sleep time of TH_Scrolling, based on speed
     *
     * @return the sleep time
     */
    public long calculateSleep() {
        return (long) (COEFFICIENT * speed + ORDINATE);
    }

    /**
     * Return to the center when not turning
     */
    public void notTurning() {
        if (turningPosition > 0) turningPosition--;
        else if (turningPosition < 0) turningPosition++;
    }

    /**
     * Add a calculated distance IN CENTIMETERS traveled since last time based on speed
     */
    public void addDistanceTraveled() {
        // Distance traveled
        // Divided by 3.6 to get m/s therefore the distance traveled is in meters
        // Clock to update this only every seconds, not as every tick (1 tick = 16 milliseconds)

        // Superior at 1000 millis (= 1 sec)
        if (accumulatedTime >= 1000) {
            distanceTraveled += Math.floor(speed / 3.6);
            accumulatedTime = 0;
            timer--;
        } else accumulatedTime += TH_Game.GAME_SPEED;
    }

    /* GETTER & SETTER */

    /**
     * Add a specified time to the timer
     */
    public void setTimer(int addedTime) {
        timer += addedTime;
    }

    /**
     * Half the speed
     */
    public void halfSpeed() {
        speed = speed / 2;
    }

    /**
     * Getter for the speed
     *
     * @return the speed
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Getter for the state
     *
     * @return the state of the player
     */
    public int getState() {
        return state;
    }

    /**
     * Getter for the animation's frame
     *
     * @return which frame of the animation it has to display
     */
    public int getAnimation() {
        return animation;
    }

    /**
     * Getter for the position
     *
     * @return the distance traveled
     */
    public int getDistanceTraveled() {
        return distanceTraveled;
    }

    /**
     * Getter for the timer
     *
     * @return the timer
     */
    public int getTimer() {
        return timer;
    }

    /**
     * Getter for the offset
     *
     * @return the offset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * Getter to the turning position
     *
     * @return the turning position
     */
    public int getTurningPosition() {
        return turningPosition;
    }

    /**
     * Getter to the lives' number
     *
     * @return the number of lives
     */
    public int getLives() {
        return lives;
    }
}
