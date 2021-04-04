package View.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Image handler
 */
public class ImageLoader {
    /**
     * Load an image
     *
     * @param path the path to the image
     * @return the loaded image
     */
    public static BufferedImage loadImage(String path) {
        try {
            /* Load an image */
            return ImageIO.read(ImageLoader.class.getResource(path));
        } catch (IOException e) {
            /* Print the error on the error output and exit the program with the status 1 */
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
