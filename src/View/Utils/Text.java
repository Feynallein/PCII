package View.Utils;

import java.awt.*;

/**
 * Print texts on the screen
 */
public class Text {
    /**
     * Print the specified text with the specified position, color and font
     *
     * @param g      the graphics
     * @param text   the text to print
     * @param xPos   the x position of the text
     * @param yPos   the y position of the text
     * @param center if the text has to be centered
     * @param c      the color of the text
     * @param f      the font to display the text
     */
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
