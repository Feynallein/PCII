package Controller;

import Model.Moto;

import java.awt.event.KeyEvent;

/**
 * The key manager
 */
public class KeyManager implements java.awt.event.KeyListener {
    /**
     * Boolean array
     */
    private final boolean[] keys;

    /**
     * Boolean that correspond to left arrow's key
     */
    private boolean left = false;

    /**
     * Boolean that correspond to right arrow's key
     */
    private boolean right = false;

    /**
     * Boolean that correspond to up arrow's key
     */
    private boolean up = false;

    /**
     * Boolean that correspond to down arrow's key
     */
    private boolean down = false;

    /**
     * The player
     */
    private final Moto player;

    /**
     * Constructor
     *
     */
    public KeyManager(Moto player) {
        keys = new boolean[256];
        this.player = player;
    }

    /**
     * Update which keys are pressed based on the key listener
     */
    public void update() {
        // Doing actions if correspondent key is pressed
        if (up) player.accelerate();
        else if (down) player.brake();
        else player.decelerate();

        if (left && player.getSpeed() > 0) player.moveLeft();
        if (right && player.getSpeed() > 0) player.moveRight();

        // Updating if the keys are being pressed
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
    }

    /**
     * Key listener's key typed
     *
     * @param e a key event
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Key listener's key pressed
     *
     * @param e a key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
            return;
        keys[e.getKeyCode()] = true;
    }

    /**
     * Key listener's key released
     *
     * @param e a key event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
            return;
        keys[e.getKeyCode()] = false;
    }
}
