package View.Utils;

import View.Scenes.HighScoreScene;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static ArrayList<String> read(String path){
        ArrayList<String> text = new ArrayList<>();
        try {
            java.io.FileReader fileReader = new java.io.FileReader(path);
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

    public static void writeHighScore(String path, String name, int score) {
        HashMap<String, Integer> toWrite = new HashMap<>();
        ArrayList<String> text = read(path);

        for(String s : text){
            String[] parts = s.split(";");
            toWrite.put(parts[0], Integer.valueOf(parts[1]));
        }

        toWrite.put(name, score);
        int max = 0;
        String maxKey = "";

        int maxIteration = HighScoreScene.MAX_HIGH_SCORES;
        if(text.size() < HighScoreScene.MAX_HIGH_SCORES) maxIteration = text.size();

        try {
            FileWriter fileWriter = new FileWriter(path);
            for(int i = 0; i < maxIteration; i++){
                for(Map.Entry<String, Integer> entry : toWrite.entrySet()){
                    if(entry.getValue() > max){
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
