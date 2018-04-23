package pressjumptospace.tile.misc;

import pressjumptospace.tile.meta.Material;
import pressjumptospace.tile.meta.SolidTile;

/**
 * A tile without special properties.
 *
 * @author Charlotte Buff
 * @version 1.4.3
 */

public class DecorativeMetalTile extends SolidTile {
    /**
     * Standard constructor.
     */
    public DecorativeMetalTile() {
        super(Material.Metal, "tile/decorative-metal-tile.png", "Decorative Metal Tile 1");
    }
}
