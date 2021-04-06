package View.Utils;

import View.Scenes.HighScoreScene;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {
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
}
