package Model;

import java.awt.*;
public class Strip {
    private class Position {
        private final int x;
        private final int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
    private final Position hg;
    private final Position hd;
    private final Position bg;
    private final Position bd;
    private final Color color;

    public Strip(Position hg, Position hd, Position bg, Position bd, Color color) {
        this.hg = hg;
        this.hd = hd;
        this.bg = bg;
        this.bd = bd;
        this.color = color;
    }

    public int[] getX(){
        return new int[]{hg.getX(), hd.getX(), bg.getX(), bd.getX()};
    }

    public int[] getY(){
        return new int[]{hg.getY(), hd.getY(), bg.getY(), bd.getY()};
    }

    public Color getColor(){
        return color;
    }
}