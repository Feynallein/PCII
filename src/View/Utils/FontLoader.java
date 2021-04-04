package View.Utils;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Font Loader
 */
public class FontLoader {
    /**
     * Load a specified font at a specified size
     *
     * @param path the path to the font
     * @param size the desired size of the font
     * @return the loaded font
     */
    public static Font loadFont(String path, int size) {
        try {
            /* Load the font */
            return Font.createFont(Font.TRUETYPE_FONT, new BufferedInputStream(new FileInputStream(path))).deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException e) {
            /* Print the error on the error output and exit the program with the status -1 */
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }
}
