package pressjumptospace.render;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Please don't ask me about this.
 *
 * @author Charlotte Buff
 * @version 1.5
 */

public abstract class Canvas extends JPanel {
    /**
     * Standard constructor.
     *
     * @throws IOException Yes.
     */
    public Canvas() throws IOException {
        setDoubleBuffered(true);
        cursor = this.getMousePosition();
    }

    public Point cursor;

    /**
     * This was at some point going to be used for something.
     *
     * @param name File path.
     * @return Image, I would presume.
     */
    public BufferedImage loadImage(String name) {
        // ????
        try {
            return ImageIO.read(getClass().getResource("/assets/image/" + name));
        }
        catch (Throwable t) {
            // fuck everything
            t.printStackTrace();
            return null;
        }
    }
}