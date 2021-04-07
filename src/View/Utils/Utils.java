package View.Utils;

import View.Scenes.HighScoreScene;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Utils class
 */
public class Utils {
    /**
     * Read the high score file
     *
     * @param path the path to the high score file
     * @return the array list of lines that are in the file
     */
    public static ArrayList<String> read(String path) {
        ArrayList<String> text = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null && text.size() < HighScoreScene.MAX_HIGH_SCORES) {
                text.add(line);
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    /**
     * Write a new high score in the high score file and sort the file from highest to lowest
     * It only keeps the 5 highest high scores
     *
     * @param path  the path the to high score file
     * @param name  the player's name
     * @param score the player's score
     */
    public static void writeHighScore(String path, String name, int score) {
        HashMap<String, Integer> toWrite = new HashMap<>();
        ArrayList<String> text = read(path);

        for (String s : text) {
            String[] parts = s.split(";");
            toWrite.put(parts[0], Integer.valueOf(parts[1]));
        }

        toWrite.put(name, score);
        int max = 0;
        String maxKey = "";

        int maxIteration = HighScoreScene.MAX_HIGH_SCORES;
        if (text.size() < HighScoreScene.MAX_HIGH_SCORES) maxIteration = text.size() + 1;

        try {
            FileWriter fileWriter = new FileWriter(path);
            for (int i = 0; i < maxIteration; i++) {
                for (Map.Entry<String, Integer> entry : toWrite.entrySet()) {
                    if (entry.getValue() > max) {
                        max = entry.getValue();
                        maxKey = entry.getKey();
                    }
                }
                fileWriter.write(maxKey + ";" + max + "\n");
                toWrite.remove(maxKey);
                max = 0;
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
