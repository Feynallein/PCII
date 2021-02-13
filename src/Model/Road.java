package Model;

import View.Gfx;

import java.awt.*;

public class Road {

    private final Strip[] strip;
    private int counter = 1;
    boolean b = false;
    public static final int SQUARE_SIZE = 60;

    public Road(){
        this.strip = new Strip[((Gfx.WIDTH - Gfx.ROAD_FINAL_WIDTH)/2)/SQUARE_SIZE];
        Color c;
        for(int i = 0; i < strip.length; i++){
            if(b) c = new Color(45, 45, 45);
            else c = new Color(40, 40, 40);
            b = !b;
            strip[i] = new Strip(new Strip.Position(), new Strip.Position(),
                    new Strip.Position(), new Strip.Position(), c);
        }
    }

    public void update(){
        if(strip.length - 2 >= 0) System.arraycopy(strip, 2, strip, 0, strip.length - 2);
        if(counter == SQUARE_SIZE){
            b = !b;
            counter = 1;
        }
        strip[strip.length - 1] = b;
        counter++;
    }

    public Strip[] getStrip() {
        return strip;
    }
}
