package View.Utils;

import javax.swing.*;

public class Handler {
    private final Game game;

    public Handler(Game game){
        this.game = game;
    }

    public JFrame getDisplay(){
        return game.getDisplay();
    }
}
