package pressjumptospace.tile.misc;

import pressjumptospace.tile.meta.Material;
import pressjumptospace.tile.meta.SolidTile;

/**
 * A tile without special properties.
 *
 * @author Charlotte Buff
 * @version 1.4.3
 */

public class DecorativeMetalTile2 extends SolidTile {
    /**
     * Standard constructor.
     */
    public DecorativeMetalTile2() {
        super(new int[] {0, 0, 0, 0}, Material.Metal, "tile/decorative-metal-tile-2.png", "Decorative Metal Tile 2");
    }
}
