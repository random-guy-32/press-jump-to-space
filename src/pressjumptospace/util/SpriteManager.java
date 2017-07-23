package pressjumptospace.util;

import pressjumptospace.render.Sprite;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Loads and stores images to be used as textures.
 *
 * @author Charlotte Buff
 * @version 1.4.3
 */

public class SpriteManager {
    // I understand literally none of this

    /**
     * All images are safed in a hash map using their disk locations as key.
     */
    public static final HashMap<String, BufferedImage> cache = new HashMap<>();

    /**
     * Root for all image locations.
     */
    public static final String assetsLocation = "/pressjumptospace/_data/assets/image/";

    /**
     * Loads an external image into memory and stores it in a hash map for later use.
     * Distributes images that have already been loaded to avoid unnecessary loading times.
     *
     * @param name Location of the desired image file.
     * @return The loaded image.
     */
    public static BufferedImage loadImage(String name) {
        // Attempts to retrieve image from cache.
        BufferedImage image = cache.get(name);
        if (image != null) {
            // If the image is already loaded into the cache, it is simply returned straight away.
            return image;
        }
        try {
            // If the image hasn't been loaded yet it is read from disk and stored in cache.
            image = ImageIO.read(SpriteManager.class.getResource(assetsLocation + name));
            cache.put(name, image);
        }
        catch (Throwable t) {
            try {
                // If the image file cannot be found a placeholder image is returned instead.
                image = ImageIO.read(SpriteManager.class.getResource(assetsLocation + Sprite.nul));
            }
            catch (Throwable t2) {
                // If the placeholder image can't be found either an error occurs.
                Util.err("Image resource '" + name + "' couldn't be found and placeholder texture couldn't be found either. Fatal error.");
            }
        }

        // Returns the fully loaded image to be used for further processing.
        return image;
    }
}