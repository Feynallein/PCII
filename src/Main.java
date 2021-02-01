import Controller.KeyManager;
import Model.Moto;
import Model.TH_Turn;
import View.Gfx;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Moto moto = new Moto();
        Gfx gfx = new Gfx(moto);
        KeyManager kl = new KeyManager(gfx, moto);

        JFrame display = new JFrame();
        display.setTitle("nom du jeu");
        display.setResizable(false);
        display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display.add(gfx);
        display.pack();
        display.setVisible(true);

        display.addKeyListener(kl);

        (new TH_Turn(kl)).run();
    }
}
