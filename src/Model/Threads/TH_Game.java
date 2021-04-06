package Model.Threads;

import View.Scenes.HighScoreScene;
import View.Scenes.MenuScene;
import View.Scenes.SceneManager;
import View.Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Main thread
 */
public class TH_Game extends Thread {
    /**
     * Const : Every threads' speed
     */
    public static final int GAME_SPEED = 16; // ~60 FPS

    /**
     * Player's movement
     */
    private final TH_KeyManager turn;

    /**
     * The road and objects scrolling
     */
    private final TH_Scrolling scroll;

    /**
     * The scene manager
     */
    private final SceneManager sceneManager;

    /**
     * Repaint the entire screen
     */
    @Override
    public void run() {
        /* Starting other threads */
        turn.start(); // Key manager
        scroll.start(); // Scrolling

        while (!sceneManager.getPlayer().timedOut() && sceneManager.getPlayer().getLives() != 0) {
            try {
                //noinspection BusyWait
                sleep(GAME_SPEED);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sceneManager.repaint(); // Repaint the scene
            sceneManager.getPlayer().addDistanceTraveled(); // Update the total distance traveled
        }

        lose();
    }

    /**
     * What to do when losing
     */
    private void lose() {
        boolean highScore = highScore();

        String[] buttons = new String[]{"Restart", "Main Menu", "Quit"};
        String randomName = "Player" + ((new Random()).nextInt(100) + 1);
        JPanel panel = new JPanel();

        int row = 4;
        if(highScore) row++;

        panel.setLayout(new GridLayout(row, 1));

        /* Creating the message */
        if (sceneManager.getPlayer().getLives() == 0) panel.add(new JLabel("No more lives!"));
        else panel.add(new JLabel("Timed Out!"));

        panel.add(new JLabel("\nScore: " + sceneManager.getPlayer().getDistanceTraveled() + " meters"));
        if(highScore) panel.add(new JLabel("\nNew High Score!!!"));
        panel.add(new JLabel("Name:"));

        /* Creating the text field */
        JTextField textField = new JTextField(randomName);
        textField.setSelectionStart(0);
        textField.setSelectionEnd(randomName.length());
        panel.add(textField);

        int a = JOptionPane.showOptionDialog(sceneManager.getDisplay(), panel, "Game Over",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, null);

        Utils.writeHighScore("Resources/HighScore", textField.getText(), sceneManager.getPlayer().getDistanceTraveled());

        (new TH_Handler(sceneManager)).start();

        switch (a) {
            /* Restarting */
            case 0 -> {
                sceneManager.newGameScene();
                sceneManager.setCurrentScene(sceneManager.getGameScene());
            }

            /* Main Menu */
            case 1 -> sceneManager.setCurrentScene(new MenuScene(sceneManager));

            /* Quitter */
            case 2 -> System.exit(0);
        }
    }

    private boolean highScore(){
        ArrayList<Integer> scores = new ArrayList<>();
        ArrayList<String> text = Utils.read("Resources/HighScore");

        for(String s: text){
            scores.add(Integer.valueOf(s.split(";")[1]));
        }

        return scores.isEmpty() || sceneManager.getPlayer().getDistanceTraveled() > Collections.max(scores);
    }

    /**
     * Constructor of this thread, create the windows, set up every elements and start other threads
     */
    public TH_Game(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.turn = new TH_KeyManager(sceneManager.getKeyManager(), sceneManager.getPlayer());
        this.scroll = new TH_Scrolling(sceneManager.getRoad(), sceneManager.getPlayer());
    }
}
