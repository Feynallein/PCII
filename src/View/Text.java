package View;

import java.awt.*;

public class Text {
    public static void drawString(Graphics g, String text, int xPos, int yPos, boolean center, Color c, Font f) {
        g.setColor(c);
        g.setFont(f);
        int x = xPos;
        int y = yPos;
        if (center) {
            FontMetrics fm = g.getFontMetrics(f);
            x = xPos - fm.stringWidth(text) / 2;
            y = yPos - fm.getHeight() / 2 + fm.getAscent();
        }
        g.drawString(text, x, y);
    }
}
