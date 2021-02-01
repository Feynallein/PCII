package View;

import Model.Moto;

import javax.swing.*;
import java.awt.*;

public class Gfx extends JPanel {
    public final static int WIDTH = 1280;
    public final static int HEIGHT = 720;
    public final static int HORIZON = HEIGHT/2 - (HEIGHT/2 - HEIGHT/3)/2;

    private Moto moto;

    public Gfx(Moto moto) {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.moto = moto;
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawLine(0, HORIZON, WIDTH, HORIZON);
        g.drawLine(0, HEIGHT, WIDTH/2 - 50, HORIZON);
        g.drawLine(WIDTH, HEIGHT, WIDTH/2 + 50, HORIZON);
        g.drawRect(moto.getX(), Moto.Y, Moto.WIDTH, Moto.HEIGHT);
    }
}
