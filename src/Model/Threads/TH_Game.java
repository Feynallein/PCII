package Model.Threads;

import View.Scenes.HighScoreScene;
import View.Scenes.MenuScene;
import View.Scenes.SceneManager;
import View.Utils.ReadFile;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
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

    private boolean isHighScore;
    private boolean isInHighScore;

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
        highScore();

        String[] buttons = new String[]{"Restart", "Main Menu", "Quit"};
        String randomName = "Player" + ((new Random()).nextInt(100) + 1);
        JPanel panel = new JPanel();

        int row = 4;
        if(isHighScore) row++;

        panel.setLayout(new GridLayout(row, 1));

        /* Creating the message */
        if (sceneManager.getPlayer().getLives() == 0) panel.add(new JLabel("No more lives!"));
        else panel.add(new JLabel("Timed Out!"));

        panel.add(new JLabel("\nScore: " + sceneManager.getPlayer().getDistanceTraveled() + " meters"));
        if(isHighScore) panel.add(new JLabel("\nNew High Score!!!"));
        panel.add(new JLabel("Name:"));

        /* Creating the text field */
        JTextField textField = new JTextField(randomName);
        textField.setSelectionStart(0);
        textField.setSelectionEnd(randomName.length());
        panel.add(textField);

        int a = JOptionPane.showOptionDialog(sceneManager.getDisplay(), panel, "Game Over",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, null);

        if(isInHighScore) {
            try {
                FileWriter fileWriter = new FileWriter("Resources/HighScore", true);
                fileWriter.write(textField.getText() + ";" + sceneManager.getPlayer().getDistanceTraveled() + "\n");
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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

    private void highScore(){
        ArrayList<Integer> scores = new ArrayList<>();
        ArrayList<String> text = ReadFile.read("Resources/HighScore");

        for(String s: text){
            scores.add(Integer.valueOf(s.split(";")[1]));
        }

        isInHighScore = scores.size() < HighScoreScene.MAX_HIGH_SCORES || sceneManager.getPlayer().getDistanceTraveled() > scores.get(scores.size() - 1);
        isHighScore = scores.isEmpty() || sceneManager.getPlayer().getDistanceTraveled() > scores.get(0);
    }

    /**
     * Constructor of this thread, create the windows, set up every elements and start other threads
     */
    public TH_Game(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.turn = new TH_KeyManager(sceneManager.getKeyManager(), sceneManager.getPlayer());
        this.scroll = new TH_Scrolling(sceneManager.getRoad(), sceneManager.getPlayer());
        this.isHighScore = false;
        this.isInHighScore = false;
    }
}
