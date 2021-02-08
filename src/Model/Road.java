package Model;

import View.Gfx;

public class Road {
    private final boolean[] points;
    private int counter = 1;
    boolean b = false;
    public static final int SQUARE_SIZE = 60;

    public Road(){
        this.points = new boolean[(Gfx.WIDTH - Gfx.ROAD_FINAL_WIDTH)/2];
        for(int i = 0; i < points.length; i++){
            if(counter == SQUARE_SIZE) {
                b = !b;
                counter = 1;
            }
            points[i] = b;
            counter++;
        }
    }

    public void update(){
        if(points.length - 2 >= 0) System.arraycopy(points, 2, points, 0, points.length - 2);
        if(counter == SQUARE_SIZE){
            b = !b;
            counter = 1;
        }
        points[points.length - 1] = b;
        counter++;
    }

    public boolean[] getPoints() {
        return points;
    }
}
