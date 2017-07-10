package pressjumptospace.util;

import pressjumptospace.render.Sprite;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class SpriteManager {
    // I understand literally none of this
    public static final HashMap<String, BufferedImage> cache = new HashMap<>();
    public static final String assetsLocation = "/pressjumptospace/_data/assets/image/";

    public static BufferedImage loadImage(String name) {
        BufferedImage image = cache.get(name);
        if (image != null) {
            return image;
        }
        try {
            image = ImageIO.read(SpriteManager.class.getResource(assetsLocation + name));
            cache.put(name, image);
        }
        catch (Throwable t) {
            //Util.err("Image resource '" + name + "' couldn't be found. Trying to load placeholder texture instead.");
            // t.printStackTrace();

            try {
                image = ImageIO.read(SpriteManager.class.getResource(assetsLocation + Sprite.nul));
            }
            catch (Throwable t2) {
                Util.err("Image resource '" + name + "' couldn't be found and placeholder texture couldn't be found either. Fatal error.");
                // t2.printStackTrace();
            }
        }
        return image;
    }
}