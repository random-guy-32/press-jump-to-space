package pressjumptospace.render;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Canvas extends JPanel {
    public Canvas() throws IOException {
        setDoubleBuffered(true);
        cursor = this.getMousePosition();
    }

    public Point cursor;

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